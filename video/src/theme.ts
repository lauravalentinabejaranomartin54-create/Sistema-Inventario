import { loadFont as loadScript } from "@remotion/google-fonts/DancingScript";
import { loadFont as loadSans } from "@remotion/google-fonts/Poppins";

export const C = {
  fondo: "#FFF4F9",
  rosado: "#F9D3E5",
  lila: "#E7DAF7",
  lilaClaro: "#F3ECFB",
  morado: "#8E62C4",
  moradoOscuro: "#5E3A8C",
  rosaFuerte: "#D45D93",
  texto: "#4A2F5E",
  textoSuave: "#8A6FA0",
  borde: "#DCC8EE",
  blanco: "#FFFFFF",
};

export const fuenteDecorativa = loadScript("normal", { weights: ["700"] }).fontFamily;
export const fuenteBase = loadSans("normal", { weights: ["400", "500", "600", "700"] }).fontFamily;

export const FPS = 30;
export const TRANSICION = 15;
