import React, { forwardRef, useImperativeHandle, useRef, useState } from "react";
import { GestureResponderEvent, PanResponder, StyleSheet, Text, View } from "react-native";
import Svg, { Line, Path } from "react-native-svg";
import { colores } from "./tema";

export type Firma = { ancho: number; alto: number; trazos: number[][][] };
export type ControlFirma = { limpiar: () => void; obtener: () => Firma | null };

const ALTO = 170;

function aRuta(trazo: number[][]) {
  return trazo.map((p, i) => `${i === 0 ? "M" : "L"}${p[0].toFixed(1)} ${p[1].toFixed(1)}`).join(" ");
}

const PanelFirma = forwardRef<ControlFirma, { onFirmando?: (activo: boolean) => void }>(({ onFirmando }, ref) => {
  const [trazos, setTrazos] = useState<number[][][]>([]);
  const actual = useRef<number[][]>([]);
  const [, refrescar] = useState(0);
  const ancho = useRef(320);

  const punto = (e: GestureResponderEvent) => {
    const { locationX, locationY } = e.nativeEvent;
    return [Math.max(0, Math.min(ancho.current, locationX)), Math.max(0, Math.min(ALTO, locationY))];
  };

  const responder = useRef(PanResponder.create({
    onStartShouldSetPanResponder: () => true,
    onStartShouldSetPanResponderCapture: () => true,
    onMoveShouldSetPanResponder: () => true,
    onMoveShouldSetPanResponderCapture: () => true,
    onPanResponderTerminationRequest: () => false,
    onPanResponderGrant: (e) => {
      onFirmando?.(true);
      actual.current = [punto(e)];
      refrescar((n) => n + 1);
    },
    onPanResponderMove: (e) => {
      actual.current.push(punto(e));
      refrescar((n) => n + 1);
    },
    onPanResponderRelease: () => {
      const terminado = actual.current;
      actual.current = [];
      if (terminado.length > 1) setTrazos((t) => [...t, terminado]);
      onFirmando?.(false);
    },
    onPanResponderTerminate: () => {
      actual.current = [];
      onFirmando?.(false);
    },
  })).current;

  useImperativeHandle(ref, () => ({
    limpiar: () => setTrazos([]),
    obtener: () => (trazos.length ? { ancho: Math.round(ancho.current), alto: ALTO, trazos } : null),
  }), [trazos]);

  const vacio = trazos.length === 0 && actual.current.length < 2;

  return (
    <View
      style={s.caja}
      onLayout={(e) => { ancho.current = e.nativeEvent.layout.width; }}
      {...responder.panHandlers}
      accessibilityLabel="Espacio para la firma del cliente">
      <Svg width="100%" height={ALTO} pointerEvents="none">
        <Line x1="20" y1={ALTO - 36} x2="96%" y2={ALTO - 36} stroke={colores.borde} strokeWidth={1.5} strokeDasharray="6 6" />
        {trazos.map((t, i) => (
          <Path key={i} d={aRuta(t)} stroke="#2B1D38" strokeWidth={2.6} fill="none" strokeLinecap="round" strokeLinejoin="round" />
        ))}
        {actual.current.length > 1 && (
          <Path d={aRuta(actual.current)} stroke="#2B1D38" strokeWidth={2.6} fill="none" strokeLinecap="round" strokeLinejoin="round" />
        )}
      </Svg>
      {vacio && <Text style={s.ayuda} pointerEvents="none">Firme aquí con el dedo</Text>}
    </View>
  );
});

export default PanelFirma;

const s = StyleSheet.create({
  caja: { height: ALTO, backgroundColor: colores.blanco, borderRadius: 18, borderWidth: 1.5, borderColor: colores.borde, overflow: "hidden", cursor: "crosshair" } as object,
  ayuda: { position: "absolute", alignSelf: "center", top: ALTO / 2 - 20, color: colores.textoSuave, fontSize: 15 },
});
