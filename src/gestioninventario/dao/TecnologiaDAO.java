package gestioninventario.dao;

import gestioninventario.modelo.Tecnologia;
import java.sql.SQLException;

/**
 * Acceso a datos de la categoría Tecnología.
 *
 * @author Master
 */
public interface TecnologiaDAO extends CrudDAO<Tecnologia> {

    // Buscar por referencia; retorna null si no existe
    Tecnologia buscarPorReferencia(String referencia) throws SQLException;
}
