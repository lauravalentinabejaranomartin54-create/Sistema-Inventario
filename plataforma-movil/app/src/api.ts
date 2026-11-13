import { Platform } from "react-native";

const urlPorDefecto =
  process.env.EXPO_PUBLIC_API_URL ||
  (Platform.OS === "android" ? "http://10.0.2.2:8080/api" : "http://localhost:8080/api");

let servidor = urlPorDefecto;
let token = "";

export type Usuario = { id: number; usuario: string };

export type Campo = {
  campo: string;
  etiqueta: string;
  tipo: "texto" | "opciones" | "entero" | "precio";
  max?: number;
  opciones?: string[];
};

export type Categoria = { clave: string; nombre: string; color: string; campos: Campo[] };

export type Producto = {
  id: number;
  referencia: string;
  descripcion: string;
  stock: number;
  precioDetal: number;
  precioMayorista: number;
  precioImportador: number;
  [clave: string]: string | number;
};

export type ResumenPedido = {
  id: number;
  cliente: string;
  documento: string | null;
  tipoVenta: string;
  total: number;
  fecha: string;
  usuario: string;
};

export const obtenerServidor = () => servidor;
export const cambiarServidor = (url: string) => {
  servidor = url.trim().replace(/\/+$/, "");
};
export const cambiarToken = (nuevo: string) => {
  token = nuevo;
};

export async function pedir<T>(metodo: string, ruta: string, cuerpo?: unknown): Promise<T> {
  let respuesta: Response;
  try {
    respuesta = await fetch(servidor + ruta, {
      method: metodo,
      headers: {
        "Content-Type": "application/json",
        ...(token ? { Authorization: `Bearer ${token}` } : {}),
      },
      body: cuerpo === undefined ? undefined : JSON.stringify(cuerpo),
    });
  } catch {
    throw new Error(`No se pudo conectar con el servidor (${servidor}). Revisa que el backend esté encendido y la dirección sea correcta.`);
  }
  const datos = await respuesta.json().catch(() => ({}));
  if (!respuesta.ok) throw new Error((datos as { error?: string }).error || `Error ${respuesta.status}`);
  return datos as T;
}

export const urlRecibo = (idPedido: number) => `${servidor}/pedidos/${idPedido}/recibo.pdf?token=${encodeURIComponent(token)}`;
