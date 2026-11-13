const crypto = require("crypto");
const { db, malaPeticion, ErrorApi, firmarToken, requiereUsuario, conErrores, texto, crearApp, arrancar } = require("comun");

const ITERACIONES = 120000;
const LONGITUD_HASH = 32;
const LONGITUD_SAL = 16;

function generarHash(contrasena) {
  const sal = crypto.randomBytes(LONGITUD_SAL);
  const hash = crypto.pbkdf2Sync(contrasena, sal, ITERACIONES, LONGITUD_HASH, "sha256");
  return `${ITERACIONES}:${sal.toString("base64")}:${hash.toString("base64")}`;
}

function verificar(contrasena, guardado) {
  const partes = String(guardado || "").split(":");
  if (partes.length !== 3) return false;
  const iteraciones = Number(partes[0]);
  const sal = Buffer.from(partes[1], "base64");
  const esperado = Buffer.from(partes[2], "base64");
  if (!iteraciones || esperado.length === 0) return false;
  const calculado = crypto.pbkdf2Sync(contrasena, sal, iteraciones, esperado.length, "sha256");
  return crypto.timingSafeEqual(esperado, calculado);
}

const app = crearApp("auth-service");

app.post("/registro", conErrores(async (req, res) => {
  const usuario = texto(req.body.usuario, "Usuario", 50);
  const contrasena = String(req.body.contrasena || "");
  const confirmacion = String(req.body.confirmacion || "");
  if (usuario.length < 4) throw malaPeticion("El usuario debe tener al menos 4 caracteres");
  if (!/^[A-Za-z0-9._-]+$/.test(usuario)) throw malaPeticion("El usuario solo puede tener letras, números, punto, guion o guion bajo");
  if (contrasena.length < 6) throw malaPeticion("La contraseña debe tener al menos 6 caracteres");
  if (contrasena !== confirmacion) throw malaPeticion("Las contraseñas no coinciden");
  const [existe] = await db().query("SELECT id FROM usuarios WHERE usuario = ?", [usuario]);
  if (existe.length) throw new ErrorApi(409, "Ese usuario ya está registrado");
  const [r] = await db().query("INSERT INTO usuarios (usuario, contrasena) VALUES (?, ?)", [usuario, generarHash(contrasena)]);
  res.status(201).json({ id: r.insertId, usuario });
}));

app.post("/login", conErrores(async (req, res) => {
  const usuario = String(req.body.usuario || "").trim();
  const contrasena = String(req.body.contrasena || "");
  if (!usuario || !contrasena) throw malaPeticion("Escribe tu usuario y contraseña");
  const [filas] = await db().query("SELECT id, usuario, contrasena FROM usuarios WHERE usuario = ?", [usuario]);
  if (!filas.length || !verificar(contrasena, filas[0].contrasena)) {
    throw new ErrorApi(401, "Usuario o contraseña incorrectos");
  }
  const u = { id: filas[0].id, usuario: filas[0].usuario };
  res.json({ token: firmarToken(u), usuario: u });
}));

app.get("/yo", requiereUsuario, (req, res) => res.json({ id: req.usuario.id, usuario: req.usuario.usuario }));

arrancar(app, "auth-service", Number(process.env.PORT || 4001));
