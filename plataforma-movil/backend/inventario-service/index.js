const { db, malaPeticion, ErrorApi, requiereUsuario, requiereServicio, conErrores, texto, entero, precio, crearApp, arrancar } = require("comun");
const categorias = require("./categorias");

const app = crearApp("inventario-service");

function categoria(clave) {
  const c = categorias[clave];
  if (!c) throw new ErrorApi(404, "Categoría no encontrada");
  return c;
}

function aObjeto(c, fila) {
  const p = { id: fila.id };
  for (const f of c.campos) p[f.campo] = fila[f.columna];
  p.descripcion = c.descripcion(p);
  return p;
}

function leerFormulario(c, cuerpo) {
  const valores = {};
  for (const f of c.campos) {
    const v = cuerpo[f.campo];
    if (f.tipo === "texto" || f.tipo === "opciones") valores[f.columna] = texto(v, f.etiqueta, f.max);
    else if (f.tipo === "entero") valores[f.columna] = entero(v, f.etiqueta);
    else valores[f.columna] = precio(v, f.etiqueta);
  }
  return valores;
}

async function validarReferencia(c, referencia, idActual) {
  const [filas] = await db().query(`SELECT id FROM ${c.tabla} WHERE referencia = ?`, [referencia]);
  if (filas.length && filas[0].id !== idActual) throw new ErrorApi(409, `Ya existe un producto con la referencia ${referencia}`);
}

app.get("/categorias", requiereUsuario, (req, res) => {
  res.json(Object.entries(categorias).map(([clave, c]) => ({ clave, nombre: c.nombre, color: c.color, campos: c.campos.map(({ columna, ...resto }) => resto) })));
});

app.get("/:categoria", requiereUsuario, conErrores(async (req, res) => {
  const c = categoria(req.params.categoria);
  const columnas = ["id", ...c.campos.map((f) => f.columna)].join(", ");
  const [filas] = await db().query(`SELECT ${columnas} FROM ${c.tabla} ORDER BY id DESC`);
  res.json(filas.map((f) => aObjeto(c, f)));
}));

app.get("/:categoria/:id", requiereUsuario, conErrores(async (req, res) => {
  const c = categoria(req.params.categoria);
  const columnas = ["id", ...c.campos.map((f) => f.columna)].join(", ");
  const [filas] = await db().query(`SELECT ${columnas} FROM ${c.tabla} WHERE id = ?`, [Number(req.params.id)]);
  if (!filas.length) throw new ErrorApi(404, "Producto no encontrado");
  res.json(aObjeto(c, filas[0]));
}));

app.post("/:categoria", requiereUsuario, conErrores(async (req, res) => {
  const c = categoria(req.params.categoria);
  const v = leerFormulario(c, req.body);
  await validarReferencia(c, v.referencia, 0);
  const columnas = Object.keys(v);
  const [r] = await db().query(
    `INSERT INTO ${c.tabla} (${columnas.join(", ")}) VALUES (${columnas.map(() => "?").join(", ")})`,
    Object.values(v));
  res.status(201).json({ id: r.insertId, mensaje: "Producto guardado correctamente" });
}));

app.put("/:categoria/:id", requiereUsuario, conErrores(async (req, res) => {
  const c = categoria(req.params.categoria);
  const id = Number(req.params.id);
  const v = leerFormulario(c, req.body);
  await validarReferencia(c, v.referencia, id);
  const columnas = Object.keys(v);
  const [r] = await db().query(
    `UPDATE ${c.tabla} SET ${columnas.map((k) => `${k} = ?`).join(", ")} WHERE id = ?`,
    [...Object.values(v), id]);
  if (!r.affectedRows) throw new ErrorApi(404, "Producto no encontrado");
  res.json({ mensaje: "Producto actualizado correctamente" });
}));

app.delete("/:categoria/:id", requiereUsuario, conErrores(async (req, res) => {
  const c = categoria(req.params.categoria);
  const [r] = await db().query(`DELETE FROM ${c.tabla} WHERE id = ?`, [Number(req.params.id)]);
  if (!r.affectedRows) throw new ErrorApi(404, "Producto no encontrado");
  res.json({ mensaje: "Producto eliminado" });
}));

function leerItems(cuerpo) {
  if (!Array.isArray(cuerpo.items) || !cuerpo.items.length) throw malaPeticion("No hay productos para procesar");
  return cuerpo.items.map((i) => ({ categoria: i.categoria, productoId: entero(i.productoId, "producto", 1), cantidad: entero(i.cantidad, "cantidad", 1) }));
}

app.post("/interno/descontar", requiereServicio, conErrores(async (req, res) => {
  const items = leerItems(req.body);
  const conexion = await db().getConnection();
  try {
    await conexion.beginTransaction();
    const productos = [];
    for (const i of items) {
      const c = categoria(i.categoria);
      const columnas = ["id", ...c.campos.map((f) => f.columna)].join(", ");
      const [filas] = await conexion.query(`SELECT ${columnas} FROM ${c.tabla} WHERE id = ? FOR UPDATE`, [i.productoId]);
      if (!filas.length) throw new ErrorApi(404, "Uno de los productos ya no existe");
      const p = aObjeto(c, filas[0]);
      if (p.stock < i.cantidad) throw new ErrorApi(409, `No hay stock suficiente de ${p.referencia}: quedan ${p.stock}`);
      await conexion.query(`UPDATE ${c.tabla} SET stock = stock - ? WHERE id = ?`, [i.cantidad, i.productoId]);
      productos.push({ ...i, referencia: p.referencia, descripcion: p.descripcion, precioDetal: p.precioDetal, precioMayorista: p.precioMayorista });
    }
    await conexion.commit();
    res.json({ productos });
  } catch (e) {
    await conexion.rollback();
    throw e;
  } finally {
    conexion.release();
  }
}));

app.post("/interno/reponer", requiereServicio, conErrores(async (req, res) => {
  const items = leerItems(req.body);
  for (const i of items) {
    const c = categoria(i.categoria);
    await db().query(`UPDATE ${c.tabla} SET stock = stock + ? WHERE id = ?`, [i.cantidad, i.productoId]);
  }
  res.json({ mensaje: "Stock repuesto" });
}));

arrancar(app, "inventario-service", Number(process.env.PORT || 4002));
