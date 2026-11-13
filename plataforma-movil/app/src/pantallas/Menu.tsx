import React from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import { Usuario } from "../api";
import { Pantalla } from "../componentes";
import { colores, fuenteDecorativa } from "../tema";

const categorias = [
  { clave: "tecnologia", nombre: "Tecnología", detalle: "Referencias, marcas y precios", color: "#8E62C4" },
  { clave: "ropa", nombre: "Ropa", detalle: "Tipos, tallas y curvas", color: "#D45D93" },
  { clave: "peluches", nombre: "Peluches", detalle: "Colores y tamaños", color: "#8C7BD1" },
  { clave: "portarretratos", nombre: "Portarretratos", detalle: "Referencias y precios", color: "#B5699F" },
];

export default function Menu({ usuario, onCategoria, onPedido, onHistorial, onSalir }: {
  usuario: Usuario; onCategoria: (clave: string) => void; onPedido: () => void; onHistorial: () => void; onSalir: () => void;
}) {
  return (
    <Pantalla>
      <Text style={s.titulo}>Inventario por categorías</Text>
      <Text style={s.saludo}>Hola, {usuario.usuario}. ¿Qué quieres gestionar hoy?</Text>
      <View style={s.grilla}>
        {categorias.map((c) => (
          <Pressable key={c.clave} onPress={() => onCategoria(c.clave)} accessibilityRole="button"
            style={({ pressed }) => [s.tarjeta, { backgroundColor: c.color, opacity: pressed ? 0.88 : 1 }]}>
            <Text style={s.nombre}>{c.nombre}</Text>
            <Text style={s.detalle}>{c.detalle}</Text>
          </Pressable>
        ))}
      </View>
      <Pressable onPress={onPedido} accessibilityRole="button" style={({ pressed }) => [s.pedido, { opacity: pressed ? 0.88 : 1 }]}>
        <Text style={s.pedidoTitulo}>Nuevo pedido / recibo</Text>
        <Text style={s.detalle}>Elige productos, firma y descarga el PDF</Text>
      </Pressable>
      <View style={s.fila}>
        <Pressable onPress={onHistorial} style={s.secundario}><Text style={s.secundarioTexto}>Pedidos anteriores</Text></Pressable>
        <Pressable onPress={onSalir} style={s.secundario}><Text style={s.secundarioTexto}>Cerrar sesión</Text></Pressable>
      </View>
    </Pantalla>
  );
}

const s = StyleSheet.create({
  titulo: { fontFamily: fuenteDecorativa, fontSize: 40, color: colores.moradoOscuro, textAlign: "center", marginTop: 16 },
  saludo: { color: colores.textoSuave, fontSize: 16, textAlign: "center", marginBottom: 22 },
  grilla: { flexDirection: "row", flexWrap: "wrap", gap: 14 },
  tarjeta: { flexGrow: 1, flexBasis: "45%", minHeight: 130, borderRadius: 26, padding: 18, justifyContent: "center", alignItems: "center" },
  nombre: { fontFamily: fuenteDecorativa, fontSize: 32, color: "#fff" },
  detalle: { color: "#fff", fontWeight: "600", fontSize: 13, textAlign: "center", marginTop: 4, opacity: 0.95 },
  pedido: { marginTop: 14, borderRadius: 26, padding: 22, alignItems: "center", backgroundColor: colores.moradoOscuro },
  pedidoTitulo: { color: "#fff", fontSize: 22, fontWeight: "800" },
  fila: { flexDirection: "row", gap: 12, marginTop: 14 },
  secundario: { flex: 1, borderRadius: 16, paddingVertical: 14, alignItems: "center", backgroundColor: colores.lila },
  secundarioTexto: { color: colores.texto, fontWeight: "700" },
});
