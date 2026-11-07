package gestioninventario.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * INTERFAZ con las operaciones básicas (CRUD) que comparten los DAO de
 * todas las categorías.
 *
 * <TipoProducto> se reemplaza por el modelo real, por ejemplo:
 *   TecnologiaDAO extends CrudDAO<Tecnologia>
 *
 * @param <TipoProducto> modelo que maneja el DAO
 * @author Master
 */
public interface CrudDAO<TipoProducto> {

    // Insertar un registro nuevo
    boolean insertar(TipoProducto producto) throws SQLException;

    // Consultar todos los registros
    List<TipoProducto> listar() throws SQLException;

    // Modificar un registro existente (se identifica por su id)
    boolean actualizar(TipoProducto producto) throws SQLException;

    // Eliminar un registro por su id
    boolean eliminar(int id) throws SQLException;
}
