package gestioninventario.dao;

import gestioninventario.modelo.ArticuloInventario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

final class DaoUtil {
    static final String COLUMNAS_COMUNES =
            "caracteristica, stock, precio_importador, precio_mayorista, precio_detal";

    static final String SET_COMUNES =
            "caracteristica = ?, stock = ?, precio_importador = ?, "
            + "precio_mayorista = ?, precio_detal = ?";

    private DaoUtil() {
    }

    static int asignarComunes(PreparedStatement ps, int posicion, ArticuloInventario articulo)
            throws SQLException {
        ps.setString(posicion++, articulo.getCaracteristica());
        ps.setInt(posicion++, articulo.getStock());
        ps.setDouble(posicion++, articulo.getPrecioImportador());
        ps.setDouble(posicion++, articulo.getPrecioMayorista());
        ps.setDouble(posicion++, articulo.getPrecioDetal());
        return posicion;
    }

    static void leerComunes(ResultSet rs, ArticuloInventario articulo) throws SQLException {
        articulo.setId(rs.getInt("id"));
        articulo.setCaracteristica(rs.getString("caracteristica"));
        articulo.setStock(rs.getInt("stock"));
        articulo.setPrecioImportador(rs.getDouble("precio_importador"));
        articulo.setPrecioMayorista(rs.getDouble("precio_mayorista"));
        articulo.setPrecioDetal(rs.getDouble("precio_detal"));
    }
}
