package gestioninventario.dao;

import gestioninventario.modelo.Usuario;
import java.sql.SQLException;

/**
 * Operaciones de acceso a datos para los usuarios.
 *
 * @author Master
 */
public interface UsuarioDAO {

    // Registrar un usuario nuevo (con la contraseña ya convertida en hash)
    boolean insertar(Usuario usuario) throws SQLException;

    // Buscar un usuario por su nombre de usuario; retorna null si no existe
    Usuario buscarPorUsuario(String usuario) throws SQLException;
}
