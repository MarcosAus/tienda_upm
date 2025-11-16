package es.upm.etsisi.poo;

public class Product {
    public enum Categoria {MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS;

        /**
         * Metodo que devuelve la categoria en formato Enum al pasarle un String como argumento
         * @param categoria String que comprobaremos si es o no una categoria del enum
         * @return Devuelve la Categoria en tipo Enum
         */
        public static Categoria getCategoria(String categoria) {
            switch (categoria.toUpperCase()) {
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
    private String fechaEvento;
    private final int MAX_NUM_PARTICIPANTES = 100;


    /**
     * Constructor de la clase Producto
     * @param ID Numero Identificador del producto
     * @param nombre String que sirve como nombre del producto
     * @param categoria Categoria del enum Categoria que define la categoria del producto
     * @param precio Double que representa el precio individual del producto
     */
    public Product(int ID, String nombre, Categoria categoria, double precio) {
        this.ID = ID;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }
    public Product(int ID, String nombre, double precioPersona, int numAsistentes, String fechaEvento) {
        if (numAsistentes > MAX_NUM_PARTICIPANTES) {
            this.ID = ID;
            this.nombre = nombre;
            this.precio = precioPersona;
            this.fechaEvento = fechaEvento;
        }
    }

    // Devuelve el precio del producto
    public double getPrecio() {
        return precio;
    }

    //Cambia el precio del producto por el dado
    public void setPrecio(double precio) {
        this.precio = precio;
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

    /**
     * Metodo que comprueba si dos productos son iguales comparando sus IDs
     * @param product Objeto Producto con el que se quiere comparar
     * @return Devuelve true si el objeto comparado es igual que el parametro, false en caso contrario
     */
    public boolean equals(Product product){
        return product.getID()==this.getID();
    }

    /**
     * Metodo que devuelve el descuento aplicable a un producto en funcion de su categoria
     * @return Devuelve un double que representa el descuento a aplicar
     */
    public double descuento() {
        double descuento = 0;
        switch (categoria){
            case Categoria.MERCH:
                descuento = 0;
                break;
            case Categoria.STATIONERY:
                descuento = precio*0.05;
                break;
            case Categoria.CLOTHES:
                descuento = precio*0.07;
                break;
            case Categoria.BOOK:
                descuento = precio*0.1;
                break;
            case Categoria.ELECTRONICS:
                descuento = precio*0.03;
                break;
        }
        return descuento;
    }

    /**
     * Metodo que devuelve el Producto como un String
     * @return String que representa el Producto con todos sus datos
     */
    @Override
    public String toString() {
        return "{class:Product, id:" + getID() +
                ", name:" + getNombre() +
                ", category:" + getCategoriaString() +
                ", price:" + getPrecio() + "}";
    }
}