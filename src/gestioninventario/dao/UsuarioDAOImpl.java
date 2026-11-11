package gestioninventario.dao;

import gestioninventario.conexion.Conexion;
import gestioninventario.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {
    @Override
    public boolean insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (usuario, contrasena) VALUES (?, ?)";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasenaHash());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Usuario buscarPorUsuario(String usuario) throws SQLException {
        String sql = "SELECT id, usuario, contrasena FROM usuarios WHERE usuario = ?";
        try (
                Connection conexion = Conexion.getInstancia().conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("usuario"),
                            rs.getString("contrasena")
                    );
                }
            }
        }
        return null;
    }
}
