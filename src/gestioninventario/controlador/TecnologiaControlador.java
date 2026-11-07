package gestioninventario.controlador;

import gestioninventario.dao.TecnologiaDAO;
import gestioninventario.dao.TecnologiaDAOImpl;
import gestioninventario.modelo.Tecnologia;
import gestioninventario.util.Validaciones;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador de la categoría Tecnología.
 * Implementa la interfaz Controlador usando Tecnologia como TipoProducto.
 *
 * @author Master
 */
public class TecnologiaControlador implements Controlador<Tecnologia> {

    // Dependencia hacia el DAO
    private final TecnologiaDAO tecnologiaDAO;

    public TecnologiaControlador() {
        tecnologiaDAO = new TecnologiaDAOImpl();
    }

    @Override
    public boolean registrar(Tecnologia producto) throws SQLException {
        validar(producto);
        return tecnologiaDAO.insertar(producto);
    }

    @Override
    public List<Tecnologia> listar() throws SQLException {
        return tecnologiaDAO.listar();
    }

    @Override
    public boolean actualizar(Tecnologia producto) throws SQLException {
        Validaciones.validarSeleccion(producto.getId());
        validar(producto);
        return tecnologiaDAO.actualizar(producto);
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        Validaciones.validarSeleccion(id);
        return tecnologiaDAO.eliminar(id);
    }

    private void validar(Tecnologia producto) throws SQLException {
        Validaciones.requerido(producto.getReferencia(), "Referencia", 30);
        Validaciones.requerido(producto.getProducto(), "Producto", 100);
        Validaciones.requerido(producto.getMarca(), "Marca", 100);
        Validaciones.validarComunes(producto);
        // La referencia no se puede repetir
        String referencia = producto.getReferencia().trim();
        Tecnologia existente = tecnologiaDAO.buscarPorReferencia(referencia);
        Validaciones.validarReferenciaUnica(existente, producto.getId(), referencia);
    }
}
