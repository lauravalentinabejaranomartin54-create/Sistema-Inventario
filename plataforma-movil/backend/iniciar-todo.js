const { spawn } = require("child_process");
const path = require("path");

const servicios = [
  { nombre: "auth", carpeta: "auth-service", color: "\x1b[35m" },
  { nombre: "inventario", carpeta: "inventario-service", color: "\x1b[36m" },
  { nombre: "pedidos", carpeta: "pedidos-service", color: "\x1b[33m" },
  { nombre: "gateway", carpeta: "api-gateway", color: "\x1b[32m" },
];

const procesos = servicios.map((s) => {
  const p = spawn(process.execPath, ["index.js"], { cwd: path.join(__dirname, s.carpeta), env: process.env });
  const escribir = (dato) => String(dato).trimEnd().split("\n").forEach((l) => console.log(`${s.color}[${s.nombre}]\x1b[0m ${l}`));
  p.stdout.on("data", escribir);
  p.stderr.on("data", escribir);
  p.on("exit", (codigo) => console.log(`${s.color}[${s.nombre}]\x1b[0m se detuvo (código ${codigo})`));
  return p;
});

const detener = () => {
  procesos.forEach((p) => p.kill());
  process.exit(0);
};
process.on("SIGINT", detener);
process.on("SIGTERM", detener);
