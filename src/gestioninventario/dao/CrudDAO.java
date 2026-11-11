package gestioninventario.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<TipoProducto> {
    boolean insertar(TipoProducto producto) throws SQLException;

    List<TipoProducto> listar() throws SQLException;

    boolean actualizar(TipoProducto producto) throws SQLException;

    boolean eliminar(int id) throws SQLException;
}
