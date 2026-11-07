-- Creamos la base de datos solamente si todavía no existe.
CREATE DATABASE IF NOT EXISTS gestion_inventario;

-- Seleccionamos la base de datos sobre la cual vamos a trabajar.
USE gestion_inventario;

-- Creamos la tabla donde almacenaremos los productos.
CREATE TABLE IF NOT EXISTS productos (

    -- Creamos el identificador único del producto.
    id INT AUTO_INCREMENT PRIMARY KEY,

    -- Guardamos el código o referencia del producto.
    -- UNIQUE evita que dos productos tengan el mismo código.
    codigo VARCHAR(30) NOT NULL UNIQUE,

    -- Guardamos el nombre del producto.
    nombre VARCHAR(100) NOT NULL,

    -- Guardamos la marca.
    marca VARCHAR(100) NOT NULL,

    -- Guardamos las características del producto.
    caracteristicas VARCHAR(255) NOT NULL,

    -- Guardamos el tono (por ejemplo: Nude, Rojo pasión, 120 Ivory).
    tono VARCHAR(50) NOT NULL,

    -- Guardamos las unidades disponibles.
    stock INT NOT NULL DEFAULT 0
);

-- =====================================================================
-- SISTEMA DE INVENTARIO POR CATEGORÍAS
-- =====================================================================

-- Usuarios que pueden iniciar sesión.
-- La contraseña NUNCA se guarda en texto plano: se guarda un hash PBKDF2
-- con el formato  iteraciones:sal:hash  (ver gestioninventario.util.Seguridad).
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Categoría Tecnología.
CREATE TABLE IF NOT EXISTS tecnologia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    referencia VARCHAR(30) NOT NULL UNIQUE,
    producto VARCHAR(100) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    caracteristica VARCHAR(255) NOT NULL,
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    precio_importador DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_importador >= 0),
    precio_mayorista DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_mayorista >= 0),
    precio_detal DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_detal >= 0)
);

-- Categoría Ropa.
CREATE TABLE IF NOT EXISTS ropa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_ropa VARCHAR(50) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL,
    caracteristica VARCHAR(255) NOT NULL,
    -- Ejemplo: "S, M, L" o "6-8-10".
    tallas VARCHAR(50) NOT NULL,
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    precio_importador DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_importador >= 0),
    precio_mayorista DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_mayorista >= 0),
    precio_detal DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_detal >= 0)
);

-- Categoría Peluches.
CREATE TABLE IF NOT EXISTS peluches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    referencia VARCHAR(30) NOT NULL UNIQUE,
    producto VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL,
    caracteristica VARCHAR(255) NOT NULL,
    -- Ejemplo: "Pequeño", "Mediano", "Grande" o "30 cm".
    tamano VARCHAR(50) NOT NULL,
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    precio_importador DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_importador >= 0),
    precio_mayorista DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_mayorista >= 0),
    precio_detal DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_detal >= 0)
);

-- Categoría Portarretratos.
CREATE TABLE IF NOT EXISTS portarretratos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    referencia VARCHAR(30) NOT NULL UNIQUE,
    producto VARCHAR(100) NOT NULL,
    caracteristica VARCHAR(255) NOT NULL,
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    precio_importador DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_importador >= 0),
    precio_mayorista DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_mayorista >= 0),
    precio_detal DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_detal >= 0)
);
