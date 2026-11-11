package gestioninventario.modelo;

public class Usuario {
    private int id;
    private String usuario;
    private String contrasenaHash;

    public Usuario() {
    }

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
        return usuario;
    }
}
