/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioninventario.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Master
 */
public class Conexion {
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB = "gestion_inventario";
    private final String USER = "root";
    private final String PASSWORD = "";

    // Declarar una variable que almacenará la única instancia de Conexion
    private static Conexion instancia;

    // Creamos un constructor privado
    // Al ser private ninguna otra clase podrá escribir new Conexion()
    private Conexion() {
    }

    // Creamos un método público y estático para obtener la instancia.
    public static Conexion getInstancia() {
        // Verificamos si todavía no hemos creado una instancia
        if (instancia == null) {
            // Creamos la instancia únicamente la primera vez
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection conectar() throws SQLException {
        // Debemos concatenar URL + DB
        return DriverManager.getConnection(
                URL + DB + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                USER,
                PASSWORD
        );
    }
}
