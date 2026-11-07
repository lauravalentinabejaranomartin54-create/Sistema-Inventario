package gestioninventario.controlador;

import gestioninventario.dao.PortarretratoDAO;
import gestioninventario.dao.PortarretratoDAOImpl;
import gestioninventario.modelo.Portarretrato;
import gestioninventario.util.Validaciones;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador de la categoría Portarretratos.
 * Implementa la interfaz Controlador usando Portarretrato como TipoProducto.
 *
 * @author Master
 */
public class PortarretratoControlador implements Controlador<Portarretrato> {

    // Dependencia hacia el DAO
    private final PortarretratoDAO portarretratoDAO;

    public PortarretratoControlador() {
        portarretratoDAO = new PortarretratoDAOImpl();
    }

    @Override
    public boolean registrar(Portarretrato producto) throws SQLException {
        validar(producto);
        return portarretratoDAO.insertar(producto);
    }

    @Override
    public List<Portarretrato> listar() throws SQLException {
        return portarretratoDAO.listar();
    }

    @Override
    public boolean actualizar(Portarretrato producto) throws SQLException {
        Validaciones.validarSeleccion(producto.getId());
        validar(producto);
        return portarretratoDAO.actualizar(producto);
    }

    @Override
    public boolean eliminar(int id) throws SQLException {
        Validaciones.validarSeleccion(id);
        return portarretratoDAO.eliminar(id);
    }

    private void validar(Portarretrato producto) throws SQLException {
        Validaciones.requerido(producto.getReferencia(), "Referencia", 30);
        Validaciones.requerido(producto.getProducto(), "Producto", 100);
        Validaciones.validarComunes(producto);
        // La referencia no se puede repetir
        String referencia = producto.getReferencia().trim();
        Portarretrato existente = portarretratoDAO.buscarPorReferencia(referencia);
        Validaciones.validarReferenciaUnica(existente, producto.getId(), referencia);
    }
}
