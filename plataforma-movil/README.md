# Mi Inventario — App móvil + microservicios

App para **Android, iOS y web** (un solo código con Expo / React Native) conectada a un backend de **microservicios** en Node.js. Usa la misma base de datos MySQL que la app de escritorio en Java, así que las dos ven el mismo inventario, los mismos usuarios y los mismos pedidos.

## Arquitectura

```
 Celular (Android / iOS)  ─┐
 Navegador (web)          ─┼──►  API Gateway :8080  ──►  auth-service       :4001  (usuarios, inicio de sesión, JWT)
                           │     (única puerta de    ──►  inventario-service :4002  (4 categorías, stock)
                           │      entrada, valida    ──►  pedidos-service    :4003  (pedidos, firma, recibo PDF)
                           │      el token)                     │
                           │                                    └──► llama a inventario-service para descontar
 App de escritorio (Java) ─┴─────────────────────────────►  MySQL / MariaDB  (gestion_inventario)
```

| Servicio | Responsabilidad | Tablas que maneja |
| --- | --- | --- |
| `api-gateway` | Recibe todas las peticiones de la app, valida el token y las envía al servicio correcto. Bloquea las rutas internas. | — |
| `auth-service` | Registro e inicio de sesión. Usa el mismo cifrado PBKDF2 que la app de escritorio. | `usuarios` |
| `inventario-service` | Guardar, listar, actualizar y eliminar productos de las 4 categorías. Describe los campos de cada categoría para que la app arme los formularios sola. | `tecnologia`, `ropa`, `peluches`, `portarretratos` |
| `pedidos-service` | Registra pedidos con la firma del cliente y genera el recibo en PDF. Pide el descuento de stock a `inventario-service`; si algo falla, le pide que lo reponga (patrón *saga*). | `pedidos`, `pedido_detalle` |
| `comun` | Código compartido: conexión a la base, validaciones, tokens y manejo de errores. | — |

## 1. Encender el backend

Requisitos: **Node.js 20+** y **MySQL de XAMPP encendido** con el script `database/gestion_inventario.sql` ya ejecutado.

```bash
cd plataforma-movil/backend
npm install
npm start
```

`npm start` enciende los 4 servicios a la vez. Para comprobar que todo está bien, abre <http://localhost:8080/api/salud>.

Configuración opcional (variables de entorno): `DB_HOST`, `DB_USER`, `DB_PASSWORD`, `DB_NAME`, `JWT_SECRET`, `INTERNAL_TOKEN`, `NEGOCIO` (nombre que sale en el recibo).

### Con Docker (para un servidor)

```bash
cd plataforma-movil/backend
docker compose up --build
```

Crea su propia base MariaDB con el script del proyecto y publica el gateway en el puerto 8080.

## 2. Abrir la app

```bash
cd plataforma-movil/app
npm install
npx expo start
```

- **En tu celular:** instala **Expo Go** (Play Store o App Store) y escanea el código QR que aparece en la terminal. El celular y el computador deben estar en la **misma red WiFi**.
  En la pantalla de inicio toca **⚙ Servidor** y escribe la IP de tu computador, por ejemplo `http://192.168.1.10:8080/api`. Para ver tu IP en Windows ejecuta `ipconfig` y busca "Dirección IPv4".
- **En el navegador:** presiona `w` en la terminal (o `npx expo start --web`).
- **Emulador Android:** presiona `a` (usa `http://10.0.2.2:8080/api` automáticamente).

## 3. Generar el instalable (APK / iOS)

Con una cuenta gratuita de Expo:

```bash
npm install -g eas-cli
eas login
eas build -p android --profile preview   # genera un APK para instalar en Android
eas build -p ios                          # requiere cuenta de Apple Developer
```

## Qué hace la app

- Inicio de sesión y registro (los mismos usuarios de la app de escritorio).
- Menú con las 4 categorías, **Nuevo pedido / recibo** y **Pedidos anteriores**.
- Cada categoría: lista con buscador, stock (y **curvas** en Ropa), crear, editar y eliminar.
- **Pedido:** cliente, tipo de venta (detal o por mayor), productos con control de stock, **firma con el dedo**, confirmación que **descuenta el stock** y abre el **recibo en PDF** para compartirlo (WhatsApp, correo, imprimir…).
