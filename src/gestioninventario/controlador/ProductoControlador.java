/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioninventario.controlador;

import gestioninventario.dao.ProductoDAO;
import gestioninventario.dao.ProductoDAOImpl;
import gestioninventario.modelo.Producto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Master
 */
public class ProductoControlador {
    //Declaramos la dependencia hacia DAO
    private final ProductoDAO productoDAO;

    public ProductoControlador(){
        // Creamos la implementación concreta del DAO
        productoDAO = new ProductoDAOImpl();
    }

    //Creamos el método encargado de registrar
    public boolean registrar(Producto producto) throws SQLException{
        //Validamos primero los datos
        validarProducto(producto);
        //Buscamos si ya existe el código
        Producto encontrado = productoDAO.buscarPorCodigo(producto.getCodigo());
        if(encontrado != null){
            throw new IllegalArgumentException("Ya existe un producto con ese código");
        }
        return productoDAO.insertar(producto);
    }

    // Creamos método para listar
    public List<Producto> listar() throws SQLException{
        return productoDAO.listar();
    }

    public Producto buscar(String codigo) throws SQLException{
        if(codigo == null){
            throw new IllegalArgumentException("Debe ingresar un código");
        }

        //Eliminamos espacios laterales
        codigo = codigo.trim();

        if(codigo.isEmpty()){
            throw new IllegalArgumentException("Debe ingresar un código");
        }

        return productoDAO.buscarPorCodigo(codigo);
    }

    // Creamos método actualizar
    public boolean actualizar(Producto producto) throws SQLException{
        //Validamos los datos
        validarProducto(producto);
        //Validamos que exista un identificador
        if(producto.getId() <= 0){
            throw new IllegalArgumentException("Debe seleccionar un producto");
        }
        Producto existente = productoDAO.buscarPorCodigo(producto.getCodigo());

        //Comprobamos si el código pertenece a otro producto
        if(existente != null && existente.getId() != producto.getId()){
            throw new IllegalArgumentException("El código ya pertenece a otro producto");
        }
        return productoDAO.actualizar(producto);
    }

    public boolean eliminar(int id) throws SQLException{
        if(id <= 0){
            throw new IllegalArgumentException("Debe seleccionar un producto");
        }

        return productoDAO.eliminar(id);
    }

    private void validarProducto(Producto producto){
        //Validamos el código
        if(producto.getCodigo() == null || producto.getCodigo().trim().isEmpty()){
            throw new IllegalArgumentException("El código es obligatorio");
        }

        // Validamos el nombre
        if(producto.getNombre() == null || producto.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }

        // Validamos la marca
        if(producto.getMarca() == null || producto.getMarca().trim().isEmpty()){
            throw new IllegalArgumentException("La marca es obligatoria");
        }

        // Validamos las características
        if(producto.getCaracteristicas() == null || producto.getCaracteristicas().trim().isEmpty()){
            throw new IllegalArgumentException("Las características son obligatorias");
        }

        // Validamos el tono
        if(producto.getTono() == null || producto.getTono().trim().isEmpty()){
            throw new IllegalArgumentException("El tono es obligatorio");
        }

        // Validamos el stock
        if(producto.getStock() < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}
