package gestioninventario.dao;

import gestioninventario.modelo.Peluche;
import java.sql.SQLException;

/**
 * Acceso a datos de la categoría Peluches.
 *
 * @author Master
 */
public interface PelucheDAO extends CrudDAO<Peluche> {

    // Buscar por referencia; retorna null si no existe
    Peluche buscarPorReferencia(String referencia) throws SQLException;
}
