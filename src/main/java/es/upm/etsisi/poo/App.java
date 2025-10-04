package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/** COSAS QUE FALTAN POR HACER (MARCAR CON UNA X LAS QUE SE VAYAN COMPLETANDO):
 * METODOS PRINT Y REMOVE DE LA CLASE TICKET
 * SACAR LOS SWITCH DE PROD Y TICKET DEL MEGASWITCH DE EJECUTARCOMANDO
 * AÑADIR JAVADOC DE LAS FUNCIONES DEL RESTO DE CLASES, SOLO ESTAN COMENTADAS POR ENCIMA
 * METODO PARA LEER Y OTRO PARA ESCRIBIR FICHEROS, SERIA UTIL A LA HORA DE PROBAR
 * COMPRAR PAN, LECHE Y HUEVOS
 */

public class App {
    private static int MAX_LIST = 200;
    private static Inventario productList;
    private static Ticket ticket;
    private static int MAX_IN_TICKET = 100;

    public static void main(String[] args) {
        ticket = new Ticket();
        productList = new Inventario();

        System.out.println("Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.");
        boolean continuar = true;
        Scanner sc = new Scanner(System.in); // Scanner sc = new Scanner(New File((args))
        do {
            System.out.print("tUPM> ");
            String entrada = sc.nextLine();
            // Aunque pueda dar miedo, este split separa por espacios
            // ignorando los espacios que haya dentro de las comillas dobles
            String[] comando = entrada.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            continuar = ejecutarComando(comando, true);
        } while (continuar);

    }

    /**
     * Metodo que se llama a lo largo del programa para leer la linea de comandos y ejecutar el resto de metodos
     * del programa, devuelve un boolean para decirle al bucle while del main si debe seguir ejecutandose
     * @param comando Array de Strings obtenido tras separar por espacios la linea del input
     * @param continuar Variable booleana que determina si el programa debe seguir ejecutandose
     * @return El valor true/false de la variable continuar
     */
    public static boolean ejecutarComando(String[] comando, boolean continuar) {
        int id;
        // El comando solo tiene una palabra que DEBE ser help o exit, en caso contrario esta mal
        if (comando.length < 2) {
            if (comando[0].equalsIgnoreCase("help")) {
                mostrarComandos();
            }
            else if (comando[0].equalsIgnoreCase("exit")) {
                continuar = false;
            }
            else {
                System.out.println("Command not valid");
            }
        }

        // El comando tiene 2 o mas palabras por lo que tiene que ser prod, ticket o echo
        else {
            switch (comando[0]) {
                // Comandos tipo prod
                case "prod":
                    comandosProdAux(comando);
                    break;
                // Comandos tipo ticket
                case "ticket":
                    switch (comando[1]) {
                        case "new":
                            ticket = new Ticket();
                            System.out.println("ticket new: ok");
                            break;

                        case "add":
                            try {
                                if (comando.length == 4 && productList.getCapacidad() < MAX_IN_TICKET) {
                                    id = Integer.parseInt(comando[2]);
                                    int cantidad = Integer.parseInt(comando[3]);
                                    ticket.addProducto(Utilidades.busquedaProductoPorID(productList.getLista(), id), cantidad);
                                    hacerPrintTicket();
                                    System.out.println("ticket add: ok");
                                }
                                else {
                                    System.out.println("Cannot add Products to a Ticket because:");
                                    if (comando.length != 4) {
                                        System.out.println("Command format is wrong");
                                    }
                                    if (ticket.getNumeroProductos() == MAX_IN_TICKET) {
                                        System.out.println("Ticket is full, cannot add any more Products");
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("ID or amount is not a number");
                            }
                            break;

                        case "remove":
                            // TODO
                            break;

                        case "print":
                            hacerPrintTicket();
                            System.out.println("ticket print: ok");
                            break;

                        default:
                            System.out.println("Unknown ticket command");
                            break;
                    }
                    break;

                // El usuario quiere que la consola haga de papagayo (que repita lo que ponga)
                case "echo":
                    System.out.println(comando[0] + comando[1]);
                    break;
            }
        } return continuar;
    }

    /**
     * Metodo auxiliar para acceder al repertorio de comandos relacionados con los productos de la tienda
     * Revisa el segundo campo del array comando y decide que comando debe ejecutar
     * Si es "add" debe añadir un producto
     * Si es "list" debe imprimir una lista de productos de la tienda
     * Si es "update" debe actualizar un producto
     * Si es "remove" debe eliminar un producto de la tienda
     * @param comando Array de Strings que contiene las instrucciones para prod, se revisa el segundo campo
     *                para ver si es un add, list, update o remove
     */
    public static void comandosProdAux(String[] comando) {
        int id;
        switch (comando[1]) {
            // Añadir un producto si no existe
            case "add":
                try {
                    // Para ver si el comando esta bien Y hay espacio en la lista
                    if (comando.length == 6 && productList.getCapacidad() < MAX_LIST) {
                        id = Integer.parseInt(comando[2]);
                        String nombre = comando[3];
                        Producto.Categoria categoria = Producto.Categoria.getCategoria(comando[4]);

                        if (categoria != null) {
                            double precio = Double.parseDouble(comando[5]);
                            productList.addProduct(id, nombre, categoria, precio);
                        } else System.out.println("Category is wrong");
                    }

                    // Si no deja es porque el comando esta mal o porque la lista esta llena
                    // Son dos IFs a continuacion porque puede darse que ambas condiciones se cumplan
                    else {
                        if (comando.length != 6) {
                            System.out.println("Command length is wrong");
                        }
                        if (productList.getCapacidad() == MAX_LIST) {
                            System.out.println("List of Products is full, cannot add any more Products");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID or Price is not a number");
                }
                break;

            // Listar los productos de la tienda
            case "list":
                productList.printList();
                break;

            // Actualizar un producto
            case "update":
                try {
                    id = Integer.parseInt(comando[2]);
                    String campo = comando[3];
                    String valor = comando[4];
                    productList.updateProduct(id, campo, valor);
                } catch (NumberFormatException e) {
                    System.out.println("ID is not a number");
                }
                break;

            // Eliminar un producto
            case "remove":
                try {
                    id = Integer.parseInt(comando[2]);
                    productList.removeProduct(id);
                } catch (NumberFormatException e) {
                    System.out.println("ID is not a number");
                }
                break;
        }
    }

    /**
     * Metodo que imprime por pantalla todos los comandos disponibles y las categorias de los productos
     * Tambien informa del descuento que se aplica si hay 2 o mas unidades de un producto de la misma cateoria
     */
    public static void mostrarComandos() {
        System.out.println("""
                Commands
                    prod add <id> "<name>" <category> <price>
                    prod list
                    prod update <id> NAME|CATEGORY|PRICE <value>
                    prod remove <id>
                    ticket new
                    ticket add <prodId> <quantity>
                    ticket remove <prodId>
                    ticket print
                    echo "<texto>"
                    help
                    exit
                   \s
                Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS 
                Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.""");
    }

    public static void hacerPrintTicket() {
        ArrayList<Producto> productos  = productList.getLista();
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            System.out.println(producto.productoToString()+"**discount -"+producto.descuento());
        }
        double precio = calcularPrecio();
        System.out.println("Total price: "+ precio);
        double descuentos = calcularDescuentoTotal();
        System.out.println("Total discount: "+ descuentos);
        System.out.println("Final price: " + (precio - descuentos));
    }

    public static double calcularPrecio() {
        double precio = 0;
        Iterator<Producto> iterator = ticket.getProductos().iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            precio += producto.getPrecio();
        }
        return precio;
    }

    public static double calcularDescuentoTotal() {
        double descuento = 0;
        int[] cantidadProductos = ticket.getCantidadProductoCategoria();
        Iterator<Producto> iterator = ticket.getProductos().iterator();
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
}
