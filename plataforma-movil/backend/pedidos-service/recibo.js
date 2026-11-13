const PDFDocument = require("pdfkit");

const MORADO = "#8E62C4";
const MORADO_OSCURO = "#5E3A8C";
const LILA = "#F3ECFB";
const ROSADO = "#FBE3EF";
const TEXTO = "#4A2F5E";
const SUAVE = "#8A6FA0";
const BORDE = "#DCC8EE";

const NEGOCIO = process.env.NEGOCIO || "Mi Inventario";
const DATOS_NEGOCIO = process.env.DATOS_NEGOCIO || "Tecnología · Ropa · Peluches · Portarretratos";

const moneda = (n) => "$ " + Math.round(Number(n)).toLocaleString("es-CO");

function fechaTexto(fecha) {
  const d = new Date(fecha);
  const dos = (n) => String(n).padStart(2, "0");
  let h = d.getHours();
  const ampm = h >= 12 ? "p. m." : "a. m.";
  h = h % 12 || 12;
  return `${dos(d.getDate())}/${dos(d.getMonth() + 1)}/${d.getFullYear()} ${dos(h)}:${dos(d.getMinutes())} ${ampm}`;
}

function dibujarFirma(doc, firma, x, y, ancho, alto) {
  if (!firma || !firma.length) return;
  if (firma[0] === 0x89 && firma[1] === 0x50) {
    doc.image(firma, x, y, { fit: [ancho, alto], align: "center", valign: "bottom" });
    return;
  }
  let datos;
  try {
    datos = JSON.parse(firma.toString("utf8"));
  } catch {
    return;
  }
  const escala = Math.min(ancho / datos.ancho, alto / datos.alto);
  const dx = x + (ancho - datos.ancho * escala) / 2;
  const dy = y + (alto - datos.alto * escala) / 2;
  doc.save().lineWidth(1.6).strokeColor("#2B1D38").lineCap("round").lineJoin("round");
  for (const trazo of datos.trazos) {
    if (trazo.length < 2) continue;
    doc.moveTo(dx + trazo[0][0] * escala, dy + trazo[0][1] * escala);
    for (let i = 1; i < trazo.length; i++) doc.lineTo(dx + trazo[i][0] * escala, dy + trazo[i][1] * escala);
    doc.stroke();
  }
  doc.restore();
}

function generarRecibo(pedido, detalles, salida) {
  const doc = new PDFDocument({ size: "LETTER", margin: 42 });
  doc.pipe(salida);
  const izq = 42;
  const ancho = doc.page.width - 84;

  doc.rect(izq, 36, ancho * 0.6, 92).fill(MORADO);
  doc.rect(izq + ancho * 0.6, 36, ancho * 0.4, 92).fill(MORADO_OSCURO);
  doc.fillColor("white").font("Helvetica-Bold").fontSize(22).text(NEGOCIO, izq + 18, 62);
  doc.font("Helvetica").fontSize(10).text(DATOS_NEGOCIO, izq + 18, 92);
  const derX = izq + ancho * 0.6;
  const derAncho = ancho * 0.4 - 18;
  doc.font("Helvetica-Bold").fontSize(13).text("RECIBO DE PEDIDO", derX, 54, { width: derAncho, align: "right" });
  doc.fontSize(18).text(`N.º ${String(pedido.id).padStart(6, "0")}`, derX, 74, { width: derAncho, align: "right" });
  doc.font("Helvetica").fontSize(10).text(fechaTexto(pedido.fecha), derX, 100, { width: derAncho, align: "right" });

  const datos = [
    ["CLIENTE", pedido.cliente],
    ["DOCUMENTO", pedido.documento || "—"],
    ["TIPO DE VENTA", pedido.tipo_venta],
    ["ATENDIDO POR", pedido.usuario || "—"],
  ];
  const anchoDato = ancho / 4;
  datos.forEach(([etiqueta, valor], i) => {
    const x = izq + i * anchoDato;
    doc.rect(x + 1, 148, anchoDato - 2, 50).fill(LILA);
    doc.fillColor(MORADO).font("Helvetica-Bold").fontSize(9).text(etiqueta, x + 10, 158);
    doc.fillColor(TEXTO).fontSize(10).text(String(valor), x + 10, 174, { width: anchoDato - 20, ellipsis: true, height: 14 });
  });

  const cols = [
    { t: "Referencia", w: 0.15, a: "left" },
    { t: "Descripción", w: 0.43, a: "left" },
    { t: "Cant.", w: 0.1, a: "right" },
    { t: "Precio unit.", w: 0.16, a: "right" },
    { t: "Subtotal", w: 0.16, a: "right" },
  ];
  let y = 222;
  doc.rect(izq, y, ancho, 26).fill(MORADO);
  let x = izq;
  doc.fillColor("white").font("Helvetica-Bold").fontSize(10);
  for (const c of cols) {
    doc.text(c.t, x + 7, y + 8, { width: c.w * ancho - 14, align: c.a });
    x += c.w * ancho;
  }
  y += 26;
  let unidades = 0;
  detalles.forEach((d, i) => {
    unidades += d.cantidad;
    const valores = [d.referencia, d.descripcion, String(d.cantidad), moneda(d.precio_unitario), moneda(d.subtotal)];
    doc.font("Helvetica").fontSize(10);
    const alto = Math.max(24, doc.heightOfString(d.descripcion, { width: cols[1].w * ancho - 14 }) + 12);
    doc.rect(izq, y, ancho, alto).fillAndStroke(i % 2 ? LILA : "white", BORDE);
    x = izq;
    doc.fillColor(TEXTO);
    cols.forEach((c, j) => {
      doc.text(valores[j], x + 7, y + 7, { width: c.w * ancho - 14, align: c.a });
      x += c.w * ancho;
    });
    y += alto;
  });

  y += 12;
  doc.fillColor(TEXTO).font("Helvetica").fontSize(10).text(`${unidades} unidades en el pedido`, izq, y + 12);
  doc.rect(izq + ancho * 0.62, y, ancho * 0.38, 38).fill(ROSADO);
  doc.fillColor(MORADO_OSCURO).font("Helvetica-Bold").fontSize(14)
    .text(`TOTAL  ${moneda(pedido.total)}`, izq + ancho * 0.62, y + 12, { width: ancho * 0.38 - 14, align: "right" });

  y += 70;
  doc.fillColor(TEXTO).font("Helvetica").fontSize(10)
    .text("Recibí a satisfacción los productos descritos en este recibo.", izq, y + 30, { width: ancho * 0.45 });
  const fx = izq + ancho * 0.52;
  const fAncho = ancho * 0.48;
  dibujarFirma(doc, pedido.firma, fx + 10, y, fAncho - 20, 80);
  doc.moveTo(fx, y + 88).lineTo(fx + fAncho, y + 88).strokeColor(TEXTO).lineWidth(0.7).stroke();
  doc.fillColor(TEXTO).font("Helvetica-Bold").fontSize(10)
    .text(`Firma del cliente: ${pedido.cliente}`, fx, y + 96, { width: fAncho, align: "center" });

  doc.fillColor(SUAVE).font("Helvetica-Oblique").fontSize(8)
    .text("Este documento es un recibo de entrega del pedido. No reemplaza la factura electrónica.", izq, y + 140, { width: ancho, align: "center" });
  doc.end();
}

module.exports = { generarRecibo };
