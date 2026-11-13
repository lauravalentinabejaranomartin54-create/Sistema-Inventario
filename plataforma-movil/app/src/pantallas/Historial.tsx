import React, { useEffect, useState } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import { ResumenPedido, pedir } from "../api";
import { Cargando, Encabezado, Pantalla, avisar } from "../componentes";
import { abrirRecibo } from "../recibo";
import { colores, moneda } from "../tema";

function fecha(texto: string) {
  const d = new Date(texto);
  const dos = (n: number) => String(n).padStart(2, "0");
  return `${dos(d.getDate())}/${dos(d.getMonth() + 1)}/${d.getFullYear()} ${dos(d.getHours())}:${dos(d.getMinutes())}`;
}

export default function Historial({ onVolver }: { onVolver: () => void }) {
  const [pedidos, setPedidos] = useState<ResumenPedido[] | null>(null);

  useEffect(() => {
    pedir<ResumenPedido[]>("GET", "/pedidos").then(setPedidos).catch((e) => { avisar("Error", (e as Error).message); setPedidos([]); });
  }, []);

  return (
    <Pantalla>
      <Encabezado titulo="Pedidos" subtitulo="Toca un pedido para ver su recibo en PDF" onVolver={onVolver} />
      {pedidos === null ? <Cargando /> : pedidos.length === 0 ? <Text style={s.vacio}>Todavía no hay pedidos</Text> : pedidos.map((p) => (
        <Pressable key={p.id} onPress={() => abrirRecibo(p.id).catch((e) => avisar("No se pudo abrir el PDF", (e as Error).message))}
          style={({ pressed }) => [s.item, pressed && { backgroundColor: colores.lilaClaro }]}>
          <View style={s.numero}><Text style={s.numeroTexto}>N.º {p.id}</Text></View>
          <View style={{ flex: 1 }}>
            <Text style={s.cliente}>{p.cliente}</Text>
            <Text style={s.detalle}>{fecha(p.fecha)} · {p.tipoVenta} · por {p.usuario}</Text>
          </View>
          <View style={{ alignItems: "flex-end" }}>
            <Text style={s.total}>{moneda(p.total)}</Text>
            <Text style={s.pdf}>Ver PDF ›</Text>
          </View>
        </Pressable>
      ))}
    </Pantalla>
  );
}

const s = StyleSheet.create({
  item: { flexDirection: "row", alignItems: "center", gap: 12, backgroundColor: colores.blanco, borderRadius: 18, padding: 14, marginBottom: 10, borderWidth: 1, borderColor: colores.borde },
  numero: { backgroundColor: colores.lila, borderRadius: 12, paddingVertical: 8, paddingHorizontal: 10 },
  numeroTexto: { color: colores.moradoOscuro, fontWeight: "800" },
  cliente: { color: colores.texto, fontWeight: "800", fontSize: 16 },
  detalle: { color: colores.textoSuave, fontSize: 13, marginTop: 2 },
  total: { color: colores.rosaFuerte, fontWeight: "900", fontSize: 16 },
  pdf: { color: colores.morado, fontWeight: "700", fontSize: 13, marginTop: 2 },
  vacio: { textAlign: "center", color: colores.textoSuave, padding: 24 },
});
