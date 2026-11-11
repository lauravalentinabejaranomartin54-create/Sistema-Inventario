package gestioninventario.dao;

import gestioninventario.modelo.Tecnologia;
import java.sql.SQLException;

public interface TecnologiaDAO extends CrudDAO<Tecnologia> {
    Tecnologia buscarPorReferencia(String referencia) throws SQLException;
}
