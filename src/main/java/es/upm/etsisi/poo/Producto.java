package es.upm.etsisi.poo;

public class Producto {
    public enum Categoria {MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS;

        public static Categoria getCategoria(String categoria) {
            switch (categoria) {
                case "MERCH":
                     return Categoria.MERCH;
                case "STATIONERY":
                    return Categoria.STATIONERY;
                case "CLOTHES":
                    return Categoria.CLOTHES;
                case "BOOK":
                    return Categoria.BOOK;
                case "ELECTRONICS":
                    return Categoria.ELECTRONICS;
                default:
                    return null;
            }
        }
    }
    private int ID;
    private String nombre;
    private Categoria categoria;
    private double precio;

    public Producto(int ID, String nombre, Categoria categoria, double precio) {
        this.ID = ID;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria.toString();
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String productoToString() {
        return "{class:Product, id:" + getID() + ", name:" + getNombre() +", category:" + getCategoria() + ", price:" + getPrecio() + "}";
    }


}

