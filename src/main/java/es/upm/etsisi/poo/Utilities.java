package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;

public class Utilities {
    /**
     * Metodo que busca un Producto en concreto en la tienda con el ID proporcionado y, si lo encuentra, lo devuelve
     * Si no encuentra ningun producto con ese ID, devuelve null
     * @param productos: ArrayList donde se va a buscar el producto.
     * @param id: Entero que indica el ID del producto que se quiere buscar en la tienda
     * @return Objeto de clase Producto, es null si no se encuentra el Producto buscado
     */
    public static Producto busquedaProductoPorID(ArrayList<Producto> productos, int id) {
        Producto resultado = null;
        boolean existe = false;
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext() && !existe) {
            Producto producto = iterator.next();
            if (producto.getID() == id) {
                resultado = producto;
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
