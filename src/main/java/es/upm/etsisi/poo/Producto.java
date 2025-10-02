package es.upm.etsisi.poo;

public class Producto {
    //Contiene las distintas categorías y organiza los productos en ellas dependiendo de un string
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
    private int ID; // Id del producto
    private String nombre; // Nombre del producto
    private Categoria categoria; // Categoría del producto
    private double precio; // Precio del producto

    //Constructos de la clase Producto
    public Producto(int ID, String nombre, Categoria categoria, double precio) {
        this.ID = ID;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    // Devuelve el precio del producto
    public double getPrecio() {
        return precio;
    }

    //Cambia el precio del producto por el dado
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Devuelve la categoría a la que pertenece el producto
    public String getCategoria() {
        return categoria.toString();
    }

    //Cambia la categoría del producto por la dada
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Devuelve el nombre del producto
    public String getNombre() {
        return nombre;
    }

    //Cambia el nombre del producto por el dado
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el id del producto
    public int getID() {
        return ID;
    }

    //Cambia el id del producto por el dado
    public void setID(int ID) {
        this.ID = ID;
    }

    // Devuelve los datos del producto en un string con el formato "{class:Producto, id:-, name:-, category:-, price:-}"
    public String productoToString() {
        return "{class:Product, id:" + getID() + ", name:" + getNombre() +", category:" + getCategoria() + ", price:" + getPrecio() + "}";
    }


}

