export type Caja = { x: number; y: number; w: number; h: number };
export type Punto = { x: number; y: number };

export type Paso = {
  file: string;
  caption: string;
  title: string;
  w: number;
  h: number;
  focus?: Caja;
  cursor?: Punto;
  dialog?: boolean;
  dur?: number;
};

const LOGIN = { w: 440, h: 560 };
const MENU = { w: 1108, h: 589 };
const CAT = { w: 1540, h: 863 };

const FORM: Caja = { x: 24, y: 112, w: 435, h: 722 };
const TABLA: Caja = { x: 505, y: 175, w: 990, h: 150 };
const GUARDAR: Punto = { x: 142, y: 745 };
const ACTUALIZAR: Punto = { x: 341, y: 745 };

const dlg = (x: number, y: number, w: number, h: number): Caja => ({
  x: x - 110,
  y: y - 70,
  w: w + 220,
  h: h + 140,
});

export type Capitulo = { numero: string; titulo: string; detalle: string; pasos: Paso[] };

export const capitulos: Capitulo[] = [
  {
    numero: "01",
    titulo: "Registro e inicio de sesión",
    detalle: "Usuario y contraseña, guardada con cifrado PBKDF2",
    pasos: [
      { file: "01-login.png", caption: "Pantalla de inicio de sesión", title: "Inventario - Iniciar sesión", ...LOGIN, cursor: { x: 215, y: 441 } },
      { file: "02-registro.png", caption: "Formulario para crear una cuenta", title: "Inventario - Crear cuenta", ...LOGIN, focus: { x: 20, y: 170, w: 400, h: 220 } },
      { file: "03-registro-lleno.png", caption: "Escribimos usuario, contraseña y confirmación", title: "Inventario - Crear cuenta", ...LOGIN, cursor: { x: 218, y: 423 } },
      { file: "04-registro-ok.png", caption: "La cuenta se guarda en MySQL", title: "Inventario - Crear cuenta", ...LOGIN, dialog: true, focus: dlg(59, 235, 321, 90) },
      { file: "06-login-error.png", caption: "Con una contraseña incorrecta no deja entrar", title: "Inventario - Iniciar sesión", ...LOGIN, dialog: true, focus: dlg(83, 235, 274, 90) },
      { file: "07-login-lleno.png", caption: "Con la contraseña correcta, ingresamos", title: "Inventario - Iniciar sesión", ...LOGIN, cursor: { x: 218, y: 391 } },
    ],
  },
  {
    numero: "02",
    titulo: "Menú principal",
    detalle: "Las cuatro categorías del inventario",
    pasos: [
      { file: "08-menu.png", caption: "Saluda al usuario y muestra las 4 categorías", title: "Inventario - Menú principal", ...MENU, cursor: { x: 292, y: 214 }, dur: 105 },
    ],
  },
  {
    numero: "03",
    titulo: "Tecnología: guardar, editar y buscar",
    detalle: "CRUD completo contra la base de datos",
    pasos: [
      { file: "09-tec-vacio.png", caption: "Formulario a la izquierda, tabla a la derecha", title: "Inventario - Tecnología", ...CAT },
      { file: "10-tec-lleno.png", caption: "Llenamos los datos del producto", title: "Inventario - Tecnología", ...CAT, focus: FORM, cursor: GUARDAR },
      { file: "11-tec-guardado.png", caption: "Guardar: se inserta en la tabla tecnologia", title: "Inventario - Tecnología", ...CAT, dialog: true, focus: dlg(627, 386, 286, 90) },
      { file: "12-tec-tabla1.png", caption: "El producto aparece con precios en pesos", title: "Inventario - Tecnología", ...CAT, focus: TABLA },
      { file: "13-tec-tabla3.png", caption: "Agregamos dos productos más", title: "Inventario - Tecnología", ...CAT, focus: TABLA },
      { file: "14-tec-seleccion.png", caption: "Al hacer clic en una fila, se carga en el formulario", title: "Inventario - Tecnología", ...CAT, cursor: { x: 700, y: 266 } },
      { file: "15-tec-editado.png", caption: "Cambiamos el stock y el precio al detal", title: "Inventario - Tecnología", ...CAT, focus: { x: 24, y: 340, w: 435, h: 190 }, cursor: ACTUALIZAR },
      { file: "16-tec-actualizado.png", caption: "Actualizar: el cambio se guarda", title: "Inventario - Tecnología", ...CAT, dialog: true, focus: dlg(622, 386, 295, 90) },
      { file: "17-tec-tabla-act.png", caption: "La tabla ya muestra stock 8 y $ 2.099.000", title: "Inventario - Tecnología", ...CAT, focus: TABLA },
      { file: "18-tec-buscar.png", caption: "El buscador filtra mientras se escribe", title: "Inventario - Tecnología", ...CAT, focus: { x: 500, y: 128, w: 1000, h: 140 } },
    ],
  },
  {
    numero: "04",
    titulo: "Validaciones y eliminación",
    detalle: "El sistema no acepta datos incorrectos",
    pasos: [
      { file: "19-tec-vacio-error.png", caption: "No se puede guardar con campos vacíos", title: "Inventario - Tecnología", ...CAT, dialog: true, focus: dlg(639, 386, 262, 90) },
      { file: "20-tec-duplicado.png", caption: "No se permiten referencias repetidas", title: "Inventario - Tecnología", ...CAT, dialog: true, focus: dlg(586, 386, 368, 90) },
      { file: "21-tec-negativo.png", caption: "El stock y los precios no pueden ser negativos", title: "Inventario - Tecnología", ...CAT, dialog: true, focus: dlg(565, 386, 409, 90) },
      { file: "22-tec-confirmar.png", caption: "Antes de eliminar, pide confirmación", title: "Inventario - Tecnología", ...CAT, dialog: true, focus: dlg(596, 379, 347, 104), cursor: { x: 744, y: 456 } },
      { file: "23-tec-eliminado.png", caption: "Producto eliminado de la base de datos", title: "Inventario - Tecnología", ...CAT, dialog: true, focus: dlg(627, 386, 286, 90) },
      { file: "24-tec-final.png", caption: "Quedan dos productos en Tecnología", title: "Inventario - Tecnología", ...CAT, focus: TABLA },
    ],
  },
  {
    numero: "05",
    titulo: "Ropa, Peluches y Portarretratos",
    detalle: "Cada categoría con sus propios campos",
    pasos: [
      { file: "25-ropa-lleno.png", caption: "Ropa: tipo, marca, color y tallas", title: "Inventario - Ropa", ...CAT, focus: FORM, cursor: GUARDAR },
      { file: "27-ropa-tabla.png", caption: "Tabla de ropa con tres prendas", title: "Inventario - Ropa", ...CAT, focus: TABLA },
      { file: "28-pel-lleno.png", caption: "Peluches: color y tamaño", title: "Inventario - Peluches", ...CAT, focus: FORM, cursor: GUARDAR },
      { file: "29-pel-tabla.png", caption: "Tabla de peluches", title: "Inventario - Peluches", ...CAT, focus: TABLA },
      { file: "30-por-lleno.png", caption: "Portarretratos: referencia y medidas", title: "Inventario - Portarretratos", ...CAT, focus: FORM, cursor: GUARDAR },
      { file: "31-por-tabla.png", caption: "Tabla de portarretratos", title: "Inventario - Portarretratos", ...CAT, focus: TABLA },
    ],
  },
  {
    numero: "06",
    titulo: "Cerrar sesión",
    detalle: "Regresa a la pantalla de inicio",
    pasos: [
      { file: "32-logout.png", caption: "Cerrar sesión vuelve al inicio", title: "Inventario - Iniciar sesión", ...LOGIN },
    ],
  },
];

export const consultaTecnologia = {
  sql: "SELECT id, referencia, producto, stock, precio_detal FROM tecnologia;",
  columnas: ["id", "referencia", "producto", "stock", "precio_detal"],
  filas: [
    ["14", "TEC-001", "Audífonos Bluetooth", "15", "189900.00"],
    ["15", "TEC-002", "Portátil", "8", "2099000.00"],
  ],
};

export const consultaUsuarios = {
  sql: "SELECT id, usuario, contrasena FROM usuarios WHERE usuario = 'admin';",
  columnas: ["id", "usuario", "contrasena"],
  filas: [["6", "admin", "120000:a8eg9APg3gYRGFNCYcTrjg==:lbSLNb8YAMpUp0…"]],
};

export const REPO = "github.com/lauravalentinabejaranomartin54-create/Sistema-Inventario";
