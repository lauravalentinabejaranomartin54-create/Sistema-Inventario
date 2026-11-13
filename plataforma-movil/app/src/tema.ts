export const colores = {
  fondo: "#FFF4F9",
  fondo2: "#F6ECFB",
  rosado: "#F9D3E5",
  lila: "#E7DAF7",
  lilaClaro: "#F4EEFC",
  morado: "#8E62C4",
  moradoOscuro: "#5E3A8C",
  rosaFuerte: "#D45D93",
  rojo: "#C9507A",
  texto: "#4A2F5E",
  textoSuave: "#8A6FA0",
  borde: "#DCC8EE",
  blanco: "#FFFFFF",
};

export const fuenteDecorativa = "DancingScript_700Bold";

export function moneda(valor: number): string {
  const entero = Math.round(Number(valor) || 0);
  const signo = entero < 0 ? "-" : "";
  const digitos = String(Math.abs(entero)).replace(/\B(?=(\d{3})+(?!\d))/g, ".");
  return `${signo}$ ${digitos}`;
}
