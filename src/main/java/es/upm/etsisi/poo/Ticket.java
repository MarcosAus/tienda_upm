package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;

public class Ticket {
    private  ArrayList<Producto> productos;
    private final int MAX_PRODUCTS = 100;

    public Ticket() {
        this.productos = new ArrayList<>();
    }
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public int getNumeroProductos() {
        return productos.size();
    }


    //Añade un producto con su cantidad a ambos arrays
    //IMPORTANTE. Hay que ordenar el producto a la hora de añadirlo.
    public void addProduct(Producto producto, int cantidad) {
        if (producto != null) {
            for (int i = 0; i < cantidad; i++) {
                productos.add(producto);
            }
            // Ordena alfabeticamente los productos del ticket
            productos.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
        }
        else System.out.println("This product does not exist. No products were added");
    }
    //

    public int[] getCantidadProductoCategoria() {
        int[] resultado = new int[5];
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto p = iterator.next();
            switch (p.getCategoriaString()) {
                case "MERCH":
                    resultado[0] = resultado[0] + 1;
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

    //Devuelve false si no está el producto en el ticket. En el caso contrario devuelve true. Puedes usar también getCantidadProducto solo que este devuelve 0 si no lo encuentra.
    public boolean existeProducto(Producto producto) {
        Iterator<Producto> iterador = productos.iterator();
        while (iterador.hasNext()) {
            Producto p = iterador.next();
            if (p.equals(producto)) {
                return true;
            }
        }
        return false;
    }


    public void printTicket() {
        ArrayList<Producto> productos  = this.getProductos();
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            System.out.println(producto.productoToString()+"**discount -"+producto.descuento());
        }

        double precio = calcularPrecio();
        double descuentos = calcularDescuentoTotal();

        System.out.println("Total price: "+ precio);
        System.out.println("Total discount: "+ descuentos);
        System.out.println("Final price: " + (precio - descuentos));
    }

    public double calcularPrecio() {
        double precio = 0;
        Iterator<Producto> iterator = this.getProductos().iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            precio += producto.getPrecio();
        }
        return precio;
    }

    public double calcularDescuentoTotal() {
        double descuento = 0;
        int[] cantidadProductos = this.getCantidadProductoCategoria();
        Iterator<Producto> iterator = this.getProductos().iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            switch (producto.getCategoriaString()) {
                case "MERCH":
                    break;
                case "STATIONERY":
                    if (cantidadProductos[1] > 1) descuento += producto.descuento();
                    break;
                case "CLOTHES":
                    if (cantidadProductos[2] > 1) descuento += producto.descuento();
                    break;
                case "BOOK":
                    if (cantidadProductos[3] > 1) descuento += producto.descuento();
                    break;
                case "ELECTRONICS":
                    if (cantidadProductos[4] > 1) descuento += producto.descuento();
                    break;
            }
        }
        return descuento;
    }

    public void removeProduct(int id) {
        Producto producto = productos.get(id);
        if  (producto != null) {
            Iterator<Producto> iterator = productos.iterator();
            while (iterator.hasNext()) {
                Producto producto1 = iterator.next();
                if (producto1.equals(producto)) {
                    System.out.println(producto1.productoToString());
                    iterator.remove();
                }
            }
        }
    }





    //Devuelve la cantidad de productos

    // Elimina el producto y su cantidad de ambos arrays. Si no encuentra el producto devuelve false.
    /*public boolean removeProducto(Producto producto){
        boolean resultado = false;
        int conteo = 0;
        //Resultado es true si se encuentra el producto (se recorre el arraylist manualmente).
        while (!resultado && conteo < productos.size()){
            if (productos.get(conteo).getProducto() == producto){
                productos.remove(conteo);
                resultado = true;
            }
            conteo++;
        }
        return resultado;
    }
    */

    /*public double calcularDescuentoProducto() {
        double descuento = 0;
        if (getCantidadProductoCategoria(Producto.Categoria.getCategoria(producto.getCategoriaString())) > 1) {
            switch (producto.getCategoriaString()) {
                case "MERCH":
                    descuento = 0;
                    break;
                case "STATIONERY":
                    descuento = descuento * 0.05;
                    break;
                case "CLOTHES":
                    descuento = descuento * 0.07;
                    break;
                case "BOOK":
                    descuento = descuento * 0.1;
                    break;
                case "ELECTRONICS":
                    descuento = descuento * 0.03;
                    break;
            }
        } else {
            descuento = 0;
        }
        return descuento;
    }
}
 */
}