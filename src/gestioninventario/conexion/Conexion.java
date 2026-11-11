package gestioninventario.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB = "gestion_inventario";
    private final String USER = "root";
    private final String PASSWORD = "";

    private static Conexion instancia;

    private Conexion() {
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                URL + DB + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                USER,
                PASSWORD
        );
    }
}
