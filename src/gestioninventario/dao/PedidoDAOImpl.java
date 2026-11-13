package gestioninventario.dao;

import gestioninventario.conexion.Conexion;
import gestioninventario.modelo.DetallePedido;
import gestioninventario.modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class PedidoDAOImpl implements PedidoDAO {
    @Override
    public int registrar(Pedido pedido) throws SQLException {
        String sqlPedido = "INSERT INTO pedidos (cliente, documento, tipo_venta, total, firma, usuario_id) "
                + "VALUES (?,?,?,?,?,?)";
        String sqlDetalle = "INSERT INTO pedido_detalle (pedido_id, categoria, producto_id, referencia, "
                + "descripcion, cantidad, precio_unitario, subtotal) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conexion = Conexion.getInstancia().conectar()) {
            conexion.setAutoCommit(false);
            try {
                LocalDateTime fecha = LocalDateTime.now().withNano(0);
                int idPedido;
                try (PreparedStatement ps = conexion.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, pedido.getCliente());
                    ps.setString(2, pedido.getDocumento());
                    ps.setString(3, pedido.getTipoVenta());
                    ps.setDouble(4, pedido.getTotal());
                    ps.setBytes(5, pedido.getFirma());
                    ps.setInt(6, pedido.getUsuarioId());
                    ps.executeUpdate();
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        rs.next();
                        idPedido = rs.getInt(1);
                    }
                }
                for (DetallePedido d : pedido.getDetalles()) {
                    descontarStock(conexion, d);
                    try (PreparedStatement ps = conexion.prepareStatement(sqlDetalle)) {
                        ps.setInt(1, idPedido);
                        ps.setString(2, d.getCategoria());
                        ps.setInt(3, d.getProductoId());
                        ps.setString(4, d.getReferencia());
                        ps.setString(5, d.getDescripcion());
                        ps.setInt(6, d.getCantidad());
                        ps.setDouble(7, d.getPrecioUnitario());
                        ps.setDouble(8, d.getSubtotal());
                        ps.executeUpdate();
                    }
                }
                conexion.commit();
                pedido.setId(idPedido);
                pedido.setFecha(fecha);
                return idPedido;
            } catch (SQLException | RuntimeException e) {
                conexion.rollback();
                throw e;
            }
        }
    }

    private void descontarStock(Connection conexion, DetallePedido d) throws SQLException {
        String sql = "UPDATE " + tabla(d.getCategoria()) + " SET stock = stock - ? WHERE id = ? AND stock >= ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, d.getCantidad());
            ps.setInt(2, d.getProductoId());
            ps.setInt(3, d.getCantidad());
            if (ps.executeUpdate() == 0) {
                throw new IllegalArgumentException("No hay stock suficiente de " + d.getReferencia()
                        + " (" + d.getDescripcion() + ")");
            }
        }
    }

    private String tabla(String categoria) {
        switch (categoria) {
            case "tecnologia":
                return "tecnologia";
            case "ropa":
                return "ropa";
            case "peluches":
                return "peluches";
            case "portarretratos":
                return "portarretratos";
            default:
                throw new IllegalArgumentException("Categoría no válida: " + categoria);
        }
    }
}
