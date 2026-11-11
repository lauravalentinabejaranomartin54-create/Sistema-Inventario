package gestioninventario.modelo;

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
