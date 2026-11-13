package gestioninventario.controlador;

import gestioninventario.dao.PedidoDAO;
import gestioninventario.dao.PedidoDAOImpl;
import gestioninventario.modelo.DetallePedido;
import gestioninventario.modelo.Pedido;
import gestioninventario.util.Validaciones;
import java.sql.SQLException;

public class PedidoControlador {
    private final PedidoDAO pedidoDAO;

    public PedidoControlador() {
        pedidoDAO = new PedidoDAOImpl();
    }

    public int registrar(Pedido pedido) throws SQLException {
        validar(pedido);
        return pedidoDAO.registrar(pedido);
    }

    public void validar(Pedido pedido) {
        Validaciones.requerido(pedido.getCliente(), "Cliente", 100);
        if (pedido.getDocumento() != null && pedido.getDocumento().length() > 30) {
            throw new IllegalArgumentException("El documento admite máximo 30 caracteres");
        }
        if (pedido.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("Agrega al menos un producto al pedido");
        }
        for (DetallePedido d : pedido.getDetalles()) {
            if (d.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad de " + d.getReferencia() + " debe ser mayor que cero");
            }
        }
        if (pedido.getFirma() == null || pedido.getFirma().length == 0) {
            throw new IllegalArgumentException("El cliente debe firmar el pedido");
        }
        if (pedido.getUsuarioId() <= 0) {
            throw new IllegalArgumentException("Debes iniciar sesión para registrar pedidos");
        }
    }
}
