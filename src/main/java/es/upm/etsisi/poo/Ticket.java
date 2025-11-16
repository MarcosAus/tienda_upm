package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;

public class Ticket {
    /**
     * Array de productos actuales en el ticket
     */

    public enum State {
        EMPTY, ACTIVE, CLOSED
    }
    private final ArrayList<Product> products;
    private String id;
    private State stateTicket;
    private Client client;
    private Cashier cashier;

    /**
     * Constructor de la clase Ticket
     */
    public Ticket(Client client, Cashier cashier) {
        this.products = new ArrayList<>();
        this.stateTicket = State.EMPTY;
        this.client = client;
        this.cashier = cashier;
    }

    /**
     * Metodo para acceder a la lista de productos del ticket
     * @return Devuelve un ArrayList con los productos del ticket
     */
    public ArrayList<Product> getProductos() {
        return products;
    }

    /**
     * Metodo que devuelve el tamaño del ticket
     * @return Devuelve el numero de productos que contiene el ticket
     */
    public int getNumeroProductos() {
        return products.size();
    }

    /**
     * Metodo que añade productos al ticket
     * @param product Objeto clase Producto que se quiere meter al ticket
     * @param cantidad El numero de productos que se deben añadir al ticket
     */
    public void addProduct(Product product, int cantidad) {
        if (product != null) {
            for (int i = 0; i < cantidad; i++) {
                products.add(product);
            }
            products.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
            System.out.println("ticket add: ok");
        }
        else System.out.println("This product does not exist. No products were added");
    }


    /**
     * Metodo que revisa la cantidad de productos de una misma categoria que hay en el ticket
     * @return Devuelve un Array de enteros donde cada elemento representa el numero de productos de una categoria
     */
    public int[] getCantidadProductoCategoria() {
        int[] resultado = new int[5];
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            switch (p.getCategoriaString()) {
                case "MERCH":
                    // MERCH NO TIENE DESCUENTO
                    break;
                case "STATIONERY":
                    resultado[1] = resultado[1] + 1;
                    break;
                case "CLOTHES":
                    resultado[2] = resultado[2] + 1;
                    break;
                case "BOOK":
                    resultado[3] = resultado[3] + 1;
                    break;
                case "ELECTRONICS":
                    resultado[4] = resultado[4] + 1;
                    break;
            }
        }
        return resultado;
    }

    /**
     * Metodo que revisa si hay descuento de alguna categoria en especifico
     * @param categoria Categoria de la que se quiere comprobar si hay descuento
     * @param cantidadProductos Array de enteros que contiene la cantidad de productos de cada categoria
     * @return Devuelve true si hay descuento de la categoria parametro y false si no lo hay
     */
    public boolean tieneDescuento(String categoria, int[] cantidadProductos) {
        switch (categoria) {
            case "STATIONERY":
                return cantidadProductos[1] > 1;
            case "CLOTHES":
                return cantidadProductos[2] > 1;
            case "BOOK":
                return cantidadProductos[3] > 1;
            case "ELECTRONICS":
                return cantidadProductos[4] > 1;
            default:
                return false;
        }
    }

    /**
     * Metodo que imprime cada producto del ticket por una linea y, si tiene descuento, lo imprime junto al producto
     */
    public void printTicket() {
        double precio = calcularPrecio();
        double descuentos = calcularDescuentoTotal();
        int[] descuentoPorCategoria = getCantidadProductoCategoria();

        ArrayList<Product> products = this.getProductos();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();

            System.out.print(product);
            if (tieneDescuento(product.getCategoriaString(),descuentoPorCategoria)) {
                System.out.print("**discount -"+ product.descuento());
            }
            System.out.println();
        }

        System.out.println("Total price: "+ precio);
        System.out.println("Total discount: "+ descuentos);
        System.out.println("Final price: " + (precio - descuentos));
    }

    /**
     * Metodo que calcula el precio total de todos los articulos sin descuento
     * @return Devuelve un double que representa el precio sin descuento
     */
    public double calcularPrecio() {
        double precio = 0;
        Iterator<Product> iterator = this.getProductos().iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();

            precio += product.getPrecio();
        }
        return Math.round(precio);
    }

    /**
     * Metodo que calcula el descuento a aplicarle al precio total del ticket
     * @return Devuelve un double que representa el descuento que se deba restar del precio total
     */
    public double calcularDescuentoTotal() {
        double descuento = 0;
        int[] cantidadProductos = this.getCantidadProductoCategoria();
        Iterator<Product> iterator = this.getProductos().iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();

            if (tieneDescuento(product.getCategoriaString(), cantidadProductos)) {
                descuento += product.descuento();
            }
        }
        return ((double) Math.round(descuento * 1000) /1000);
    }

    /**
     * Metodo que elimina por completo un producto y todas sus apariciones del ticket
     * @param id Numero Identificador del producto que se quiere buscar y eliminar
     */
    public boolean removeProduct(int id) {
        boolean resultado = false;
        Product product = Utilities.busquedaProductoPorID(products, id);
        if  (product != null) {
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product product1 = iterator.next();
                if (product1.equals(product)) {
                    System.out.println(product1);
                    iterator.remove();
                    resultado = true;
                }
            }
        }
        return resultado;
    }
    public State getEstadoTicket() {
        return stateTicket;
    }
}