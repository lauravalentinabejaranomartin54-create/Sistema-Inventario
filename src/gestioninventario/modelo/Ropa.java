package gestioninventario.modelo;

public class Ropa implements ArticuloInventario {
    private int id;
    private String referencia;
    private String tipoRopa;
    private String marca;
    private String color;
    private String caracteristica;
    private String tallas;
    private int stockCurvas;
    private int stock;
    private double precioImportador;
    private double precioMayorista;
    private double precioDetal;

    public Ropa() {
    }

    public Ropa(int id, String referencia, String tipoRopa, String marca, String color,
            String caracteristica, String tallas, int stockCurvas, int stock,
            double precioImportador, double precioMayorista, double precioDetal) {
        this.id = id;
        this.referencia = referencia;
        this.tipoRopa = tipoRopa;
        this.marca = marca;
        this.color = color;
        this.caracteristica = caracteristica;
        this.tallas = tallas;
        this.stockCurvas = stockCurvas;
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

    public String getTipoRopa() {
        return tipoRopa;
    }

    public void setTipoRopa(String tipoRopa) {
        this.tipoRopa = tipoRopa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    public String getTallas() {
        return tallas;
    }

    public void setTallas(String tallas) {
        this.tallas = tallas;
    }

    public int getStockCurvas() {
        return stockCurvas;
    }

    public void setStockCurvas(int stockCurvas) {
        this.stockCurvas = stockCurvas;
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
        return tipoRopa + " " + marca + " " + color + " (" + tallas + ")";
    }

    @Override
    public String toString() {
        return referencia + " - " + getDescripcion();
    }
}
