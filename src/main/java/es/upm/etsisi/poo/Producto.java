package es.upm.etsisi.poo;

public class Producto {
    public enum Categoria {MERCH, PAPELERIA, ROPA, LIBRO,
        ELECTRONICA;


        public String categoriasToString(){

            return "";
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

    public Categoria getCategoria() {
        return categoria;
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

}

