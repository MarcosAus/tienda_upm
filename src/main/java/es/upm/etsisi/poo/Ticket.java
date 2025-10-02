package es.upm.etsisi.poo;

import java.util.ArrayList;

public class Ticket {
    private final ArrayList<TicketLinea> productos;

    //Constructor de la clase ticket
    public Ticket() {
        this.productos = new ArrayList<>();
    }

    //Añade un producto con su cantidad a ambos arrays
    //IMPORTANTE. Hay que ordenar el producto a la hora de añadirlo.
    public void addProducto(Producto producto, int cantidad){
        boolean productoYaExistente = false;
        int conteo = 0;
        //Comprueba que el producto ya existe (se recorre el arraylist manualmente).
        while (!productoYaExistente && conteo < productos.size()){
            if (productos.get(conteo).getProducto() == producto){
                productoYaExistente = true;
            }
            conteo++;
        }

        if (!productoYaExistente) { //Simplemente añade el producto.
            productos.add(new TicketLinea(producto, cantidad));
        }
        else{ //Se actualiza el producto repetido sumándole la cantidad solicitada.
            productos.get(conteo).actualizarCantidad(productos.get(conteo).getCantidad()+ cantidad);
        }
    }

    //Comprueba si está el producto en el ticket. Si ese es el caso da la cantidad contenida en cantidades. Si no lo encuentra devuelve 0
    public int getCantidadProducto(Producto producto){
        int resultado = 0;
        int conteo = 0;
        //Sí se encuentra un producto igual el resultado cambia a la cantidad asociada a ese producto (se recorre el arraylist manualmente).
        while(resultado == 0 && conteo < productos.size()){
            if (productos.get(conteo).getProducto() == producto){
                resultado = productos.get(conteo).getCantidad();
            }
            conteo++;
        }
        return resultado;
    }

    //Devuelve false si no está el producto en el ticket. En el caso contrario devuelve true. Puedes usar también getCantidadProducto solo que este devuelve 0 si no lo encuentra.
    public boolean existeProducto(Producto producto){
        boolean resultado = false;
        int conteo = 0;
        //Resultado es true si se encuentra el producto (se recorre el arraylist manualmente).
        while(!resultado && conteo < productos.size()){
            if (productos.get(conteo).getProducto() == producto){
                resultado = true;
            }
            conteo++;
        }
        return false;
    }

    //Devuelve la cantidad de productos
    public int getNumeroProductos() {
        return productos.size();
    }

    // Elimina el producto y su cantidad de ambos arrays. Si no encuentra el producto devuelve false.
    public boolean removeProducto(Producto producto){
        boolean resultado = false;
        int conteo = 0;
        //Resultado es true si se encuentra el producto (se recorre el arraylist manualmente).
        while(!resultado && conteo < productos.size()){
            if (productos.get(conteo).getProducto() == producto){
                productos.remove(conteo);
                resultado = true;
            }
            conteo++;
        }
        return resultado;
    }
}
