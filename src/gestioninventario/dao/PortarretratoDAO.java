package gestioninventario.dao;

import gestioninventario.modelo.Portarretrato;
import java.sql.SQLException;

/**
 * Acceso a datos de la categoría Portarretratos.
 *
 * @author Master
 */
public interface PortarretratoDAO extends CrudDAO<Portarretrato> {

    // Buscar por referencia; retorna null si no existe
    Portarretrato buscarPorReferencia(String referencia) throws SQLException;
}
