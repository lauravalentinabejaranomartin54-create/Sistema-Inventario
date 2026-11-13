import React, { useState } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import { Usuario, cambiarServidor, cambiarToken, obtenerServidor, pedir } from "../api";
import { Boton, Campo, Pantalla, Tarjeta, avisar } from "../componentes";
import { colores, fuenteDecorativa } from "../tema";

export default function Login({ onEntrar }: { onEntrar: (u: Usuario) => void }) {
  const [modo, setModo] = useState<"login" | "registro">("login");
  const [usuario, setUsuario] = useState("");
  const [contrasena, setContrasena] = useState("");
  const [confirmacion, setConfirmacion] = useState("");
  const [servidor, setServidor] = useState(obtenerServidor());
  const [verServidor, setVerServidor] = useState(false);
  const [cargando, setCargando] = useState(false);

  async function enviar() {
    cambiarServidor(servidor);
    setCargando(true);
    try {
      if (modo === "registro") {
        await pedir("POST", "/auth/registro", { usuario, contrasena, confirmacion });
        avisar("¡Cuenta creada!", "Ya puedes iniciar sesión.");
        setModo("login");
        setContrasena("");
        setConfirmacion("");
      } else {
        const r = await pedir<{ token: string; usuario: Usuario }>("POST", "/auth/login", { usuario, contrasena });
        cambiarToken(r.token);
        onEntrar(r.usuario);
      }
    } catch (e) {
      avisar(modo === "login" ? "No se pudo iniciar sesión" : "No se pudo crear la cuenta", (e as Error).message);
      setContrasena("");
    } finally {
      setCargando(false);
    }
  }

  return (
    <Pantalla>
      <View style={s.logo}>
        <Text style={s.marca}>Mi Inventario</Text>
        <Text style={s.lema}>{modo === "login" ? "Inicia sesión para continuar" : "Crea tu cuenta para administrar el inventario"}</Text>
      </View>
      <Tarjeta>
        <Campo etiqueta="Usuario" value={usuario} onChangeText={setUsuario} autoCapitalize="none" autoCorrect={false} placeholder="Tu usuario" />
        <Campo etiqueta={modo === "registro" ? "Contraseña (mínimo 6 caracteres)" : "Contraseña"} value={contrasena} onChangeText={setContrasena} secureTextEntry placeholder="••••••" onSubmitEditing={modo === "login" ? enviar : undefined} />
        {modo === "registro" && (
          <Campo etiqueta="Confirmar contraseña" value={confirmacion} onChangeText={setConfirmacion} secureTextEntry placeholder="••••••" />
        )}
        <Boton texto={modo === "login" ? "Ingresar" : "Crear cuenta"} onPress={enviar} cargando={cargando} color={modo === "login" ? colores.morado : colores.rosaFuerte} style={{ marginTop: 6 }} />
        <Pressable onPress={() => setModo(modo === "login" ? "registro" : "login")} style={s.enlace}>
          <Text style={s.enlaceTexto}>{modo === "login" ? "¿No tienes cuenta? Regístrate aquí" : "¿Ya tienes cuenta? Inicia sesión"}</Text>
        </Pressable>
      </Tarjeta>
      <Pressable onPress={() => setVerServidor(!verServidor)}>
        <Text style={s.servidorEnlace}>⚙ Servidor: {servidor}</Text>
      </Pressable>
      {verServidor && (
        <Tarjeta>
          <Campo etiqueta="Dirección del API Gateway" value={servidor} onChangeText={setServidor} autoCapitalize="none" autoCorrect={false} placeholder="http://192.168.1.10:8080/api" />
          <Text style={s.ayuda}>En tu celular escribe la IP de tu computador, por ejemplo http://192.168.1.10:8080/api (el celular y el computador deben estar en la misma red WiFi).</Text>
        </Tarjeta>
      )}
    </Pantalla>
  );
}

const s = StyleSheet.create({
  logo: { alignItems: "center", marginTop: 40, marginBottom: 24 },
  marca: { fontFamily: fuenteDecorativa, fontSize: 52, color: colores.moradoOscuro },
  lema: { color: colores.textoSuave, fontSize: 16, marginTop: 4, textAlign: "center" },
  enlace: { alignItems: "center", paddingVertical: 14 },
  enlaceTexto: { color: colores.rosaFuerte, fontWeight: "700", fontSize: 15 },
  servidorEnlace: { color: colores.textoSuave, textAlign: "center", marginBottom: 12, fontSize: 13 },
  ayuda: { color: colores.textoSuave, fontSize: 13, lineHeight: 18 },
});
