CREATE DATABASE IF NOT EXISTS gestion_inventario;

USE gestion_inventario;

CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    caracteristicas VARCHAR(255) NOT NULL,
    tono VARCHAR(50) NOT NULL,
    stock INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

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

CREATE TABLE IF NOT EXISTS ropa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_ropa VARCHAR(50) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL,
    caracteristica VARCHAR(255) NOT NULL,
    tallas VARCHAR(50) NOT NULL,
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    precio_importador DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_importador >= 0),
    precio_mayorista DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_mayorista >= 0),
    precio_detal DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_detal >= 0)
);

CREATE TABLE IF NOT EXISTS peluches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    referencia VARCHAR(30) NOT NULL UNIQUE,
    producto VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL,
    caracteristica VARCHAR(255) NOT NULL,
    tamano VARCHAR(50) NOT NULL,
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    precio_importador DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_importador >= 0),
    precio_mayorista DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_mayorista >= 0),
    precio_detal DECIMAL(12,2) NOT NULL DEFAULT 0 CHECK (precio_detal >= 0)
);

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
