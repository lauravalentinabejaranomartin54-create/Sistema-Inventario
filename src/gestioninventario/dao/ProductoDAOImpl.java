/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioninventario.dao;

import gestioninventario.conexion.Conexion;
import gestioninventario.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Master
 */
public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public boolean insertar(Producto producto) throws SQLException {
        // Creamos la instrucción SQL
        // Los signos ? serán reemplazados posteriormente
        String sql = "INSERT INTO productos "
                + "(codigo, nombre, marca, caracteristicas, tono, stock) "
                + "VALUES (?,?,?,?,?,?)";
        // Utilizamos un try-with-resources
        // java cerrará automaticamente los recursos al finalizar
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getMarca());
            ps.setString(4, producto.getCaracteristicas());
            ps.setString(5, producto.getTono());
            ps.setInt(6, producto.getStock());

            // executeUpdate retorna cuantas filas fueron afectadas
            int filasAfectadas = ps.executeUpdate();
            // Si se afectó por lo menos una fila, retornamos true.
            return filasAfectadas > 0;
        }
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        //Creamos consulta SQL SELECT
        String sql = "SELECT id, codigo, nombre, marca, caracteristicas, tono, stock "
                + "FROM productos ORDER BY id DESC";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            // Recorremos cada fila del resultado
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
                // Si encontramos el producto lo retornamos
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
        // Si no existe retornamos null
        return null;
    }

    @Override
    public boolean actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE productos "
                + "SET codigo = ?, nombre = ?, marca = ?, "
                + "caracteristicas = ?, tono = ?, stock = ? "
                + "WHERE id = ?";

        // Abrimos nuestros recursos JDBC
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
            // retornamos true si se modifica alguna fila
            return filasAfectadas > 0;
        }
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            // Asignamos el identificador
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }
}
