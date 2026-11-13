const express = require("express");
const cors = require("cors");
const { createProxyMiddleware } = require("http-proxy-middleware");
const { config, requiereUsuario } = require("comun");

const app = express();
app.use(cors({ exposedHeaders: ["Content-Disposition"] }));

app.use((req, res, next) => {
  if (req.path.includes("/interno")) return res.status(404).json({ error: "Ruta no encontrada" });
  next();
});

app.get("/api/salud", async (req, res) => {
  const estado = {};
  for (const [nombre, url] of Object.entries(config.urls)) {
    try {
      const r = await fetch(`${url}/salud`);
      estado[nombre] = r.ok ? "ok" : "con errores";
    } catch {
      estado[nombre] = "apagado";
    }
  }
  res.json({ gateway: "ok", servicios: estado });
});

const proxy = (destino) => createProxyMiddleware({
  target: destino,
  changeOrigin: true,
  on: {
    error: (err, req, res) => {
      res.writeHead(503, { "Content-Type": "application/json" });
      res.end(JSON.stringify({ error: "El servicio no está disponible en este momento" }));
    },
  },
});

app.use("/api/auth", proxy(config.urls.auth));
app.use("/api/inventario", requiereUsuario, proxy(config.urls.inventario));
app.use("/api/pedidos", requiereUsuario, proxy(config.urls.pedidos));

app.use((req, res) => res.status(404).json({ error: "Ruta no encontrada" }));

const puerto = Number(process.env.PORT || 8080);
app.listen(puerto, "0.0.0.0", () => console.log(`api-gateway escuchando en http://localhost:${puerto}`));
