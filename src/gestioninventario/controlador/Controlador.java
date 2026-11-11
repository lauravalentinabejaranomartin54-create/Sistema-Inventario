package gestioninventario.controlador;

import java.sql.SQLException;
import java.util.List;

public interface Controlador<TipoProducto> {
    boolean registrar(TipoProducto producto) throws SQLException;

    List<TipoProducto> listar() throws SQLException;

    boolean actualizar(TipoProducto producto) throws SQLException;

    boolean eliminar(int id) throws SQLException;
}
