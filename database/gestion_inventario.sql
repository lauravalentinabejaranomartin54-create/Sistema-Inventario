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
    referencia VARCHAR(30) NOT NULL UNIQUE,
    tipo_ropa VARCHAR(50) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL,
    caracteristica VARCHAR(255) NOT NULL,
    tallas VARCHAR(50) NOT NULL,
    stock_curvas INT NOT NULL DEFAULT 0 CHECK (stock_curvas >= 0),
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

ALTER TABLE ropa ADD COLUMN IF NOT EXISTS referencia VARCHAR(30) NULL AFTER id;
ALTER TABLE ropa ADD COLUMN IF NOT EXISTS stock_curvas INT NOT NULL DEFAULT 0 CHECK (stock_curvas >= 0) AFTER tallas;
UPDATE ropa SET referencia = CONCAT('ROP-', LPAD(id, 3, '0')) WHERE referencia IS NULL OR referencia = '';
ALTER TABLE ropa MODIFY referencia VARCHAR(30) NOT NULL;
ALTER TABLE ropa ADD UNIQUE INDEX IF NOT EXISTS uq_ropa_referencia (referencia);

CREATE TABLE IF NOT EXISTS pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(100) NOT NULL,
    documento VARCHAR(30) NULL,
    tipo_venta VARCHAR(20) NOT NULL,
    total DECIMAL(14,2) NOT NULL CHECK (total >= 0),
    firma MEDIUMBLOB NOT NULL,
    usuario_id INT NOT NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS pedido_detalle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    producto_id INT NOT NULL,
    referencia VARCHAR(30) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(12,2) NOT NULL CHECK (precio_unitario >= 0),
    subtotal DECIMAL(14,2) NOT NULL CHECK (subtotal >= 0),
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE
);
