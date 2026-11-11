package gestioninventario.dao;

import gestioninventario.modelo.Usuario;
import java.sql.SQLException;

public interface UsuarioDAO {
    boolean insertar(Usuario usuario) throws SQLException;

    Usuario buscarPorUsuario(String usuario) throws SQLException;
}
