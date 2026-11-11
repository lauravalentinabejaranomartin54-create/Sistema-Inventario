package gestioninventario.dao;

import gestioninventario.modelo.Producto;
import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO {
    boolean insertar(Producto producto) throws SQLException;
    List<Producto> listar() throws SQLException;
    Producto buscarPorCodigo(String codigo) throws SQLException;
    boolean actualizar(Producto producto) throws SQLException;
    boolean eliminar(int id) throws SQLException;
}
