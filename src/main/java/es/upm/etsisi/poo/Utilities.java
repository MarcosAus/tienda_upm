package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;

public class Utilities {
    //Constantes:

    public static final int MAX_LIST = 200; // Número máximo de productos en la Tienda
    public static final int MAX_IN_TICKET = 100; //Número máximo de productos en el Ticket

    //Mensajes de salida:

    public static final String CATEGORY_WRONG = "Category is wrong";
    public static final String LENGTH_WRONG = "Command length is wrong";
    public static final String ID_NOT_NUMBER = "ID is not a number";
    public static final String ID_PRICE_NOT_NUMBER = "ID or price is not a number";
    public static final String PRODUCT_LIST_FULL = "List of Products is full, cannot add any more products";
    public static final String TICKET_NEW_OK = "ticket new: ok";
    public static final String MORE_THAN_TICKET_CAPACITY = "You cannot add more product than the ticket capacity allows";
    public static final String TICKET_FULL = "Ticket is full, cannot add any more products";
    public static final String ID_AMMOUNT_NOT_NUMBER = "ID or amount is not a number";
    public static final String TICKET_REMOVE_OK = "ticket remove: ok";
    public static final String NO_PRODUCTS_WITH_THAT_ID_IN_TICKET = "No products with that ID were found in ticket";
    public static final String TICKET_PRINT_OK = "ticket print: ok";
    public static final String UNKNOWN_COMMAND = "Unknown command";



    /**
     * Metodo que busca un Producto en concreto en la tienda con el ID proporcionado y, si lo encuentra, lo devuelve
     * Si no encuentra ningun producto con ese ID, devuelve null
     * @param products: ArrayList donde se va a buscar el producto.
     * @param id: Entero que indica el ID del producto que se quiere buscar en la tienda
     * @return Objeto de clase Producto, es null si no se encuentra el Producto buscado
     */
    public static Product busquedaProductoPorID(ArrayList<Product> products, int id) {
        Product resultado = null;
        boolean existe = false;
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext() && !existe) {
            Product product = iterator.next();
            if (product.getID() == id) {
                resultado = product;
                existe = true;
            }
        }
        return resultado;
    }

    public static Cashier buscarCajeroPorID(ArrayList<Cashier> cashiers, String id) {
        Cashier resultado = null;
        boolean existe = false;
        Iterator<Cashier> iterator = cashiers.iterator();
        while (iterator.hasNext() && !existe) {
            Cashier cashier = iterator.next();
            if (cashier.getId().equals(id)) {
                resultado = cashier;
                existe = true;
            }
        }
        return resultado;
    }
}
