package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;

public class Ticket {
    /**
     * Array de productos actuales en el ticket
     */
    private final ArrayList<Producto> productos;

    /**
     * Constructor de la clase Ticket
     */
    public Ticket() {
        this.productos = new ArrayList<>();
    }

    /**
     * Metodo para acceder a la lista de productos del ticket
     * @return Devuelve un ArrayList con los productos del ticket
     */
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    /**
     * Metodo que devuelve el tamaño del ticket
     * @return Devuelve el numero de productos que contiene el ticket
     */
    public int getNumeroProductos() {
        return productos.size();
    }

    /**
     * Metodo que añade productos al ticket
     * @param producto Objeto clase Producto que se quiere meter al ticket
     * @param cantidad El numero de productos que se deben añadir al ticket
     */
    public void addProduct(Producto producto, int cantidad) {
        if (producto != null) {
            for (int i = 0; i < cantidad; i++) {
                productos.add(producto);
            }
            // Este sort lo que hace es ordena alfabeticamente los productos del ticket
            productos.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
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
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto p = iterator.next();
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

        ArrayList<Producto> productos  = this.getProductos();
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();

            System.out.print(producto);
            if (tieneDescuento(producto.getCategoriaString(),descuentoPorCategoria)) {
                System.out.print("**discount -"+producto.descuento());
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
        Iterator<Producto> iterator = this.getProductos().iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();

            precio += producto.getPrecio();
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
        Iterator<Producto> iterator = this.getProductos().iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();

            if (tieneDescuento(producto.getCategoriaString(), cantidadProductos)) {
                descuento += producto.descuento();
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
        Producto producto = Utilidades.busquedaProductoPorID(productos, id);
        if  (producto != null) {
            Iterator<Producto> iterator = productos.iterator();
            while (iterator.hasNext()) {
                Producto producto1 = iterator.next();
                if (producto1.equals(producto)) {
                    System.out.println(producto1);
                    iterator.remove();
                    resultado = true;
                }
            }
        }
        return resultado;
    }
}