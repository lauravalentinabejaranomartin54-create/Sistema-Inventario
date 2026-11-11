package gestioninventario.dao;

import gestioninventario.conexion.Conexion;
import gestioninventario.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {
    @Override
    public boolean insertar(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos "
                + "(codigo, nombre, marca, caracteristicas, tono, stock) "
                + "VALUES (?,?,?,?,?,?)";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getMarca());
            ps.setString(4, producto.getCaracteristicas());
            ps.setString(5, producto.getTono());
            ps.setInt(6, producto.getStock());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id, codigo, nombre, marca, caracteristicas, tono, stock "
                + "FROM productos ORDER BY id DESC";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setMarca(rs.getString("marca"));
                producto.setCaracteristicas(rs.getString("caracteristicas"));
                producto.setTono(rs.getString("tono"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }
        }
        return productos;
    }

    @Override
    public Producto buscarPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT id, codigo, nombre, marca, caracteristicas, tono, stock "
                + "FROM productos "
                + "WHERE codigo = ?";

        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getInt("id"),
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            rs.getString("marca"),
                            rs.getString("caracteristicas"),
                            rs.getString("tono"),
                            rs.getInt("stock")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE productos "
                + "SET codigo = ?, nombre = ?, marca = ?, "
                + "caracteristicas = ?, tono = ?, stock = ? "
                + "WHERE id = ?";

        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getMarca());
            ps.setString(4, producto.getCaracteristicas());
            ps.setString(5, producto.getTono());
            ps.setInt(6, producto.getStock());
            ps.setInt(7, producto.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }
}
