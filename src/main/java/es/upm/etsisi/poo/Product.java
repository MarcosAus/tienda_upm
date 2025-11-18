package es.upm.etsisi.poo;

public class Product {


    private int ID; // Id del producto
    private String name; // Nombre del producto
    private double price; // Precio del producto

    public Product(int ID, String name, double price, String type) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }


    //private Categoria categoria; // Categoría del producto
    //private String fechaEvento;
    //private final int MAX_NUM_PARTICIPANTES = 100;


    /**
     * Constructor de la clase Producto
     * @param ID Numero Identificador del producto
     * @param nombre String que sirve como nombre del producto
     * @param categoria Categoria del enum Categoria que define la categoria del producto
     * @param precio Double que representa el precio individual del producto
     */
    /*public Product(int ID, String nombre, Categoria categoria, double precio) {
        this.ID = ID;
        this.name = nombre;
        this.categoria = categoria;
        this.price = precio;
    }
    public Product(int ID, String nombre, double precioPersona, int numAsistentes, String fechaEvento) {
        if (numAsistentes > MAX_NUM_PARTICIPANTES) {
            this.ID = ID;
            this.name = nombre;
            this.price = precioPersona;
            this.fechaEvento = fechaEvento;
        }
    }

    // Devuelve el precio del producto
    public double getPrice() {
        return price;
    }

    //Cambia el precio del producto por el dado
    public void setPrice(double price) {
        this.price = price;
    }

    // Devuelve la categoría a la que pertenece el producto en formato String
    public String getCategoriaString() {
        return categoria.toString();
    }
    //Cambia la categoría del producto por la dada
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Devuelve el nombre del producto
    public String getName() {
        return name;
    }

    //Cambia el nombre del producto por el dado
    public void setName(String name) {
        this.name = name;
    }

    // Devuelve el id del producto
    public int getID() {
        return ID;
    }*/

    /**
     * Metodo que comprueba si dos productos son iguales comparando sus IDs
     * @param product Objeto Producto con el que se quiere comparar
     * @return Devuelve true si el objeto comparado es igual que el parametro, false en caso contrario
     */
    /*public boolean equals(Product product){
        return product.getID()==this.getID();
    }*/

    /**
     * Metodo que devuelve el descuento aplicable a un producto en funcion de su categoria
     * @return Devuelve un double que representa el descuento a aplicar
     */
    /*public double descuento() {
        double descuento = 0;
        switch (categoria){
            case Categoria.MERCH:
                descuento = 0;
                break;
            case Categoria.STATIONERY:
                descuento = price *0.05;
                break;
            case Categoria.CLOTHES:
                descuento = price *0.07;
                break;
            case Categoria.BOOK:
                descuento = price *0.1;
                break;
            case Categoria.ELECTRONICS:
                descuento = price *0.03;
                break;
        }
        return descuento;
    }*/

    /**
     * Metodo que devuelve el Producto como un String
     * @return String que representa el Producto con todos sus datos
     */
    /*@Override
    public String toString() {
        return "{class:Product, id:" + getID() +
                ", name:" + getName() +
                ", category:" + getCategoriaString() +
                ", price:" + getPrice() + "}";
    }*/
}