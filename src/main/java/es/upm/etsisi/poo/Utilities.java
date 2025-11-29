package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Users.Cashier;

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
    public static final String ID_NOT_IN_BOUNDARIES = "ID needs to be between 1 and 99999";
    public static final String ID_PRICE_NOT_NUMBER = "ID or price is not a number";
    public static final String PRODUCT_LIST_FULL = "List of Products is full, cannot add any more products";
    public static final String TICKET_NEW_OK = "ticket new: ok";
    public static final String MORE_THAN_TICKET_CAPACITY = "You cannot add more product than the ticket capacity allows";
    public static final String TICKET_FULL = "Ticket is full, cannot add any more products";
    public static final String ID_AMMOUNT_NOT_NUMBER = "ID or amount is not a number";
    public static final String TICKET_REMOVE_OK = "ticket remove: ok";
    public static final String NO_PRODUCTS_WITH_THAT_ID_IN_TICKET = "No products with that ID were found in ticket";
    public static final String TICKET_PRINT_OK = "ticket print: ok";
    public static final String ID_PRICE_AMOUNT_NOT_NUMBER = "ID, price or amount is not a number";
    public static final String UNKNOWN_COMMAND = "Unknown command";
    public static final String CASHIER_ID_NOT_EXISTS = "CashId does not match any cashiers";
    public static final String CLIENT_ID_NOT_EXISTS = "ClientId does not match any clients";
    public static final String CAPACITY_REACHED = "Capacity has been reached";
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
    public static String generadorNum(int cantidad){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<cantidad; i++){
            sb.append((int)(Math.random()*10));
        }
        return sb.toString();
    }

    //Cambia el id al formato correcto como String
    public String idToString(int id){
        return String.format("%05d", id);
    }

    //Cambia el id a un int
    public int idToInt(String id){
        return Integer.parseInt(id);
    }

    public static Product busquedaProductoPorID(ArrayList<Product> listaProducts, int id) {
        Product resultado = null;
        boolean existe = false;
        Iterator<Product> iterator = listaProducts.iterator();
        while (iterator.hasNext() && !existe) {
            Product product = iterator.next();
            if (product.getId() == id) {
                resultado = product;
                existe = true;
            }
        }
        return resultado;
    }

    public static int idAleatorio(ProductHandler productHandler) {
        int idRandom = -1;
        while(idRandom == -1 || productHandler.getProduct(idRandom) != null) {
            double generate = (double) idRandom;
            generate = Math.random() * (200);
            idRandom = (int) generate;
        }
        return idRandom;
    }
}
