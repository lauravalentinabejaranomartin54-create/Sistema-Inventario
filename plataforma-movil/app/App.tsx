import React, { useEffect, useState } from "react";
import { BackHandler, SafeAreaView, StyleSheet, View } from "react-native";
import { StatusBar } from "expo-status-bar";
import { DancingScript_700Bold, useFonts } from "@expo-google-fonts/dancing-script";
import { Categoria, Producto, Usuario, cambiarToken, pedir } from "./src/api";
import { Cargando, avisar } from "./src/componentes";
import { colores } from "./src/tema";
import Login from "./src/pantallas/Login";
import Menu from "./src/pantallas/Menu";
import ListaCategoria from "./src/pantallas/ListaCategoria";
import FormularioProducto from "./src/pantallas/FormularioProducto";
import NuevoPedido from "./src/pantallas/NuevoPedido";
import Historial from "./src/pantallas/Historial";

type Ruta =
  | { nombre: "menu" }
  | { nombre: "categoria"; clave: string }
  | { nombre: "formulario"; clave: string; producto?: Producto }
  | { nombre: "pedido" }
  | { nombre: "historial" };

export default function App() {
  const [fuentesListas] = useFonts({ DancingScript_700Bold });
  const [usuario, setUsuario] = useState<Usuario | null>(null);
  const [categorias, setCategorias] = useState<Categoria[]>([]);
  const [pila, setPila] = useState<Ruta[]>([{ nombre: "menu" }]);
  const [version, setVersion] = useState(0);

  const ir = (r: Ruta) => setPila((p) => [...p, r]);
  const volver = () => setPila((p) => (p.length > 1 ? p.slice(0, -1) : p));

  useEffect(() => {
    const sub = BackHandler.addEventListener("hardwareBackPress", () => {
      if (usuario && pila.length > 1) {
        volver();
        return true;
      }
      return false;
    });
    return () => sub.remove();
  }, [usuario, pila.length]);

  async function entrar(u: Usuario) {
    try {
      setCategorias(await pedir<Categoria[]>("GET", "/inventario/categorias"));
      setUsuario(u);
      setPila([{ nombre: "menu" }]);
    } catch (e) {
      avisar("Error", (e as Error).message);
    }
  }

  function salir() {
    cambiarToken("");
    setUsuario(null);
    setPila([{ nombre: "menu" }]);
  }

  const ruta = pila[pila.length - 1];
  const categoria = (clave: string) => categorias.find((c) => c.clave === clave)!;

  let contenido: React.ReactNode;
  if (!fuentesListas) contenido = <Cargando texto="Preparando la app..." />;
  else if (!usuario) contenido = <Login onEntrar={entrar} />;
  else if (ruta.nombre === "categoria") {
    contenido = (
      <ListaCategoria categoria={categoria(ruta.clave)} version={version} onVolver={volver}
        onNuevo={() => ir({ nombre: "formulario", clave: ruta.clave })}
        onEditar={(p) => ir({ nombre: "formulario", clave: ruta.clave, producto: p })} />
    );
  } else if (ruta.nombre === "formulario") {
    contenido = (
      <FormularioProducto categoria={categoria(ruta.clave)} producto={ruta.producto} onVolver={volver}
        onGuardado={() => { setVersion((v) => v + 1); volver(); }} />
    );
  } else if (ruta.nombre === "pedido") contenido = <NuevoPedido categorias={categorias} onVolver={volver} />;
  else if (ruta.nombre === "historial") contenido = <Historial onVolver={volver} />;
  else {
    contenido = (
      <Menu usuario={usuario} onCategoria={(clave) => ir({ nombre: "categoria", clave })}
        onPedido={() => ir({ nombre: "pedido" })} onHistorial={() => ir({ nombre: "historial" })} onSalir={salir} />
    );
  }

  return (
    <SafeAreaView style={s.raiz}>
      <StatusBar style="dark" />
      <View style={s.raiz}>{contenido}</View>
    </SafeAreaView>
  );
}

const s = StyleSheet.create({
  raiz: { flex: 1, backgroundColor: colores.fondo },
});
