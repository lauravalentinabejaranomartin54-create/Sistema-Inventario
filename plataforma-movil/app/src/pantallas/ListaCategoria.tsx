import React, { useCallback, useEffect, useMemo, useState } from "react";
import { Pressable, StyleSheet, Text, TextInput, View } from "react-native";
import { Categoria, Producto, pedir } from "../api";
import { Boton, Cargando, Encabezado, Pantalla, avisar, estilos } from "../componentes";
import { colores, moneda } from "../tema";

export default function ListaCategoria({ categoria, onVolver, onNuevo, onEditar, version }: {
  categoria: Categoria; onVolver: () => void; onNuevo: () => void; onEditar: (p: Producto) => void; version: number;
}) {
  const [productos, setProductos] = useState<Producto[] | null>(null);
  const [busqueda, setBusqueda] = useState("");

  const cargar = useCallback(async () => {
    try {
      setProductos(await pedir<Producto[]>("GET", `/inventario/${categoria.clave}`));
    } catch (e) {
      avisar("Error", (e as Error).message);
      setProductos([]);
    }
  }, [categoria.clave]);

  useEffect(() => { cargar(); }, [cargar, version]);

  const filtrados = useMemo(() => {
    const t = busqueda.trim().toLowerCase();
    if (!productos || !t) return productos ?? [];
    return productos.filter((p) => Object.values(p).some((v) => String(v).toLowerCase().includes(t)));
  }, [productos, busqueda]);

  return (
    <Pantalla>
      <Encabezado titulo={categoria.nombre} subtitulo="Registra, consulta, actualiza y elimina productos" color={categoria.color} onVolver={onVolver} />
      <View style={s.barra}>
        <TextInput value={busqueda} onChangeText={setBusqueda} placeholder="Buscar por referencia, marca, color..." placeholderTextColor={colores.textoSuave} style={[estilos.campo, { flex: 1 }]} />
      </View>
      <Boton texto="+ Nuevo producto" onPress={onNuevo} color={categoria.color} style={{ marginBottom: 14 }} />
      {productos === null ? <Cargando /> : (
        <>
          <Text style={s.total}>{filtrados.length} {filtrados.length === 1 ? "producto" : "productos"}</Text>
          {filtrados.map((p) => (
            <Pressable key={p.id} onPress={() => onEditar(p)} style={({ pressed }) => [s.item, pressed && { backgroundColor: colores.lilaClaro }]}>
              <View style={[s.franja, { backgroundColor: categoria.color }]} />
              <View style={{ flex: 1 }}>
                <Text style={s.ref}>{p.referencia}</Text>
                <Text style={s.desc}>{p.descripcion}</Text>
                <Text style={s.carac} numberOfLines={1}>{String(p.caracteristica ?? "")}</Text>
                <View style={s.precios}>
                  <Text style={s.precio}>Detal {moneda(p.precioDetal)}</Text>
                  <Text style={s.precioSuave}>Mayor {moneda(p.precioMayorista)}</Text>
                </View>
              </View>
              <View style={s.stockCaja}>
                <Text style={[s.stock, p.stock <= 3 && { color: colores.rosaFuerte }]}>{p.stock}</Text>
                <Text style={s.stockTexto}>unid.</Text>
                {categoria.clave === "ropa" && <Text style={s.curvas}>{String(p.stockCurvas)} curvas</Text>}
              </View>
            </Pressable>
          ))}
          {filtrados.length === 0 && <Text style={s.vacio}>{busqueda ? "No hay productos que coincidan con la búsqueda" : "Todavía no hay productos en esta categoría"}</Text>}
        </>
      )}
    </Pantalla>
  );
}

const s = StyleSheet.create({
  barra: { flexDirection: "row", marginBottom: 12 },
  total: { color: colores.textoSuave, fontWeight: "700", marginBottom: 8 },
  item: {
    flexDirection: "row", gap: 12, backgroundColor: colores.blanco, borderRadius: 18, padding: 14, marginBottom: 10,
    borderWidth: 1, borderColor: colores.borde, alignItems: "center",
  },
  franja: { width: 6, alignSelf: "stretch", borderRadius: 6 },
  ref: { color: colores.textoSuave, fontWeight: "700", fontSize: 12 },
  desc: { color: colores.texto, fontWeight: "700", fontSize: 16 },
  carac: { color: colores.textoSuave, fontSize: 13, marginTop: 2 },
  precios: { flexDirection: "row", gap: 12, marginTop: 6 },
  precio: { color: colores.moradoOscuro, fontWeight: "700" },
  precioSuave: { color: colores.textoSuave },
  stockCaja: { alignItems: "center", minWidth: 64 },
  stock: { fontSize: 24, fontWeight: "800", color: colores.morado },
  stockTexto: { color: colores.textoSuave, fontSize: 12 },
  curvas: { color: colores.rosaFuerte, fontSize: 12, fontWeight: "700", marginTop: 2 },
  vacio: { textAlign: "center", color: colores.textoSuave, padding: 24 },
});
