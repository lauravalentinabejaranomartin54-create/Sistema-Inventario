package gestioninventario.controlador;

import gestioninventario.dao.UsuarioDAO;
import gestioninventario.dao.UsuarioDAOImpl;
import gestioninventario.modelo.Usuario;
import gestioninventario.util.Seguridad;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Controlador del inicio de sesión y del registro de usuarios.
 *
 * @author Master
 */
public class UsuarioControlador {

    private static final int MIN_USUARIO = 4;
    private static final int MAX_USUARIO = 50;
    private static final int MIN_CONTRASENA = 6;

    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    // Registra un usuario nuevo
    public boolean registrar(String usuario, char[] contrasena, char[] confirmacion) throws SQLException {
        try {
            String nombre = normalizar(usuario);
            if (nombre.length() < MIN_USUARIO || nombre.length() > MAX_USUARIO) {
                throw new IllegalArgumentException("El usuario debe tener entre "
                        + MIN_USUARIO + " y " + MAX_USUARIO + " caracteres");
            }
            if (!nombre.matches("[A-Za-z0-9._-]+")) {
                throw new IllegalArgumentException(
                        "El usuario solo puede tener letras, números, punto, guion o guion bajo (sin espacios)");
            }
            if (contrasena == null || contrasena.length < MIN_CONTRASENA) {
                throw new IllegalArgumentException("La contraseña debe tener al menos "
                        + MIN_CONTRASENA + " caracteres");
            }
            if (!Arrays.equals(contrasena, confirmacion)) {
                throw new IllegalArgumentException("Las contraseñas no coinciden");
            }
            if (usuarioDAO.buscarPorUsuario(nombre) != null) {
                throw new IllegalArgumentException("Ese nombre de usuario ya está registrado");
            }
            Usuario nuevo = new Usuario(0, nombre, Seguridad.generarHash(contrasena));
            return usuarioDAO.insertar(nuevo);
        } finally {
            // Borramos la contraseña de la memoria
            limpiar(contrasena);
            limpiar(confirmacion);
        }
    }

    // Valida usuario y contraseña; retorna el usuario si son correctos
    public Usuario iniciarSesion(String usuario, char[] contrasena) throws SQLException {
        try {
            String nombre = normalizar(usuario);
            if (nombre.isEmpty() || contrasena == null || contrasena.length == 0) {
                throw new IllegalArgumentException("Ingresa tu usuario y tu contraseña");
            }
            Usuario encontrado = usuarioDAO.buscarPorUsuario(nombre);
            // Mismo mensaje en ambos casos para no revelar qué usuarios existen
            if (encontrado == null || !Seguridad.verificar(contrasena, encontrado.getContrasenaHash())) {
                throw new IllegalArgumentException("Usuario o contraseña incorrectos");
            }
            return encontrado;
        } finally {
            limpiar(contrasena);
        }
    }

    private static String normalizar(String usuario) {
        return usuario == null ? "" : usuario.trim();
    }

    private static void limpiar(char[] datos) {
        if (datos != null) {
            Arrays.fill(datos, '\0');
        }
    }
}
