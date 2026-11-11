# Video de sustentación (Remotion)

Video del Sistema de Inventario funcionando, hecho con [Remotion](https://www.remotion.dev/) (videos escritos en React).

Las imágenes de `public/capturas/` son capturas reales del programa: se tomaron manejando la aplicación
(registro, inicio de sesión, guardar, actualizar, buscar, validaciones y eliminar) contra la base MySQL de XAMPP.

## Comandos

Instalar (solo la primera vez):

```bash
npm install
```

Abrir el editor para ver y ajustar el video en el navegador:

```bash
npm run studio
```

Generar el MP4 en `out/sistema-inventario.mp4`:

```bash
npm run render
```

## Dónde cambiar cosas

| Quiero cambiar… | Archivo |
| --- | --- |
| Textos de cada paso, zoom y cursor | `src/data.ts` |
| Colores y fuentes | `src/theme.ts` |
| Diseño de las escenas (intro, arquitectura, base de datos, resultados, cierre) | `src/scenes.tsx` |
| Orden y duración de las escenas | `src/Root.tsx` |
