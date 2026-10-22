/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioninventario.principal;

import gestioninventario.dao.ProductoDAO;
import gestioninventario.dao.ProductoDAOImpl;
import gestioninventario.modelo.Producto;
import java.sql.SQLException;

/**
 *
 * @author Master
 */
public class PruebaDao {
    public static void main(String[] args) {
        ProductoDAO productoDAO = new ProductoDAOImpl();
        Producto producto = new Producto(
                0,
                "MAQ-001",
                "Labial mate",
                "Maybelline",
                "Larga duración, 4.2 g",
                "Rojo pasión",
                24
        );
        try{
            boolean registrado = productoDAO.insertar(producto);
            System.out.println("Registrado: " + registrado);
            for(Producto item : productoDAO.listar()){
                System.out.println(item);
            }
        }catch(SQLException e){
            System.out.println("ERROR " + e.getMessage());
        }
    }
}
