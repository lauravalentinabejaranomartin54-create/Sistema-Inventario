package gestioninventario.util;

import gestioninventario.modelo.ArticuloInventario;

/**
 * Validaciones que usan los controladores de las 4 categorías.
 *
 * Es una clase normal con métodos "static": se usan sin crear objetos,
 * por ejemplo  Validaciones.requerido(texto, "Marca", 100);
 * Si un dato es incorrecto lanzan IllegalArgumentException con un mensaje
 * que la ventana muestra al usuario.
 *
 * @author Master
 */
public final class Validaciones {

    // Máximo que admite DECIMAL(12,2) en la base de datos
    private static final double PRECIO_MAXIMO = 9_999_999_999.99;

    // Constructor privado: no se necesita crear objetos de esta clase
    private Validaciones() {
    }

    // El texto no puede estar vacío ni superar el largo de la columna en la BD
    public static void requerido(String valor, String campo, int maximo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
        if (valor.trim().length() > maximo) {
            throw new IllegalArgumentException("El campo " + campo + " admite máximo " + maximo + " caracteres");
        }
    }

    // Valida los datos que tienen todos los productos: característica, stock y precios
    public static void validarComunes(ArticuloInventario articulo) {
        requerido(articulo.getCaracteristica(), "Característica", 255);
        if (articulo.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        validarPrecio(articulo.getPrecioImportador(), "Precio importador");
        validarPrecio(articulo.getPrecioMayorista(), "Precio mayorista");
        validarPrecio(articulo.getPrecioDetal(), "Precio al detal");
    }

    // Para actualizar o eliminar debe haber un registro seleccionado
    public static void validarSeleccion(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Debe seleccionar un registro de la tabla");
        }
    }

    // La referencia no puede pertenecer a OTRO producto
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
