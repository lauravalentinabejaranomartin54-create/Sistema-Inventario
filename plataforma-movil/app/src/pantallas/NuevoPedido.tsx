import React, { useEffect, useMemo, useRef, useState } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import { Categoria, Producto, pedir } from "../api";
import { Boton, Cargando, Campo, Encabezado, Opciones, Pantalla, Tarjeta, avisar, estilos } from "../componentes";
import PanelFirma, { ControlFirma } from "../PanelFirma";
import { abrirRecibo } from "../recibo";
import { colores, moneda } from "../tema";

type Item = { categoria: string; producto: Producto; cantidad: number };
const TIPOS = ["Al detal", "Al por mayor"];

export default function NuevoPedido({ categorias, onVolver }: { categorias: Categoria[]; onVolver: () => void }) {
  const [cliente, setCliente] = useState("");
  const [documento, setDocumento] = useState("");
  const [tipoVenta, setTipoVenta] = useState(TIPOS[0]);
  const [claveCategoria, setClaveCategoria] = useState(categorias[0]?.clave ?? "tecnologia");
  const [productos, setProductos] = useState<Producto[] | null>(null);
  const [seleccionado, setSeleccionado] = useState<Producto | null>(null);
  const [cantidad, setCantidad] = useState("1");
  const [items, setItems] = useState<Item[]>([]);
  const [firmando, setFirmando] = useState(false);
  const [enviando, setEnviando] = useState(false);
  const firma = useRef<ControlFirma>(null);

  useEffect(() => {
    setProductos(null);
    setSeleccionado(null);
    pedir<Producto[]>("GET", `/inventario/${claveCategoria}`)
      .then(setProductos)
      .catch((e) => { avisar("Error", (e as Error).message); setProductos([]); });
  }, [claveCategoria]);

  const precio = (p: Producto) => (tipoVenta === "Al por mayor" ? p.precioMayorista : p.precioDetal);
  const enPedido = (cat: string, id: number) => items.find((i) => i.categoria === cat && i.producto.id === id)?.cantidad ?? 0;
  const total = useMemo(() => items.reduce((s, i) => s + precio(i.producto) * i.cantidad, 0), [items, tipoVenta]);
  const nombreCategoria = categorias.find((c) => c.clave === claveCategoria)?.nombre ?? "";
  const colorCategoria = categorias.find((c) => c.clave === claveCategoria)?.color ?? colores.morado;

  function agregar() {
    if (!seleccionado) return avisar("Falta el producto", "Elige un producto de la lista");
    const n = Number(cantidad);
    if (!Number.isInteger(n) || n <= 0) return avisar("Cantidad no válida", "La cantidad debe ser un número entero mayor que cero");
    const ya = enPedido(claveCategoria, seleccionado.id);
    if (ya + n > seleccionado.stock) {
      return avisar("Stock insuficiente", `Solo hay ${seleccionado.stock} unidades de ${seleccionado.referencia}${ya ? ` (ya agregaste ${ya})` : ""}`);
    }
    setItems((lista) => {
      const i = lista.findIndex((x) => x.categoria === claveCategoria && x.producto.id === seleccionado.id);
      if (i >= 0) return lista.map((x, j) => (j === i ? { ...x, cantidad: x.cantidad + n } : x));
      return [...lista, { categoria: claveCategoria, producto: seleccionado, cantidad: n }];
    });
    setCantidad("1");
  }

  async function confirmarPedido() {
    const datosFirma = firma.current?.obtener();
    if (!cliente.trim()) return avisar("Falta el cliente", "Escribe el nombre del cliente");
    if (!items.length) return avisar("Pedido vacío", "Agrega al menos un producto");
    if (!datosFirma) return avisar("Falta la firma", "El cliente debe firmar el pedido");
    setEnviando(true);
    try {
      const r = await pedir<{ id: number; total: number }>("POST", "/pedidos", {
        cliente, documento, tipoVenta, firma: datosFirma,
        items: items.map((i) => ({ categoria: i.categoria, productoId: i.producto.id, cantidad: i.cantidad })),
      });
      avisar("¡Pedido registrado!", `Pedido N.º ${r.id} por ${moneda(r.total)}. Se descontó el stock. Ahora se abre el recibo en PDF.`);
      await abrirRecibo(r.id).catch((e) => avisar("No se pudo abrir el PDF", (e as Error).message));
      setItems([]);
      setCliente("");
      setDocumento("");
      firma.current?.limpiar();
      setProductos(null);
      pedir<Producto[]>("GET", `/inventario/${claveCategoria}`).then(setProductos).catch(() => setProductos([]));
    } catch (e) {
      avisar("No se pudo registrar el pedido", (e as Error).message);
    } finally {
      setEnviando(false);
    }
  }

  return (
    <Pantalla desplazable={!firmando}>
      <Encabezado titulo="Nuevo pedido" subtitulo="Elige productos, pide la firma y descarga el recibo" onVolver={onVolver} />

      <Tarjeta>
        <Campo etiqueta="Cliente" value={cliente} onChangeText={setCliente} placeholder="Nombre de quien recibe" />
        <Campo etiqueta="Documento (opcional)" value={documento} onChangeText={setDocumento} placeholder="Cédula o NIT" keyboardType="number-pad" />
        <Opciones etiqueta="Tipo de venta" opciones={TIPOS} valor={tipoVenta} onCambio={setTipoVenta} />
      </Tarjeta>

      <Tarjeta>
        <Opciones etiqueta="Categoría" opciones={categorias.map((c) => c.nombre)} valor={nombreCategoria}
          onCambio={(n) => setClaveCategoria(categorias.find((c) => c.nombre === n)?.clave ?? claveCategoria)} color={colorCategoria} />
        <Text style={estilos.etiqueta}>Producto</Text>
        {productos === null ? <Cargando texto="Cargando productos..." /> : (
          <View style={s.lista}>
            {productos.length === 0 && <Text style={s.vacio}>No hay productos en esta categoría</Text>}
            {productos.map((p) => {
              const disponibles = p.stock - enPedido(claveCategoria, p.id);
              const activo = seleccionado?.id === p.id;
              return (
                <Pressable key={p.id} onPress={() => setSeleccionado(p)} disabled={disponibles <= 0}
                  style={[s.producto, activo && { borderColor: colorCategoria, backgroundColor: colores.lilaClaro }, disponibles <= 0 && { opacity: 0.45 }]}>
                  <View style={{ flex: 1 }}>
                    <Text style={s.ref}>{p.referencia}</Text>
                    <Text style={s.desc}>{p.descripcion}</Text>
                  </View>
                  <View style={{ alignItems: "flex-end" }}>
                    <Text style={s.precio}>{moneda(precio(p))}</Text>
                    <Text style={s.disp}>{disponibles} disp.</Text>
                  </View>
                </Pressable>
              );
            })}
          </View>
        )}
        <View style={s.filaAgregar}>
          <View style={{ width: 110 }}>
            <Campo etiqueta="Cantidad" value={cantidad} onChangeText={setCantidad} keyboardType="number-pad" />
          </View>
          <Boton texto="Agregar al pedido" onPress={agregar} color={colorCategoria} style={{ flex: 1, marginTop: 10 }} />
        </View>
      </Tarjeta>

      <Tarjeta>
        <Text style={s.seccion}>Productos del pedido</Text>
        {items.length === 0 && <Text style={s.vacio}>Todavía no agregas productos</Text>}
        {items.map((i, idx) => (
          <View key={`${i.categoria}-${i.producto.id}`} style={s.item}>
            <View style={{ flex: 1 }}>
              <Text style={s.desc}>{i.cantidad} × {i.producto.descripcion}</Text>
              <Text style={s.ref}>{i.producto.referencia} · {moneda(precio(i.producto))} c/u</Text>
            </View>
            <Text style={s.subtotal}>{moneda(precio(i.producto) * i.cantidad)}</Text>
            <Pressable onPress={() => setItems((l) => l.filter((_, j) => j !== idx))} style={s.quitar} accessibilityLabel="Quitar producto">
              <Text style={s.quitarTexto}>✕</Text>
            </Pressable>
          </View>
        ))}
        <View style={s.totalCaja}>
          <Text style={s.totalTexto}>Total</Text>
          <Text style={s.totalValor}>{moneda(total)}</Text>
        </View>
      </Tarjeta>

      <Tarjeta>
        <View style={s.firmaTitulo}>
          <Text style={s.seccion}>Firma del cliente</Text>
          <Pressable onPress={() => firma.current?.limpiar()}><Text style={s.borrar}>Borrar firma</Text></Pressable>
        </View>
        <PanelFirma ref={firma} onFirmando={setFirmando} />
      </Tarjeta>

      <Boton texto="Confirmar pedido y descargar recibo" onPress={confirmarPedido} cargando={enviando} color={colores.rosaFuerte} />
    </Pantalla>
  );
}

const s = StyleSheet.create({
  lista: { gap: 8, marginBottom: 6 },
  producto: { flexDirection: "row", alignItems: "center", gap: 10, borderWidth: 1.5, borderColor: colores.borde, borderRadius: 14, padding: 12, backgroundColor: colores.blanco },
  ref: { color: colores.textoSuave, fontSize: 12, fontWeight: "700" },
  desc: { color: colores.texto, fontWeight: "700", fontSize: 15 },
  precio: { color: colores.moradoOscuro, fontWeight: "800" },
  disp: { color: colores.textoSuave, fontSize: 12 },
  filaAgregar: { flexDirection: "row", gap: 12, alignItems: "center" },
  seccion: { color: colores.moradoOscuro, fontWeight: "800", fontSize: 17, marginBottom: 8 },
  vacio: { color: colores.textoSuave, paddingVertical: 8 },
  item: { flexDirection: "row", alignItems: "center", gap: 10, paddingVertical: 10, borderBottomWidth: 1, borderBottomColor: colores.lila },
  subtotal: { color: colores.texto, fontWeight: "800" },
  quitar: { width: 32, height: 32, borderRadius: 16, backgroundColor: colores.rosado, alignItems: "center", justifyContent: "center" },
  quitarTexto: { color: colores.rojo, fontWeight: "800" },
  totalCaja: { flexDirection: "row", justifyContent: "space-between", alignItems: "center", marginTop: 12, backgroundColor: "#FBE3EF", borderRadius: 14, padding: 14 },
  totalTexto: { color: colores.moradoOscuro, fontWeight: "800", fontSize: 18 },
  totalValor: { color: colores.rosaFuerte, fontWeight: "900", fontSize: 24 },
  firmaTitulo: { flexDirection: "row", justifyContent: "space-between", alignItems: "center" },
  borrar: { color: colores.rosaFuerte, fontWeight: "700" },
});
