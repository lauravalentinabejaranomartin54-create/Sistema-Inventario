const { db, config, malaPeticion, ErrorApi, requiereUsuario, conErrores, texto, entero, crearApp, arrancar } = require("comun");
const { generarRecibo } = require("./recibo");

const app = crearApp("pedidos-service", "2mb");

const TIPOS_VENTA = ["Al detal", "Al por mayor"];

async function llamarInventario(ruta, items) {
  let respuesta;
  try {
    respuesta = await fetch(`${config.urls.inventario}/interno/${ruta}`, {
      method: "POST",
      headers: { "Content-Type": "application/json", "x-token-interno": config.tokenInterno },
      body: JSON.stringify({ items }),
    });
  } catch {
    throw new ErrorApi(503, "El servicio de inventario no está disponible");
  }
  const cuerpo = await respuesta.json().catch(() => ({}));
  if (!respuesta.ok) throw new ErrorApi(respuesta.status, cuerpo.error || "Error en el servicio de inventario");
  return cuerpo;
}

function leerFirma(firma) {
  if (!firma || !Array.isArray(firma.trazos)) throw malaPeticion("El cliente debe firmar el pedido");
  const trazos = firma.trazos
    .filter((t) => Array.isArray(t) && t.length > 1)
    .map((t) => t.map((p) => [Math.round(Number(p[0]) * 10) / 10, Math.round(Number(p[1]) * 10) / 10]));
  if (!trazos.length) throw malaPeticion("El cliente debe firmar el pedido");
  const ancho = Number(firma.ancho) || 400;
  const alto = Number(firma.alto) || 160;
  return Buffer.from(JSON.stringify({ ancho, alto, trazos }), "utf8");
}

app.post("/", requiereUsuario, conErrores(async (req, res) => {
  const cliente = texto(req.body.cliente, "Cliente", 100);
  const documento = texto(req.body.documento, "Documento", 30, true);
  const tipoVenta = TIPOS_VENTA.includes(req.body.tipoVenta) ? req.body.tipoVenta : null;
  if (!tipoVenta) throw malaPeticion("Elige el tipo de venta");
  if (!Array.isArray(req.body.items) || !req.body.items.length) throw malaPeticion("Agrega al menos un producto al pedido");
  const items = req.body.items.map((i) => ({
    categoria: String(i.categoria),
    productoId: entero(i.productoId, "producto", 1),
    cantidad: entero(i.cantidad, "cantidad", 1),
  }));
  const firma = leerFirma(req.body.firma);

  const { productos } = await llamarInventario("descontar", items);
  const detalles = productos.map((p) => {
    const precioUnitario = tipoVenta === "Al por mayor" ? p.precioMayorista : p.precioDetal;
    return { ...p, precioUnitario, subtotal: precioUnitario * p.cantidad };
  });
  const total = detalles.reduce((s, d) => s + d.subtotal, 0);

  const conexion = await db().getConnection();
  try {
    await conexion.beginTransaction();
    const [r] = await conexion.query(
      "INSERT INTO pedidos (cliente, documento, tipo_venta, total, firma, usuario_id) VALUES (?,?,?,?,?,?)",
      [cliente, documento, tipoVenta, total, firma, req.usuario.id]);
    for (const d of detalles) {
      await conexion.query(
        "INSERT INTO pedido_detalle (pedido_id, categoria, producto_id, referencia, descripcion, cantidad, precio_unitario, subtotal) VALUES (?,?,?,?,?,?,?,?)",
        [r.insertId, d.categoria, d.productoId, d.referencia, d.descripcion, d.cantidad, d.precioUnitario, d.subtotal]);
    }
    await conexion.commit();
    res.status(201).json({ id: r.insertId, total, mensaje: `Pedido N.º ${r.insertId} registrado` });
  } catch (e) {
    await conexion.rollback();
    await llamarInventario("reponer", items).catch((err) => console.error("[pedidos-service] No se pudo reponer el stock", err));
    throw e;
  } finally {
    conexion.release();
  }
}));

app.get("/", requiereUsuario, conErrores(async (req, res) => {
  const [filas] = await db().query(
    `SELECT p.id, p.cliente, p.documento, p.tipo_venta AS tipoVenta, p.total, p.fecha, u.usuario
     FROM pedidos p JOIN usuarios u ON u.id = p.usuario_id ORDER BY p.id DESC LIMIT 100`);
  res.json(filas);
}));

async function buscarPedido(id) {
  const [filas] = await db().query(
    "SELECT p.*, u.usuario FROM pedidos p JOIN usuarios u ON u.id = p.usuario_id WHERE p.id = ?", [id]);
  if (!filas.length) throw new ErrorApi(404, "Pedido no encontrado");
  const [detalles] = await db().query("SELECT * FROM pedido_detalle WHERE pedido_id = ? ORDER BY id", [id]);
  return { pedido: filas[0], detalles };
}

app.get("/:id", requiereUsuario, conErrores(async (req, res) => {
  const { pedido, detalles } = await buscarPedido(Number(req.params.id));
  delete pedido.firma;
  res.json({ ...pedido, detalles });
}));

app.get("/:id/recibo.pdf", requiereUsuario, conErrores(async (req, res) => {
  const { pedido, detalles } = await buscarPedido(Number(req.params.id));
  res.setHeader("Content-Type", "application/pdf");
  res.setHeader("Content-Disposition", `inline; filename="Recibo-${String(pedido.id).padStart(6, "0")}.pdf"`);
  generarRecibo(pedido, detalles, res);
}));

arrancar(app, "pedidos-service", Number(process.env.PORT || 4003));
