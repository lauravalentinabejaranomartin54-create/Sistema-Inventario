package gestioninventario.controlador;

import gestioninventario.dao.ProductoDAO;
import gestioninventario.dao.ProductoDAOImpl;
import gestioninventario.modelo.Producto;
import java.sql.SQLException;
import java.util.List;

public class ProductoControlador {
    private final ProductoDAO productoDAO;

    public ProductoControlador(){
        productoDAO = new ProductoDAOImpl();
    }

    public boolean registrar(Producto producto) throws SQLException{
        validarProducto(producto);
        Producto encontrado = productoDAO.buscarPorCodigo(producto.getCodigo());
        if(encontrado != null){
            throw new IllegalArgumentException("Ya existe un producto con ese código");
        }
        return productoDAO.insertar(producto);
    }

    public List<Producto> listar() throws SQLException{
        return productoDAO.listar();
    }

    public Producto buscar(String codigo) throws SQLException{
        if(codigo == null){
            throw new IllegalArgumentException("Debe ingresar un código");
        }

        codigo = codigo.trim();

        if(codigo.isEmpty()){
            throw new IllegalArgumentException("Debe ingresar un código");
        }

        return productoDAO.buscarPorCodigo(codigo);
    }

    public boolean actualizar(Producto producto) throws SQLException{
        validarProducto(producto);
        if(producto.getId() <= 0){
            throw new IllegalArgumentException("Debe seleccionar un producto");
        }
        Producto existente = productoDAO.buscarPorCodigo(producto.getCodigo());

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
        if(producto.getCodigo() == null || producto.getCodigo().trim().isEmpty()){
            throw new IllegalArgumentException("El código es obligatorio");
        }

        if(producto.getNombre() == null || producto.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }

        if(producto.getMarca() == null || producto.getMarca().trim().isEmpty()){
            throw new IllegalArgumentException("La marca es obligatoria");
        }

        if(producto.getCaracteristicas() == null || producto.getCaracteristicas().trim().isEmpty()){
            throw new IllegalArgumentException("Las características son obligatorias");
        }

        if(producto.getTono() == null || producto.getTono().trim().isEmpty()){
            throw new IllegalArgumentException("El tono es obligatorio");
        }

        if(producto.getStock() < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}
