package gestioninventario.modelo;

/**
 * Modelo del usuario que inicia sesión en el sistema.
 * La contraseña se guarda como hash, nunca en texto plano.
 *
 * @author Master
 */
public class Usuario {

    private int id;
    private String usuario;
    private String contrasenaHash;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con todos los atributos
    public Usuario(int id, String usuario, String contrasenaHash) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenaHash = contrasenaHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    @Override
    public String toString() {
        // Nunca mostramos el hash de la contraseña
        return usuario;
    }
}
