package gestioninventario.dao;

import gestioninventario.modelo.Portarretrato;
import java.sql.SQLException;

public interface PortarretratoDAO extends CrudDAO<Portarretrato> {
    Portarretrato buscarPorReferencia(String referencia) throws SQLException;
}
