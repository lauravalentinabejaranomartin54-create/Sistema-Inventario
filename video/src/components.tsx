import React from "react";
import { AbsoluteFill, interpolate, spring, useCurrentFrame, useVideoConfig } from "remotion";
import { C, fuenteBase, fuenteDecorativa } from "./theme";

export const Fondo: React.FC<{ children?: React.ReactNode }> = ({ children }) => {
  const f = useCurrentFrame();
  const blob = (x: number, y: number, r: number, color: string, vel: number, fase: number) => (
    <div
      style={{
        position: "absolute",
        left: x + Math.sin(f / vel + fase) * 40,
        top: y + Math.cos(f / (vel * 1.3) + fase) * 30,
        width: r,
        height: r,
        borderRadius: "50%",
        background: color,
        filter: "blur(90px)",
        opacity: 0.75,
      }}
    />
  );
  return (
    <AbsoluteFill style={{ background: `linear-gradient(135deg, ${C.fondo} 0%, #F6ECFB 55%, #FBEAF3 100%)`, overflow: "hidden" }}>
      {blob(-120, -160, 620, C.rosado, 70, 0)}
      {blob(1350, -120, 560, C.lila, 85, 2)}
      {blob(1250, 640, 640, "#F2D7EC", 95, 4)}
      {blob(-100, 700, 520, "#E9DDF9", 80, 1)}
      <Puntos />
      {children}
    </AbsoluteFill>
  );
};

const Puntos: React.FC = () => (
  <AbsoluteFill
    style={{
      backgroundImage: `radial-gradient(${C.borde} 1.4px, transparent 1.4px)`,
      backgroundSize: "34px 34px",
      opacity: 0.45,
    }}
  />
);

export const Destello: React.FC<{ x: number; y: number; size: number; delay?: number; color?: string }> = ({
  x, y, size, delay = 0, color = C.morado,
}) => {
  const f = useCurrentFrame();
  const { fps } = useVideoConfig();
  const s = spring({ frame: f - delay, fps, config: { damping: 12 } });
  const giro = interpolate(f, [0, 300], [0, 90]);
  return (
    <svg
      viewBox="0 0 100 100"
      style={{ position: "absolute", left: x, top: y, width: size, height: size, transform: `scale(${s}) rotate(${giro}deg)`, opacity: 0.85 }}
    >
      <path d="M50 0 C54 38 62 46 100 50 C62 54 54 62 50 100 C46 62 38 54 0 50 C38 46 46 38 50 0Z" fill={color} />
    </svg>
  );
};

export const Ventana: React.FC<{ titulo: string; ancho: number; children: React.ReactNode }> = ({ titulo, ancho, children }) => (
  <div
    style={{
      width: ancho,
      borderRadius: 14,
      overflow: "hidden",
      background: C.blanco,
      boxShadow: "0 30px 80px rgba(94,58,140,0.28), 0 6px 18px rgba(94,58,140,0.18)",
      border: `1px solid ${C.borde}`,
    }}
  >
    <div
      style={{
        height: BARRA,
        display: "flex",
        alignItems: "center",
        padding: "0 18px",
        background: "linear-gradient(90deg, #F7EEFB, #FBEFF6)",
        borderBottom: `1px solid ${C.borde}`,
        fontFamily: fuenteBase,
        fontSize: 17,
        color: C.texto,
        gap: 12,
      }}
    >
      <div style={{ width: 22, height: 22, borderRadius: 6, background: `linear-gradient(135deg, ${C.morado}, ${C.rosaFuerte})` }} />
      <span style={{ fontWeight: 500 }}>{titulo}</span>
      <div style={{ flex: 1 }} />
      <span style={{ color: C.textoSuave, fontSize: 18, letterSpacing: 26 }}>—▢✕</span>
    </div>
    {children}
  </div>
);
export const BARRA = 44;

export const Cursor: React.FC<{ x: number; y: number; clic: number; escala: number }> = ({ x, y, clic, escala }) => (
  <div style={{ position: "absolute", left: x, top: y, transform: `scale(${escala})`, transformOrigin: "0 0", pointerEvents: "none" }}>
    {clic > 0 && clic < 1 && (
      <div
        style={{
          position: "absolute",
          left: -40 * (0.4 + clic),
          top: -40 * (0.4 + clic),
          width: 80 * (0.4 + clic),
          height: 80 * (0.4 + clic),
          borderRadius: "50%",
          border: `4px solid ${C.rosaFuerte}`,
          opacity: 1 - clic,
        }}
      />
    )}
    <svg width="34" height="44" viewBox="0 0 34 44" style={{ filter: "drop-shadow(0 3px 4px rgba(0,0,0,0.35))" }}>
      <path d="M2 2 L2 36 L11 28 L17 42 L23 39 L17 26 L30 26 Z" fill="#fff" stroke="#2b1d38" strokeWidth="2.5" strokeLinejoin="round" />
    </svg>
  </div>
);

export const Marca: React.FC = () => (
  <div style={{ position: "absolute", left: 40, top: 28, display: "flex", alignItems: "center", gap: 14, fontFamily: fuenteBase, background: "rgba(255,255,255,0.9)", border: `2px solid ${C.borde}`, borderRadius: 20, padding: "8px 22px 8px 10px", boxShadow: "0 8px 22px rgba(94,58,140,0.12)" }}>
    <div style={{ width: 40, height: 40, borderRadius: 12, background: `linear-gradient(135deg, ${C.morado}, ${C.rosaFuerte})`, display: "grid", placeItems: "center", color: "#fff", fontWeight: 700, fontSize: 20 }}>
      SI
    </div>
    <div style={{ lineHeight: 1.1 }}>
      <div style={{ fontFamily: fuenteDecorativa, fontSize: 30, color: C.moradoOscuro }}>Sistema de Inventario</div>
      <div style={{ fontSize: 15, color: C.textoSuave, fontWeight: 500 }}>Trabajo autónomo · Sustentación final</div>
    </div>
  </div>
);
