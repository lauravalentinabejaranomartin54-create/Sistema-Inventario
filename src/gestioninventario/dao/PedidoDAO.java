package gestioninventario.dao;

import gestioninventario.modelo.Pedido;
import java.sql.SQLException;

public interface PedidoDAO {
    int registrar(Pedido pedido) throws SQLException;
}
