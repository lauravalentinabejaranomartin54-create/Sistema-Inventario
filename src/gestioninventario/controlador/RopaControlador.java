package gestioninventario.controlador;

import gestioninventario.dao.RopaDAO;
import gestioninventario.dao.RopaDAOImpl;
import gestioninventario.modelo.Ropa;
import gestioninventario.util.Validaciones;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador de la categoría Ropa.
 * Implementa la interfaz Controlador usando Ropa como TipoProducto.
 *
 * @author Master
 */
public class RopaControlador implements Controlador<Ropa> {

    // Dependencia hacia el DAO
    private final RopaDAO ropaDAO;

    public RopaControlador() {
        ropaDAO = new RopaDAOImpl();
    }

    @Override
    public boolean registrar(Ropa producto) throws SQLException {
        validar(producto);
        return ropaDAO.insertar(producto);
    }

    @Override
    public List<Ropa> listar() throws SQLException {
        return ropaDAO.listar();
    }

    @Override
    public boolean actualizar(Ropa producto) throws SQLException {
        Validaciones.validarSeleccion(producto.getId());
        validar(producto);
        return ropaDAO.actualizar(producto);
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        Validaciones.validarSeleccion(id);
        return ropaDAO.eliminar(id);
    }

    // Ropa no tiene referencia, por eso no se valida duplicado
    private void validar(Ropa producto) {
        Validaciones.requerido(producto.getTipoRopa(), "Tipo de ropa", 50);
        Validaciones.requerido(producto.getMarca(), "Marca", 100);
        Validaciones.requerido(producto.getColor(), "Color", 50);
        Validaciones.requerido(producto.getTallas(), "Tallas", 50);
        Validaciones.validarComunes(producto);
    }
}
