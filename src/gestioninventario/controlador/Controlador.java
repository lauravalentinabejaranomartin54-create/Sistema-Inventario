package gestioninventario.controlador;

import java.sql.SQLException;
import java.util.List;

/**
 * INTERFAZ (contrato) que deben cumplir los controladores de las categorías.
 *
 * <TipoProducto> es un "tipo genérico": un nombre provisional que se reemplaza
 * por el modelo real cuando una clase implementa la interfaz. Por ejemplo:
 *
 *   TecnologiaControlador implements Controlador<Tecnologia>
 *      -> registrar(Tecnologia producto), List<Tecnologia> listar(), ...
 *
 *   RopaControlador implements Controlador<Ropa>
 *      -> registrar(Ropa producto), List<Ropa> listar(), ...
 *
 * Así escribimos el contrato una sola vez y sirve para las 4 categorías.
 *
 * @param <TipoProducto> modelo de la categoría (Tecnologia, Ropa, Peluche o Portarretrato)
 * @author Master
 */
public interface Controlador<TipoProducto> {

    // Valida y guarda un producto nuevo
    boolean registrar(TipoProducto producto) throws SQLException;

    // Devuelve todos los productos de la categoría
    List<TipoProducto> listar() throws SQLException;

    // Valida y modifica un producto existente
    boolean actualizar(TipoProducto producto) throws SQLException;

    // Elimina un producto por su id
    boolean eliminar(int id) throws SQLException;
}
