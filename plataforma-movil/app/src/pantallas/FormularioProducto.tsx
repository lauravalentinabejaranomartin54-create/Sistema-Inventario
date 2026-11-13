import React, { useState } from "react";
import { View } from "react-native";
import { Categoria, Producto, pedir } from "../api";
import { Boton, Campo, Encabezado, Opciones, Pantalla, Tarjeta, avisar, confirmar } from "../componentes";
import { colores } from "../tema";

export default function FormularioProducto({ categoria, producto, onVolver, onGuardado }: {
  categoria: Categoria; producto?: Producto; onVolver: () => void; onGuardado: () => void;
}) {
  const [valores, setValores] = useState<Record<string, string>>(() => {
    const inicial: Record<string, string> = {};
    for (const c of categoria.campos) inicial[c.campo] = producto ? String(producto[c.campo] ?? "") : "";
    return inicial;
  });
  const [guardando, setGuardando] = useState(false);
  const editando = Boolean(producto);

  const cambiar = (campo: string, valor: string) => setValores((v) => ({ ...v, [campo]: valor }));

  async function guardar() {
    setGuardando(true);
    try {
      if (editando) await pedir("PUT", `/inventario/${categoria.clave}/${producto!.id}`, valores);
      else await pedir("POST", `/inventario/${categoria.clave}`, valores);
      avisar("Listo", editando ? "Producto actualizado correctamente" : "Producto guardado correctamente");
      onGuardado();
    } catch (e) {
      avisar("Revisa los datos", (e as Error).message);
    } finally {
      setGuardando(false);
    }
  }

  async function eliminar() {
    if (!(await confirmar("Eliminar producto", `¿Seguro que deseas eliminar ${producto!.referencia}? Esta acción no se puede deshacer.`))) return;
    try {
      await pedir("DELETE", `/inventario/${categoria.clave}/${producto!.id}`);
      avisar("Listo", "Producto eliminado");
      onGuardado();
    } catch (e) {
      avisar("Error", (e as Error).message);
    }
  }

  return (
    <Pantalla>
      <Encabezado titulo={editando ? "Editar producto" : "Nuevo producto"} subtitulo={categoria.nombre} color={categoria.color} onVolver={onVolver} />
      <Tarjeta>
        {categoria.campos.map((c) =>
          c.tipo === "opciones" ? (
            <Opciones key={c.campo} etiqueta={c.etiqueta} opciones={c.opciones ?? []} valor={valores[c.campo]} onCambio={(v) => cambiar(c.campo, v)} color={categoria.color} />
          ) : (
            <Campo
              key={c.campo}
              etiqueta={c.etiqueta}
              value={valores[c.campo]}
              onChangeText={(v) => cambiar(c.campo, v)}
              keyboardType={c.tipo === "entero" ? "number-pad" : c.tipo === "precio" ? "decimal-pad" : "default"}
              placeholder={c.tipo === "precio" ? "Ejemplo: 25000 o 25.000" : c.tipo === "entero" ? "0" : ""}
              maxLength={c.max}
            />
          ))}
      </Tarjeta>
      <Boton texto={editando ? "Guardar cambios" : "Guardar"} onPress={guardar} cargando={guardando} color={categoria.color} />
      {editando && (
        <View style={{ marginTop: 12 }}>
          <Boton texto="Eliminar producto" onPress={eliminar} color={colores.rojo} />
        </View>
      )}
    </Pantalla>
  );
}
