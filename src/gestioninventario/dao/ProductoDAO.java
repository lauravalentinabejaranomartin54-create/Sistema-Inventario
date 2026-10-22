/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestioninventario.dao;

import gestioninventario.modelo.Producto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Master
 */
public interface ProductoDAO {

    //Declaramos una operacion para insertar productos
    boolean insertar(Producto producto) throws SQLException;
    //Declaramos una operacion para consultar todos los productos
    List<Producto> listar() throws SQLException;
    //Declaramos una operacion para buscar por código
    Producto buscarPorCodigo(String codigo) throws SQLException;
    //Declaramos una operacion para modificar productos
    boolean actualizar(Producto producto) throws SQLException;
    //Declaramos una operacion para eliminar productos
    boolean eliminar(int id) throws SQLException;
}
