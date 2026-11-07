package gestioninventario.dao;

import gestioninventario.modelo.ArticuloInventario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Métodos de apoyo para los DAO de categorías.
 * Evita repetir en cada DAO el manejo de las columnas comunes
 * (característica, stock y los tres precios).
 *
 * @author Master
 */
final class DaoUtil {

    // Columnas comunes en el orden en que las asigna asignarComunes(...)
    static final String COLUMNAS_COMUNES =
            "caracteristica, stock, precio_importador, precio_mayorista, precio_detal";

    // Fragmento para los UPDATE
    static final String SET_COMUNES =
            "caracteristica = ?, stock = ?, precio_importador = ?, "
            + "precio_mayorista = ?, precio_detal = ?";

    private DaoUtil() {
    }

    /**
     * Asigna los 5 valores comunes a partir de la posición indicada.
     *
     * @return la siguiente posición libre del PreparedStatement
     */
    static int asignarComunes(PreparedStatement ps, int posicion, ArticuloInventario articulo)
            throws SQLException {
        ps.setString(posicion++, articulo.getCaracteristica());
        ps.setInt(posicion++, articulo.getStock());
        ps.setDouble(posicion++, articulo.getPrecioImportador());
        ps.setDouble(posicion++, articulo.getPrecioMayorista());
        ps.setDouble(posicion++, articulo.getPrecioDetal());
        return posicion;
    }

    // Lee el id y las columnas comunes de la fila actual del ResultSet
    static void leerComunes(ResultSet rs, ArticuloInventario articulo) throws SQLException {
        articulo.setId(rs.getInt("id"));
        articulo.setCaracteristica(rs.getString("caracteristica"));
        articulo.setStock(rs.getInt("stock"));
        articulo.setPrecioImportador(rs.getDouble("precio_importador"));
        articulo.setPrecioMayorista(rs.getDouble("precio_mayorista"));
        articulo.setPrecioDetal(rs.getDouble("precio_detal"));
    }
}
