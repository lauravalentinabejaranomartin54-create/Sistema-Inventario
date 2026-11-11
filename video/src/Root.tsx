import React from "react";
import { Composition } from "remotion";
import { TransitionSeries, linearTiming } from "@remotion/transitions";
import { fade } from "@remotion/transitions/fade";
import { slide } from "@remotion/transitions/slide";
import { capitulos } from "./data";
import { Arquitectura, BaseDatos, Cierre, Intro, PasoEscena, PortadaCapitulo, Resultados, Tecnologias } from "./scenes";
import { FPS, TRANSICION } from "./theme";

type Escena = { id: string; dur: number; el: React.ReactNode; entrada: "fade" | "slide" };

const armarEscenas = (): Escena[] => {
  const lista: Escena[] = [
    { id: "intro", dur: 150, el: <Intro />, entrada: "fade" },
    { id: "tecnologias", dur: 165, el: <Tecnologias />, entrada: "slide" },
    { id: "arquitectura", dur: 210, el: <Arquitectura />, entrada: "slide" },
  ];
  let numero = 0;
  for (const cap of capitulos) {
    lista.push({ id: `cap-${cap.numero}`, dur: 70, el: <PortadaCapitulo cap={cap} />, entrada: "slide" });
    cap.pasos.forEach((paso, i) => {
      numero++;
      const dur = paso.dur ?? (paso.cursor ? 105 : paso.dialog ? 80 : 85);
      lista.push({
        id: `paso-${numero}`,
        dur,
        el: <PasoEscena paso={paso} cap={cap} indice={numero} anterior={cap.pasos[i - 1]} />,
        entrada: "fade",
      });
    });
  }
  lista.push(
    { id: "base-datos", dur: 300, el: <BaseDatos />, entrada: "slide" },
    { id: "resultados", dur: 170, el: <Resultados />, entrada: "slide" },
    { id: "cierre", dur: 150, el: <Cierre />, entrada: "fade" },
  );
  return lista;
};

const escenas = armarEscenas();
const DURACION = escenas.reduce((s, e) => s + e.dur, 0) - TRANSICION * (escenas.length - 1);

export const Video: React.FC = () => (
  <TransitionSeries>
    {escenas.map((e, i) => (
      <React.Fragment key={e.id}>
        {i > 0 && (
          <TransitionSeries.Transition
            presentation={e.entrada === "slide" ? slide({ direction: "from-right" }) : fade()}
            timing={linearTiming({ durationInFrames: TRANSICION })}
          />
        )}
        <TransitionSeries.Sequence durationInFrames={e.dur}>{e.el}</TransitionSeries.Sequence>
      </React.Fragment>
    ))}
  </TransitionSeries>
);

export const RemotionRoot: React.FC = () => (
  <Composition id="Sustentacion" component={Video} durationInFrames={DURACION} fps={FPS} width={1920} height={1080} />
);
