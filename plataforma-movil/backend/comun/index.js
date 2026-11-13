const express = require("express");
const mysql = require("mysql2/promise");
const jwt = require("jsonwebtoken");

const config = {
  db: {
    host: process.env.DB_HOST || "localhost",
    port: Number(process.env.DB_PORT || 3306),
    user: process.env.DB_USER || "root",
    password: process.env.DB_PASSWORD || "",
    database: process.env.DB_NAME || "gestion_inventario",
  },
  jwtSecreto: process.env.JWT_SECRET || "cambia-este-secreto-en-produccion",
  tokenInterno: process.env.INTERNAL_TOKEN || "token-interno-de-desarrollo",
  urls: {
    auth: process.env.AUTH_URL || "http://localhost:4001",
    inventario: process.env.INVENTARIO_URL || "http://localhost:4002",
    pedidos: process.env.PEDIDOS_URL || "http://localhost:4003",
  },
};

let pool;
function db() {
  if (!pool) {
    pool = mysql.createPool({ ...config.db, waitForConnections: true, connectionLimit: 10, decimalNumbers: true, dateStrings: false });
  }
  return pool;
}

class ErrorApi extends Error {
  constructor(estado, mensaje) {
    super(mensaje);
    this.estado = estado;
  }
}

const malaPeticion = (mensaje) => new ErrorApi(400, mensaje);

function firmarToken(usuario) {
  return jwt.sign({ id: usuario.id, usuario: usuario.usuario }, config.jwtSecreto, { expiresIn: "12h" });
}

function leerToken(req) {
  const cabecera = req.headers.authorization || "";
  if (cabecera.startsWith("Bearer ")) return cabecera.slice(7);
  if (req.query && typeof req.query.token === "string") return req.query.token;
  return null;
}

function requiereUsuario(req, res, next) {
  const token = leerToken(req);
  if (!token) return res.status(401).json({ error: "Debes iniciar sesión" });
  try {
    req.usuario = jwt.verify(token, config.jwtSecreto);
    next();
  } catch {
    res.status(401).json({ error: "La sesión expiró, vuelve a iniciar sesión" });
  }
}

function requiereServicio(req, res, next) {
  if (req.headers["x-token-interno"] !== config.tokenInterno) {
    return res.status(403).json({ error: "Solo otros servicios pueden usar esta ruta" });
  }
  next();
}

const conErrores = (fn) => (req, res, next) => Promise.resolve(fn(req, res, next)).catch(next);

function manejadorErrores(nombre) {
  return (err, req, res, next) => {
    if (err instanceof ErrorApi) return res.status(err.estado).json({ error: err.message });
    if (err && err.code === "ER_DUP_ENTRY") return res.status(409).json({ error: "Ya existe un registro con ese valor" });
    if (err && (err.code === "ECONNREFUSED" || err.code === "PROTOCOL_CONNECTION_LOST")) {
      return res.status(503).json({ error: "No hay conexión con la base de datos. ¿Está encendido MySQL?" });
    }
    if (err && err.type === "entity.too.large") return res.status(413).json({ error: "La información enviada es demasiado grande" });
    console.error(`[${nombre}]`, err);
    res.status(500).json({ error: "Error interno del servidor" });
  };
}

function texto(valor, campo, max, opcional = false) {
  const t = typeof valor === "string" ? valor.trim() : valor == null ? "" : String(valor).trim();
  if (!t) {
    if (opcional) return null;
    throw malaPeticion(`El campo ${campo} es obligatorio`);
  }
  if (t.length > max) throw malaPeticion(`El campo ${campo} admite máximo ${max} caracteres`);
  return t;
}

function entero(valor, campo, minimo = 0) {
  const n = typeof valor === "number" ? valor : Number(String(valor ?? "").trim());
  if (!Number.isInteger(n)) throw malaPeticion(`El campo ${campo} debe ser un número entero`);
  if (n < minimo) throw malaPeticion(minimo === 0 ? `El campo ${campo} no puede ser negativo` : `El campo ${campo} debe ser mayor que ${minimo - 1}`);
  return n;
}

function precio(valor, campo) {
  let t = typeof valor === "number" ? String(valor) : String(valor ?? "").trim().replace(/\$/g, "").replace(/\s/g, "");
  if (!t) throw malaPeticion(`El campo ${campo} es obligatorio`);
  if (t.includes(",")) t = t.replace(/\./g, "").replace(",", ".");
  else if (/^\d{1,3}(\.\d{3})+$/.test(t)) t = t.replace(/\./g, "");
  if (!/^\d+(\.\d{1,2})?$/.test(t)) throw malaPeticion(`El campo ${campo} debe ser un precio válido`);
  const n = Number(t);
  if (n > 9999999999.99) throw malaPeticion(`El campo ${campo} es demasiado alto`);
  return n;
}

function crearApp(nombre, limite = "100kb") {
  const app = express();
  app.get("/salud", (req, res) => res.json({ servicio: nombre, estado: "ok" }));
  app.use(express.json({ limit: limite }));
  return app;
}

function arrancar(app, nombre, puerto) {
  app.use(manejadorErrores(nombre));
  return app.listen(puerto, () => console.log(`${nombre} escuchando en http://localhost:${puerto}`));
}

module.exports = {
  config, db, ErrorApi, malaPeticion, firmarToken, requiereUsuario, requiereServicio,
  conErrores, texto, entero, precio, crearApp, arrancar,
};
