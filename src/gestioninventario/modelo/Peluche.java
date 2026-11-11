package gestioninventario.modelo;

public class Peluche implements ArticuloInventario {
    private int id;
    private String referencia;
    private String producto;
    private String color;
    private String caracteristica;
    private String tamano;
    private int stock;
    private double precioImportador;
    private double precioMayorista;
    private double precioDetal;

    public Peluche() {
    }

    public Peluche(int id, String referencia, String producto, String color,
            String caracteristica, String tamano, int stock,
            double precioImportador, double precioMayorista, double precioDetal) {
        this.id = id;
        this.referencia = referencia;
        this.producto = producto;
        this.color = color;
        this.caracteristica = caracteristica;
        this.tamano = tamano;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getCaracteristica() {
        return caracteristica;
    }

    @Override
    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
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
    public String toString() {
        return referencia + " - " + producto + " - " + color + " - " + tamano;
    }
}
