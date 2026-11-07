package gestioninventario.modelo;

/**
 * INTERFAZ con los métodos que deben tener TODOS los productos del inventario
 * (Tecnologia, Ropa, Peluche y Portarretrato).
 *
 * Una interfaz es un "contrato": solo dice QUÉ métodos debe tener una clase,
 * no cómo funcionan. Cada clase que escribe "implements ArticuloInventario"
 * está obligada a programar todos estos métodos.
 *
 * ¿Para qué sirve? Para que otras clases (por ejemplo DaoUtil o Validaciones)
 * puedan trabajar con cualquier producto sin importar su categoría: si recibe
 * un ArticuloInventario, sabe que tiene id, característica, stock y precios.
 *
 * @author Master
 */
public interface ArticuloInventario {

    int getId();

    void setId(int id);

    String getCaracteristica();

    void setCaracteristica(String caracteristica);

    int getStock();

    void setStock(int stock);

    double getPrecioImportador();

    void setPrecioImportador(double precioImportador);

    double getPrecioMayorista();

    void setPrecioMayorista(double precioMayorista);

    double getPrecioDetal();

    void setPrecioDetal(double precioDetal);
}
