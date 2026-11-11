import React from "react";
import { AbsoluteFill, Easing, Img, interpolate, spring, staticFile, useCurrentFrame, useVideoConfig } from "remotion";
import { BARRA, Cursor, Destello, Fondo, Marca, Ventana } from "./components";
import { Capitulo, Paso, REPO, consultaTecnologia, consultaUsuarios } from "./data";
import { C, fuenteBase, fuenteDecorativa } from "./theme";

const entra = (f: number, fps: number, delay = 0) => spring({ frame: f - delay, fps, config: { damping: 16, mass: 0.8 } });

export const Intro: React.FC = () => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  const t = entra(f, fps, 5);
  const sub = entra(f, fps, 25);
  const chips = ["Java 25", "Swing", "MySQL", "MVC + DAO"];
  return (
    <Fondo>
      <Destello x={300} y={190} size={70} delay={10} />
      <Destello x={1520} y={260} size={90} delay={18} color={C.rosaFuerte} />
      <Destello x={1450} y={780} size={56} delay={26} />
      <Destello x={360} y={790} size={44} delay={34} color={C.rosaFuerte} />
      <AbsoluteFill style={{ alignItems: "center", justifyContent: "center", textAlign: "center" }}>
        <div style={{ fontFamily: fuenteBase, fontSize: 30, fontWeight: 600, letterSpacing: 8, color: C.rosaFuerte, opacity: sub, textTransform: "uppercase" }}>
          Proyecto final · Trabajo autónomo
        </div>
        <div
          style={{
            fontFamily: fuenteDecorativa,
            fontSize: 150,
            lineHeight: 1.05,
            color: C.moradoOscuro,
            transform: `translateY(${(1 - t) * 60}px) scale(${0.9 + 0.1 * t})`,
            opacity: t,
            margin: "18px 0 10px",
          }}
        >
          Sistema de Inventario
        </div>
        <div style={{ fontFamily: fuenteBase, fontSize: 44, color: C.texto, fontWeight: 500, opacity: sub, transform: `translateY(${(1 - sub) * 30}px)` }}>
          por Categorías
        </div>
        <div style={{ display: "flex", gap: 18, marginTop: 50 }}>
          {chips.map((c, i) => {
            const s = entra(f, fps, 45 + i * 6);
            return (
              <div key={c} style={{ fontFamily: fuenteBase, fontSize: 26, fontWeight: 600, color: C.moradoOscuro, background: C.blanco, border: `2px solid ${C.borde}`, borderRadius: 999, padding: "12px 28px", transform: `scale(${s})`, boxShadow: "0 8px 20px rgba(94,58,140,0.12)" }}>
                {c}
              </div>
            );
          })}
        </div>
      </AbsoluteFill>
    </Fondo>
  );
};

const tecnologias = [
  { nombre: "Java 25", uso: "Lenguaje de programación", color: C.morado },
  { nombre: "Swing", uso: "Ventanas, tablas y formularios", color: C.rosaFuerte },
  { nombre: "MySQL · XAMPP", uso: "Base de datos gestion_inventario", color: "#8C7BD1" },
  { nombre: "JDBC", uso: "Conexión con PreparedStatement", color: "#B5699F" },
  { nombre: "MVC + DAO", uso: "Código separado en capas", color: C.moradoOscuro },
  { nombre: "NetBeans · Git", uso: "Entorno y control de versiones", color: "#C77DB0" },
];

export const Tecnologias: React.FC = () => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  return (
    <Fondo>
      <Marca />
      <Titulo texto="Tecnologías utilizadas" />
      <div style={{ position: "absolute", left: 160, right: 160, top: 330, display: "grid", gridTemplateColumns: "repeat(3, 1fr)", gap: 36 }}>
        {tecnologias.map((t, i) => {
          const s = entra(f, fps, 10 + i * 7);
          return (
            <div key={t.nombre} style={{ background: C.blanco, borderRadius: 26, padding: "34px 36px", border: `2px solid ${C.borde}`, boxShadow: "0 18px 40px rgba(94,58,140,0.12)", transform: `translateY(${(1 - s) * 50}px)`, opacity: s }}>
              <div style={{ width: 54, height: 8, borderRadius: 8, background: t.color, marginBottom: 22 }} />
              <div style={{ fontFamily: fuenteBase, fontSize: 40, fontWeight: 700, color: C.moradoOscuro }}>{t.nombre}</div>
              <div style={{ fontFamily: fuenteBase, fontSize: 25, color: C.textoSuave, marginTop: 8 }}>{t.uso}</div>
            </div>
          );
        })}
      </div>
    </Fondo>
  );
};

const Titulo: React.FC<{ texto: string; sub?: string }> = ({ texto, sub }) => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  const s = entra(f, fps);
  return (
    <div style={{ position: "absolute", top: 150, width: "100%", textAlign: "center", opacity: s, transform: `translateY(${(1 - s) * 30}px)` }}>
      <div style={{ fontFamily: fuenteDecorativa, fontSize: 92, color: C.moradoOscuro }}>{texto}</div>
      {sub && <div style={{ fontFamily: fuenteBase, fontSize: 28, color: C.textoSuave, marginTop: 4 }}>{sub}</div>}
    </div>
  );
};

const capas = [
  { nombre: "Vista", detalle: "FrmLogin, FrmMenu y una ventana por categoría" },
  { nombre: "Controlador", detalle: "Valida los datos antes de guardar" },
  { nombre: "DAO", detalle: "Consultas SQL con PreparedStatement" },
  { nombre: "MySQL", detalle: "usuarios, tecnologia, ropa, peluches, portarretratos" },
];

export const Arquitectura: React.FC = () => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  const modelo = entra(f, fps, 70);
  return (
    <Fondo>
      <Marca />
      <Titulo texto="Arquitectura MVC + DAO" sub="Cada capa solo habla con la de abajo" />
      <div style={{ position: "absolute", left: 300, top: 360, width: 880 }}>
        {capas.map((c, i) => {
          const s = entra(f, fps, 12 + i * 14);
          const ultima = i === capas.length - 1;
          return (
            <div key={c.nombre} style={{ opacity: s, transform: `translateX(${(1 - s) * -60}px)` }}>
              <div style={{ display: "flex", alignItems: "center", gap: 28, background: ultima ? `linear-gradient(90deg, ${C.morado}, ${C.rosaFuerte})` : C.blanco, color: ultima ? "#fff" : C.moradoOscuro, borderRadius: 22, padding: "22px 34px", border: `2px solid ${ultima ? "transparent" : C.borde}`, boxShadow: "0 14px 30px rgba(94,58,140,0.12)" }}>
                <div style={{ fontFamily: fuenteBase, fontWeight: 700, fontSize: 36, width: 250 }}>{c.nombre}</div>
                <div style={{ fontFamily: fuenteBase, fontSize: 25, opacity: ultima ? 0.95 : 0.8 }}>{c.detalle}</div>
              </div>
              {!ultima && (
                <div style={{ height: 34, display: "flex", justifyContent: "flex-start", paddingLeft: 140 }}>
                  <div style={{ width: 4, background: C.borde, height: 34 * entra(f, fps, 22 + i * 14) }} />
                </div>
              )}
            </div>
          );
        })}
      </div>
      <div style={{ position: "absolute", left: 1240, top: 360, width: 380, borderRadius: 26, background: C.lilaClaro, border: `2px dashed ${C.morado}`, padding: 36, opacity: modelo, transform: `scale(${0.9 + 0.1 * modelo})` }}>
        <div style={{ fontFamily: fuenteBase, fontWeight: 700, fontSize: 36, color: C.moradoOscuro }}>Modelo</div>
        <div style={{ fontFamily: fuenteBase, fontSize: 23, color: C.textoSuave, margin: "10px 0 26px" }}>Objetos que viajan entre capas</div>
        {["ArticuloInventario (interfaz)", "Tecnologia", "Ropa", "Peluche", "Portarretrato", "Usuario"].map((m, i) => (
          <div key={m} style={{ fontFamily: fuenteBase, fontSize: 25, color: C.texto, padding: "7px 0", opacity: entra(f, fps, 80 + i * 5), fontWeight: i === 0 ? 600 : 400 }}>
            {m}
          </div>
        ))}
      </div>
    </Fondo>
  );
};

export const PortadaCapitulo: React.FC<{ cap: Capitulo }> = ({ cap }) => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  const s = entra(f, fps);
  const s2 = entra(f, fps, 8);
  return (
    <Fondo>
      <Marca />
      <AbsoluteFill style={{ justifyContent: "center", paddingLeft: 240 }}>
        <div style={{ fontFamily: fuenteBase, fontWeight: 700, fontSize: 420, lineHeight: 1, color: C.morado, opacity: 0.1 * s, position: "absolute", right: 160, top: 300 }}>{cap.numero}</div>
        <div style={{ fontFamily: fuenteBase, fontWeight: 600, fontSize: 30, letterSpacing: 6, color: C.rosaFuerte, opacity: s, textTransform: "uppercase" }}>Capítulo {cap.numero}</div>
        <div style={{ fontFamily: fuenteDecorativa, fontSize: 110, color: C.moradoOscuro, transform: `translateX(${(1 - s) * -80}px)`, opacity: s, margin: "8px 0" }}>{cap.titulo}</div>
        <div style={{ width: 340 * s2, height: 10, borderRadius: 10, background: `linear-gradient(90deg, ${C.morado}, ${C.rosaFuerte})` }} />
        <div style={{ fontFamily: fuenteBase, fontSize: 34, color: C.texto, marginTop: 26, opacity: s2 }}>{cap.detalle}</div>
      </AbsoluteFill>
      <Destello x={1500} y={300} size={80} delay={6} />
      <Destello x={1600} y={720} size={50} delay={12} color={C.rosaFuerte} />
    </Fondo>
  );
};

const AREA_W = 1680;
const AREA_H = 860;

export const PasoEscena: React.FC<{ paso: Paso; cap: Capitulo; indice: number; anterior?: Paso }> = ({ paso, cap, indice, anterior }) => {
  const f = useCurrentFrame();
  const { fps, durationInFrames } = useVideoConfig();

  const escala = Math.min(AREA_W / paso.w, (AREA_H - BARRA) / paso.h, 1.45);
  const vw = paso.w * escala;
  const vh = paso.h * escala + BARRA;
  const wx = (1920 - vw) / 2;
  const wy = 100 + (AREA_H - vh) / 2;

  let zoom = 1, tx = 0, ty = 0, ox = 960, oy = 540;
  if (paso.focus) {
    const fo = paso.focus;
    ox = wx + (fo.x + fo.w / 2) * escala;
    oy = wy + BARRA + (fo.y + fo.h / 2) * escala;
    const objetivo = Math.min(1500 / (fo.w * escala), 700 / (fo.h * escala), paso.dialog ? 1.9 : 1.75, 2 / escala);
    const p = interpolate(f, [8, 42], [0, 1], { extrapolateLeft: "clamp", extrapolateRight: "clamp", easing: Easing.inOut(Easing.cubic) });
    zoom = 1 + (Math.max(1, objetivo) - 1) * p;
    tx = (960 - ox) * p;
    ty = (500 - oy) * p;
  }

  let cursor = null;
  if (paso.cursor) {
    const desde = anterior?.cursor && anterior.w === paso.w ? anterior.cursor : { x: paso.w * 0.82, y: paso.h * 0.92 };
    const inicio = paso.focus ? 30 : 10;
    const p = interpolate(f, [inicio, inicio + 30], [0, 1], { extrapolateLeft: "clamp", extrapolateRight: "clamp", easing: Easing.inOut(Easing.quad) });
    const clic = interpolate(f, [inicio + 32, inicio + 50], [0, 1], { extrapolateLeft: "clamp", extrapolateRight: "clamp" });
    const x = (desde.x + (paso.cursor.x - desde.x) * p) * escala;
    const y = (desde.y + (paso.cursor.y - desde.y) * p) * escala;
    cursor = <Cursor x={x} y={y} clic={clic} escala={1 / Math.max(1, zoom * 0.85)} />;
  }

  const etiqueta = entra(f, fps, 6);
  const salida = interpolate(f, [durationInFrames - 12, durationInFrames], [1, 0.96], { extrapolateLeft: "clamp" });

  return (
    <Fondo>
      <AbsoluteFill style={{ transform: `translate(${tx}px, ${ty}px) scale(${zoom * salida})`, transformOrigin: `${ox}px ${oy}px` }}>
        <div style={{ position: "absolute", left: wx, top: wy }}>
          <Ventana titulo={paso.title} ancho={vw}>
            <div style={{ position: "relative", width: vw, height: paso.h * escala }}>
              <Img src={staticFile(`capturas/${paso.file}`)} style={{ width: vw, height: paso.h * escala, display: "block" }} />
              {cursor}
            </div>
          </Ventana>
        </div>
      </AbsoluteFill>
      <Marca />
      <div style={{ position: "absolute", right: 56, top: 46, fontFamily: fuenteBase, fontSize: 20, fontWeight: 600, color: C.moradoOscuro, background: "rgba(255,255,255,0.85)", border: `2px solid ${C.borde}`, padding: "10px 22px", borderRadius: 999 }}>
        {cap.numero} · {cap.titulo}
      </div>
      <div
        style={{
          position: "absolute",
          left: "50%",
          bottom: 44,
          transform: `translate(-50%, ${(1 - etiqueta) * 40}px)`,
          opacity: etiqueta,
          display: "flex",
          alignItems: "center",
          gap: 18,
          background: "rgba(255,255,255,0.94)",
          border: `2px solid ${C.borde}`,
          borderRadius: 999,
          padding: "14px 34px 14px 16px",
          boxShadow: "0 16px 40px rgba(94,58,140,0.22)",
          fontFamily: fuenteBase,
          whiteSpace: "nowrap",
        }}
      >
        <div style={{ background: `linear-gradient(135deg, ${C.morado}, ${C.rosaFuerte})`, color: "#fff", fontWeight: 700, fontSize: 22, borderRadius: 999, padding: "8px 18px" }}>
          Paso {indice}
        </div>
        <div style={{ fontSize: 30, fontWeight: 500, color: C.texto }}>{paso.caption}</div>
      </div>
    </Fondo>
  );
};

const TablaSQL: React.FC<{ consulta: { sql: string; columnas: string[]; filas: string[][] }; inicio: number; resaltar?: number }> = ({ consulta, inicio, resaltar }) => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  const letras = Math.floor(interpolate(f - inicio, [0, 40], [0, consulta.sql.length], { extrapolateLeft: "clamp", extrapolateRight: "clamp" }));
  return (
    <div style={{ marginBottom: 40 }}>
      <div style={{ fontFamily: "Consolas, monospace", fontSize: 22, color: C.moradoOscuro, marginBottom: 18, whiteSpace: "nowrap" }}>
        <span style={{ color: C.rosaFuerte, fontWeight: 700 }}>MariaDB [gestion_inventario]&gt; </span>
        {consulta.sql.slice(0, letras)}
        {letras < consulta.sql.length && <span style={{ opacity: Math.floor(f / 8) % 2 }}>▍</span>}
      </div>
      <table style={{ borderCollapse: "collapse", fontFamily: fuenteBase, fontSize: 25, opacity: entra(f, fps, inicio + 45), width: "100%" }}>
        <thead>
          <tr>
            {consulta.columnas.map((c) => (
              <th key={c} style={{ textAlign: "left", background: C.morado, color: "#fff", padding: "12px 18px", fontWeight: 600 }}>{c}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {consulta.filas.map((fila, i) => (
            <tr key={i} style={{ background: i % 2 ? C.lilaClaro : C.blanco, opacity: entra(f, fps, inicio + 50 + i * 6) }}>
              {fila.map((v, j) => (
                <td key={j} style={{ padding: "12px 18px", color: C.texto, borderBottom: `1px solid ${C.borde}`, fontFamily: j === resaltar ? "Consolas, monospace" : undefined, background: j === resaltar ? "#FCE3EF" : undefined, fontWeight: j === resaltar ? 600 : 400 }}>
                  {v}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export const BaseDatos: React.FC = () => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  const nota = entra(f, fps, 190);
  return (
    <Fondo>
      <Marca />
      <Titulo texto="Los datos quedan en MySQL" sub="Consultas reales a la base gestion_inventario" />
      <div style={{ position: "absolute", left: 200, right: 200, top: 340 }}>
        <Ventana titulo="XAMPP · Consola de MySQL" ancho={1520}>
          <div style={{ padding: "34px 40px 6px" }}>
            <TablaSQL consulta={consultaTecnologia} inicio={10} />
            <TablaSQL consulta={consultaUsuarios} inicio={110} resaltar={2} />
          </div>
        </Ventana>
      </div>
      <div style={{ position: "absolute", right: 150, top: 840, fontFamily: fuenteBase, fontSize: 28, fontWeight: 600, color: "#fff", background: C.rosaFuerte, borderRadius: 18, padding: "16px 28px", transform: `scale(${nota}) rotate(-2deg)`, boxShadow: "0 14px 30px rgba(212,93,147,0.35)" }}>
        La contraseña se guarda cifrada (PBKDF2)
      </div>
    </Fondo>
  );
};

const resultados = [
  { valor: 4, texto: "categorías" },
  { valor: 5, texto: "tablas en MySQL" },
  { valor: 47, texto: "pruebas superadas" },
  { valor: 0, texto: "contraseñas en texto plano" },
];

export const Resultados: React.FC = () => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  return (
    <Fondo>
      <Marca />
      <Titulo texto="Resultados" />
      <div style={{ position: "absolute", left: 140, right: 140, top: 380, display: "grid", gridTemplateColumns: "repeat(4, 1fr)", gap: 34 }}>
        {resultados.map((r, i) => {
          const s = entra(f, fps, 10 + i * 8);
          const n = Math.round(interpolate(f, [15 + i * 8, 55 + i * 8], [0, r.valor], { extrapolateLeft: "clamp", extrapolateRight: "clamp" }));
          return (
            <div key={r.texto} style={{ background: C.blanco, borderRadius: 30, padding: "50px 24px", textAlign: "center", border: `2px solid ${C.borde}`, boxShadow: "0 18px 40px rgba(94,58,140,0.12)", transform: `translateY(${(1 - s) * 60}px)`, opacity: s }}>
              <div style={{ fontFamily: fuenteBase, fontWeight: 700, fontSize: 130, lineHeight: 1, backgroundImage: `linear-gradient(135deg, ${C.morado}, ${C.rosaFuerte})`, WebkitBackgroundClip: "text", color: "transparent" }}>{n}</div>
              <div style={{ fontFamily: fuenteBase, fontSize: 30, color: C.texto, marginTop: 18, fontWeight: 500 }}>{r.texto}</div>
            </div>
          );
        })}
      </div>
      <div style={{ position: "absolute", bottom: 150, width: "100%", textAlign: "center", fontFamily: fuenteBase, fontSize: 30, color: C.textoSuave, opacity: entra(f, fps, 60) }}>
        Registro, inicio de sesión y CRUD completo en las cuatro categorías
      </div>
    </Fondo>
  );
};

export const Cierre: React.FC = () => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  const s = entra(f, fps, 5);
  const s2 = entra(f, fps, 25);
  return (
    <Fondo>
      <Destello x={420} y={240} size={80} delay={8} />
      <Destello x={1430} y={230} size={64} delay={14} color={C.rosaFuerte} />
      <Destello x={1500} y={760} size={90} delay={20} />
      <AbsoluteFill style={{ alignItems: "center", justifyContent: "center", textAlign: "center" }}>
        <div style={{ fontFamily: fuenteDecorativa, fontSize: 210, color: C.moradoOscuro, transform: `scale(${0.8 + 0.2 * s})`, opacity: s }}>¡Gracias!</div>
        <div style={{ fontFamily: fuenteBase, fontSize: 38, color: C.texto, opacity: s2, marginTop: 10 }}>Sistema de Inventario por Categorías · Trabajo autónomo</div>
        <div style={{ fontFamily: "Consolas, monospace", fontSize: 28, color: C.moradoOscuro, background: C.blanco, border: `2px solid ${C.borde}`, borderRadius: 16, padding: "16px 30px", marginTop: 44, opacity: s2 }}>
          {REPO}
        </div>
      </AbsoluteFill>
    </Fondo>
  );
};
