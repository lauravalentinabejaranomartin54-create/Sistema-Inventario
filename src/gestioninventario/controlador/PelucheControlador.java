package gestioninventario.controlador;

import gestioninventario.dao.PelucheDAO;
import gestioninventario.dao.PelucheDAOImpl;
import gestioninventario.modelo.Peluche;
import gestioninventario.util.Validaciones;
import java.sql.SQLException;
import java.util.List;

public class PelucheControlador implements Controlador<Peluche> {
    private final PelucheDAO pelucheDAO;

    public PelucheControlador() {
        pelucheDAO = new PelucheDAOImpl();
    }

    @Override
    public boolean registrar(Peluche producto) throws SQLException {
        validar(producto);
        return pelucheDAO.insertar(producto);
    }

    @Override
    public List<Peluche> listar() throws SQLException {
        return pelucheDAO.listar();
    }

    @Override
    public boolean actualizar(Peluche producto) throws SQLException {
        Validaciones.validarSeleccion(producto.getId());
        validar(producto);
        return pelucheDAO.actualizar(producto);
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        Validaciones.validarSeleccion(id);
        return pelucheDAO.eliminar(id);
    }

    private void validar(Peluche producto) throws SQLException {
        Validaciones.requerido(producto.getReferencia(), "Referencia", 30);
        Validaciones.requerido(producto.getProducto(), "Producto", 100);
        Validaciones.requerido(producto.getColor(), "Color", 50);
        Validaciones.requerido(producto.getTamano(), "Tamaño", 50);
        Validaciones.validarComunes(producto);
        String referencia = producto.getReferencia().trim();
        Peluche existente = pelucheDAO.buscarPorReferencia(referencia);
        Validaciones.validarReferenciaUnica(existente, producto.getId(), referencia);
    }
}
