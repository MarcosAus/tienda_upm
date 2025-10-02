package es.upm.etsisi.poo;
/*
Esta clase solo guarda un producto y su cantidad necesaría.
Hay otras formas de realizar esta sección de la práctica. Se podría haber creado una matriz en Ticket que hiciese lo mismo,
pero de esta manera tienes directamente el nombre lo que te permite ordenar alfabéticamente más facialmente. También consideramos
la opción de tener dos arrays separados, uno para la cantidad y otro para el producto. De esta manerá se pueden usar las
posiciones de los arrays para obtener las cantidades de los productos pero esta solución es poco legible y puede llevar a errores.
IMPORTANTE: SI SE OS OCURRE OTRA COSA COMENTADLA.
*/
public class TicketLinea {
    private Producto producto;
    private int cantidad;

    //Constructor de la clase Ticket Linea
    public TicketLinea(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    //Devuelve el producto.
    public Producto getProducto() { return producto; }
    //Devuelve la cantidad de un producto.
    public int getCantidad() { return cantidad; }

    public void actualizarCantidad(int nuevaCantidad) {
        cantidad = nuevaCantidad;
    }
}

