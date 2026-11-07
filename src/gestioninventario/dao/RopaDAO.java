package gestioninventario.dao;

import gestioninventario.modelo.Ropa;

/**
 * Acceso a datos de la categoría Ropa.
 * Ropa no tiene referencia única, por eso solo usa las operaciones CRUD básicas.
 *
 * @author Master
 */
public interface RopaDAO extends CrudDAO<Ropa> {
}
