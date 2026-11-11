package gestioninventario.util;

import gestioninventario.modelo.ArticuloInventario;

public final class Validaciones {
    private static final double PRECIO_MAXIMO = 9_999_999_999.99;

    private Validaciones() {
    }

    public static void requerido(String valor, String campo, int maximo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
        if (valor.trim().length() > maximo) {
            throw new IllegalArgumentException("El campo " + campo + " admite máximo " + maximo + " caracteres");
        }
    }

    public static void validarComunes(ArticuloInventario articulo) {
        requerido(articulo.getCaracteristica(), "Característica", 255);
        if (articulo.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        validarPrecio(articulo.getPrecioImportador(), "Precio importador");
        validarPrecio(articulo.getPrecioMayorista(), "Precio mayorista");
        validarPrecio(articulo.getPrecioDetal(), "Precio al detal");
    }

    public static void validarSeleccion(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Debe seleccionar un registro de la tabla");
        }
    }

    public static void validarReferenciaUnica(ArticuloInventario existente, int idActual, String referencia) {
        if (existente != null && existente.getId() != idActual) {
            throw new IllegalArgumentException("Ya existe un producto con la referencia " + referencia);
        }
    }

    private static void validarPrecio(double precio, String campo) {
        if (precio < 0) {
            throw new IllegalArgumentException("El campo " + campo + " no puede ser negativo");
        }
        if (precio > PRECIO_MAXIMO) {
            throw new IllegalArgumentException("El campo " + campo + " es demasiado alto");
        }
    }
}
