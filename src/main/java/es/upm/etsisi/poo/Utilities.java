package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Users.Cashier;

import java.util.ArrayList;
import java.util.Iterator;

public class Utilities {
    //Constantes:

    public static final int MAX_LIST = 200; // Número máximo de productos en la Tienda
    public static final int MAX_IN_TICKET = 100; //Número máximo de productos en el Ticket
    
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
    public static int numGenerator(int cantidad) {
        int resultado=0;
        int multiplicador=1;
        for (int i = 0; i < cantidad; i++) {
            double generate = Math.random() * (10);
            resultado += (int) generate * multiplicador;
            multiplicador *= 10;
        }
        return resultado;
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
