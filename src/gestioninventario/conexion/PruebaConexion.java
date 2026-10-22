/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioninventario.conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Master
 */
public class PruebaConexion {
    public static void main(String[] args) {
        try{
            Connection conexion = Conexion.getInstancia().conectar();
            if(conexion != null){
                System.out.println("Conexión exitosa con MySQL");
                conexion.close();
            }
        }catch(SQLException e){
            System.out.println("Error de conexion: " + e.getMessage());
        }
    }
}
