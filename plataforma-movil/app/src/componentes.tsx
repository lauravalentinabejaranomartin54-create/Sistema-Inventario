import React from "react";
import {
  ActivityIndicator, Alert, Platform, Pressable, ScrollView, StyleSheet, Text, TextInput, TextInputProps, View, ViewStyle,
} from "react-native";
import { colores, fuenteDecorativa } from "./tema";

export function avisar(titulo: string, mensaje: string) {
  if (Platform.OS === "web") window.alert(`${titulo}\n\n${mensaje}`);
  else Alert.alert(titulo, mensaje);
}

export function confirmar(titulo: string, mensaje: string): Promise<boolean> {
  if (Platform.OS === "web") return Promise.resolve(window.confirm(`${titulo}\n\n${mensaje}`));
  return new Promise((resolver) =>
    Alert.alert(titulo, mensaje, [
      { text: "No", style: "cancel", onPress: () => resolver(false) },
      { text: "Sí", style: "destructive", onPress: () => resolver(true) },
    ]));
}

export function Pantalla({ children, scroll = true, desplazable = true }: { children: React.ReactNode; scroll?: boolean; desplazable?: boolean }) {
  if (!scroll) return <View style={estilos.pantalla}>{children}</View>;
  return (
    <ScrollView style={estilos.pantalla} contentContainerStyle={estilos.contenido} keyboardShouldPersistTaps="handled" scrollEnabled={desplazable}>
      {children}
    </ScrollView>
  );
}

export function Encabezado({ titulo, subtitulo, color = colores.morado, onVolver }: { titulo: string; subtitulo?: string; color?: string; onVolver?: () => void }) {
  return (
    <View style={estilos.encabezado}>
      {onVolver && (
        <Pressable onPress={onVolver} style={estilos.volver} accessibilityRole="button" accessibilityLabel="Volver">
          <Text style={estilos.volverTexto}>‹ Volver</Text>
        </Pressable>
      )}
      <Text style={[estilos.titulo, { color }]}>{titulo}</Text>
      {subtitulo ? <Text style={estilos.subtitulo}>{subtitulo}</Text> : null}
    </View>
  );
}

export function Tarjeta({ children, style }: { children: React.ReactNode; style?: ViewStyle }) {
  return <View style={[estilos.tarjeta, style]}>{children}</View>;
}

export function Boton({ texto, onPress, color = colores.morado, claro = false, cargando = false, deshabilitado = false, style }: {
  texto: string; onPress: () => void; color?: string; claro?: boolean; cargando?: boolean; deshabilitado?: boolean; style?: ViewStyle;
}) {
  const inactivo = cargando || deshabilitado;
  return (
    <Pressable
      onPress={onPress}
      disabled={inactivo}
      accessibilityRole="button"
      style={({ pressed }) => [estilos.boton, { backgroundColor: claro ? colores.lila : color, opacity: inactivo ? 0.6 : pressed ? 0.85 : 1 }, style]}>
      {cargando ? <ActivityIndicator color={claro ? colores.texto : "#fff"} /> : (
        <Text style={[estilos.botonTexto, claro && { color: colores.texto }]}>{texto}</Text>
      )}
    </Pressable>
  );
}

export function Campo({ etiqueta, ...props }: TextInputProps & { etiqueta: string }) {
  const [enfocado, setEnfocado] = React.useState(false);
  return (
    <View style={estilos.campoCaja}>
      <Text style={estilos.etiqueta}>{etiqueta}</Text>
      <TextInput
        placeholderTextColor={colores.textoSuave}
        {...props}
        onFocus={(e) => { setEnfocado(true); props.onFocus?.(e); }}
        onBlur={(e) => { setEnfocado(false); props.onBlur?.(e); }}
        style={[estilos.campo, enfocado && { borderColor: colores.morado }, props.style]}
      />
    </View>
  );
}

export function Opciones({ etiqueta, opciones, valor, onCambio, color = colores.morado }: {
  etiqueta: string; opciones: string[]; valor: string; onCambio: (v: string) => void; color?: string;
}) {
  return (
    <View style={estilos.campoCaja}>
      <Text style={estilos.etiqueta}>{etiqueta}</Text>
      <View style={estilos.chips}>
        {opciones.map((o) => {
          const activo = o === valor;
          return (
            <Pressable key={o} onPress={() => onCambio(o)} accessibilityRole="button" accessibilityState={{ selected: activo }}
              style={[estilos.chip, activo && { backgroundColor: color, borderColor: color }]}>
              <Text style={[estilos.chipTexto, activo && { color: "#fff" }]}>{o}</Text>
            </Pressable>
          );
        })}
      </View>
    </View>
  );
}

export function Cargando({ texto = "Cargando..." }: { texto?: string }) {
  return (
    <View style={estilos.cargando}>
      <ActivityIndicator color={colores.morado} size="large" />
      <Text style={estilos.subtitulo}>{texto}</Text>
    </View>
  );
}

export const estilos = StyleSheet.create({
  pantalla: { flex: 1, backgroundColor: colores.fondo },
  contenido: { padding: 20, paddingBottom: 48, width: "100%", maxWidth: 760, alignSelf: "center" },
  encabezado: { marginBottom: 16 },
  volver: { alignSelf: "flex-start", paddingVertical: 6, paddingRight: 12, marginBottom: 4 },
  volverTexto: { color: colores.morado, fontSize: 16, fontWeight: "700" },
  titulo: { fontFamily: fuenteDecorativa, fontSize: 38, color: colores.morado },
  subtitulo: { color: colores.textoSuave, fontSize: 15, marginTop: 2 },
  tarjeta: {
    backgroundColor: colores.blanco, borderRadius: 22, padding: 18, marginBottom: 16,
    borderWidth: 1, borderColor: colores.borde,
    shadowColor: colores.moradoOscuro, shadowOpacity: 0.1, shadowRadius: 14, shadowOffset: { width: 0, height: 6 }, elevation: 3,
  },
  boton: { borderRadius: 16, paddingVertical: 14, paddingHorizontal: 18, alignItems: "center", justifyContent: "center", minHeight: 50 },
  botonTexto: { color: "#fff", fontWeight: "700", fontSize: 16 },
  campoCaja: { marginBottom: 12 },
  etiqueta: { color: colores.texto, fontWeight: "700", fontSize: 14, marginBottom: 6 },
  campo: {
    borderWidth: 1.5, borderColor: colores.borde, borderRadius: 14, paddingHorizontal: 14, paddingVertical: 11,
    fontSize: 16, color: colores.texto, backgroundColor: colores.blanco,
  },
  chips: { flexDirection: "row", flexWrap: "wrap", gap: 8 },
  chip: { borderWidth: 1.5, borderColor: colores.borde, borderRadius: 999, paddingVertical: 8, paddingHorizontal: 14, backgroundColor: colores.blanco },
  chipTexto: { color: colores.texto, fontWeight: "600" },
  cargando: { padding: 40, alignItems: "center", gap: 12 },
});
