package es.upm.etsisi.poo;

public class Producto {
    public enum categorias {MERCH, PAPELERIA, ROPA, LIBRO,
        ELECTRONICA;


        public String categoriasToString(){

            return "";
        }
    }
    private int ID;
    private int numero;
    private String nombre;
    private String categoria;
    private double precio;

    public Producto(int numero, int ID, String nombre, String categoria, double precio) {
        this.numero = numero;
        this.ID = ID;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
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

