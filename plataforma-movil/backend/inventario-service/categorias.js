const PRECIOS = [
  { campo: "precioImportador", columna: "precio_importador", etiqueta: "Precio importador", tipo: "precio" },
  { campo: "precioMayorista", columna: "precio_mayorista", etiqueta: "Precio mayorista", tipo: "precio" },
  { campo: "precioDetal", columna: "precio_detal", etiqueta: "Precio al detal", tipo: "precio" },
];

const REFERENCIA = { campo: "referencia", columna: "referencia", etiqueta: "Referencia", tipo: "texto", max: 30 };
const CARACTERISTICA = { campo: "caracteristica", columna: "caracteristica", etiqueta: "Característica", tipo: "texto", max: 255 };
const STOCK = { campo: "stock", columna: "stock", etiqueta: "Stock", tipo: "entero" };

const categorias = {
  tecnologia: {
    nombre: "Tecnología",
    tabla: "tecnologia",
    color: "#8E62C4",
    campos: [
      REFERENCIA,
      { campo: "producto", columna: "producto", etiqueta: "Producto", tipo: "texto", max: 100 },
      { campo: "marca", columna: "marca", etiqueta: "Marca", tipo: "texto", max: 100 },
      CARACTERISTICA, STOCK, ...PRECIOS,
    ],
    descripcion: (p) => `${p.producto} ${p.marca}`,
  },
  ropa: {
    nombre: "Ropa",
    tabla: "ropa",
    color: "#D45D93",
    campos: [
      REFERENCIA,
      {
        campo: "tipoRopa", columna: "tipo_ropa", etiqueta: "Tipo de ropa", tipo: "opciones", max: 50,
        opciones: ["Blusa", "Camiseta", "Camisa", "Pantalón", "Jean", "Falda", "Vestido", "Short", "Chaqueta", "Buzo", "Pijama", "Conjunto", "Conjunto deportivo"],
      },
      { campo: "marca", columna: "marca", etiqueta: "Marca", tipo: "texto", max: 100 },
      { campo: "color", columna: "color", etiqueta: "Color", tipo: "texto", max: 50 },
      CARACTERISTICA,
      { campo: "tallas", columna: "tallas", etiqueta: "Tallas", tipo: "texto", max: 50 },
      { campo: "stockCurvas", columna: "stock_curvas", etiqueta: "Stock de curvas", tipo: "entero" },
      STOCK, ...PRECIOS,
    ],
    descripcion: (p) => `${p.tipoRopa} ${p.marca} ${p.color} (${p.tallas})`,
  },
  peluches: {
    nombre: "Peluches",
    tabla: "peluches",
    color: "#8C7BD1",
    campos: [
      REFERENCIA,
      { campo: "producto", columna: "producto", etiqueta: "Producto", tipo: "texto", max: 100 },
      { campo: "color", columna: "color", etiqueta: "Color", tipo: "texto", max: 50 },
      CARACTERISTICA,
      { campo: "tamano", columna: "tamano", etiqueta: "Tamaño", tipo: "opciones", max: 50, opciones: ["Pequeño", "Mediano", "Grande", "Gigante"] },
      STOCK, ...PRECIOS,
    ],
    descripcion: (p) => `${p.producto} ${p.color} ${p.tamano}`,
  },
  portarretratos: {
    nombre: "Portarretratos",
    tabla: "portarretratos",
    color: "#B5699F",
    campos: [
      REFERENCIA,
      { campo: "producto", columna: "producto", etiqueta: "Producto", tipo: "texto", max: 100 },
      CARACTERISTICA, STOCK, ...PRECIOS,
    ],
    descripcion: (p) => p.producto,
  },
};

module.exports = categorias;
