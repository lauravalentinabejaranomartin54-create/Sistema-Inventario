package gestioninventario.dao;

import gestioninventario.modelo.Peluche;
import java.sql.SQLException;

public interface PelucheDAO extends CrudDAO<Peluche> {
    Peluche buscarPorReferencia(String referencia) throws SQLException;
}
