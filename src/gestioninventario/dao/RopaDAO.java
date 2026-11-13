package gestioninventario.dao;

import gestioninventario.modelo.Ropa;
import java.sql.SQLException;

public interface RopaDAO extends CrudDAO<Ropa> {
    Ropa buscarPorReferencia(String referencia) throws SQLException;
}
