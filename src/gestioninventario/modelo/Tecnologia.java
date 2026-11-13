package gestioninventario.modelo;

public class Tecnologia implements ArticuloInventario {
    private int id;
    private String referencia;
    private String producto;
    private String marca;
    private String caracteristica;
    private int stock;
    private double precioImportador;
    private double precioMayorista;
    private double precioDetal;

    public Tecnologia() {
    }

    public Tecnologia(int id, String referencia, String producto, String marca,
            String caracteristica, int stock,
            double precioImportador, double precioMayorista, double precioDetal) {
        this.id = id;
        this.referencia = referencia;
        this.producto = producto;
        this.marca = marca;
        this.caracteristica = caracteristica;
        this.stock = stock;
        this.precioImportador = precioImportador;
        this.precioMayorista = precioMayorista;
        this.precioDetal = precioDetal;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getReferencia() {
        return referencia;
    }

    @Override
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String getCaracteristica() {
        return caracteristica;
    }

    @Override
    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public double getPrecioImportador() {
        return precioImportador;
    }

    @Override
    public void setPrecioImportador(double precioImportador) {
        this.precioImportador = precioImportador;
    }

    @Override
    public double getPrecioMayorista() {
        return precioMayorista;
    }

    @Override
    public void setPrecioMayorista(double precioMayorista) {
        this.precioMayorista = precioMayorista;
    }

    @Override
    public double getPrecioDetal() {
        return precioDetal;
    }

    @Override
    public void setPrecioDetal(double precioDetal) {
        this.precioDetal = precioDetal;
    }

    @Override
    public String getDescripcion() {
        return producto + " " + marca;
    }

    @Override
    public String toString() {
        return referencia + " - " + producto + " - " + marca;
    }
}
