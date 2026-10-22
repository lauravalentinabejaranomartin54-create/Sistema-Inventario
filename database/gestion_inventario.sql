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
