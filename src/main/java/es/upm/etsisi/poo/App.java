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
    private static ArrayList<Producto> productList = new ArrayList<>();
    private static int MAX_LIST = 200;
    private static Ticket ticket;
    private static int MAX_IN_TICKET = 100;

    public static void main(String[] args) {
        ticket = new Ticket();
        System.out.println("Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.");
        boolean continuar = true;
        Scanner sc = new Scanner(System.in); // Scanner sc = new Scanner(New File((args))
        do {
            System.out.print("tUPM> ");
            String entrada = sc.nextLine();
            // Aunque pueda dar miedo, este split separa por espacios
            // ignorando los espacios que haya dentro de las comillas dobles
            String[] comando = entrada.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            continuar = ejecutarComando(comando, continuar);
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
                    switch (comando[1]) {
                        // El usuario quiere añadir un producto
                        case "add":
                            try {
                                // Para ver si el comando esta bien Y hay espacio en la lista
                                if (comando.length == 6 && productList.size() < MAX_LIST) {
                                    id = Integer.parseInt(comando[2]);
                                    String nombre = comando[3];
                                    Producto.Categoria categoria = Producto.Categoria.getCategoria(comando[4]);

                                    if (categoria != null) {
                                        double precio = Double.parseDouble(comando[5]);
                                        hacerAddProd(id, nombre, categoria, precio);
                                    } else System.out.println("Category is wrong");
                                }

                                // Si no deja es porque el comando esta mal o porque la lista esta llena
                                // Son dos IFs a continuacion porque puede darse que ambas condiciones se cumplan
                                else {
                                    if (comando.length != 6) {
                                        System.out.println("Command format is wrong");
                                    }
                                    if (productList.size() == MAX_LIST) {
                                        System.out.println("List of Products is full, cannot add any more Products");
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("ID or Price is not a number");
                            }
                            break;

                        case "list":
                            hacerList(productList);
                            break;

                        case "update":
                            try {
                                id = Integer.parseInt(comando[2]);
                                String campo = comando[3];
                                String valor = comando[4];
                                hacerUpdate(id, campo, valor);
                            } catch (NumberFormatException e) {
                                System.out.println("ID is not a number");
                            }
                            break;

                        case "remove":
                            try {
                                id = Integer.parseInt(comando[2]);
                                hacerRemoveProd(productList, id);
                            } catch (NumberFormatException e) {
                                System.out.println("ID is not a number");
                            }
                            break;

                        default:
                            System.out.println("Unknown prod command");
                            break;
                    }
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
                                if (comando.length == 4 && productList.size() < MAX_IN_TICKET) {
                                    id = Integer.parseInt(comando[2]);
                                    int cantidad = Integer.parseInt(comando[3]);
                                    ticket.addProducto(busquedaProductoPorID(id), cantidad);
                                    System.out.println("ticket add: ok");
                                }
                                else {
                                    System.out.println("Cannot add Products to a Ticket because:");
                                    if (comando.length != 4) {
                                        System.out.println("Command format is wrong");
                                    }
                                    if (productList.size() == MAX_IN_TICKET) {
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
                            // TODO
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


    /**
     * Metodo que añade Productos a la tienda si no existen ya en el catalogo
     * Busca el Producto que se quiere añadir en la tienda y si lo encuentra informa al usuario de que ya existe
     * Si no encuentra nada con el mismo ID, añade el Producto con los atributos pasados como argumentos
     * @param id        Número Identificador del producto a añadir
     * @param nombre    String nombre del producto a añadir
     * @param categoria Atributo de enum Categoria de Producto
     * @param precio    Precio unitario del producto
     */
    public static void hacerAddProd(int id, String nombre, Producto.Categoria categoria, double precio) {
        if (id >= 0) {
            Producto existente = busquedaProductoPorID(id);
            boolean encontrado = existente != null;

            if (!encontrado) {
                if (nombre.isEmpty() || nombre.length() > 100) System.out.println("Name length is incorrect");
                else if (precio < 0) System.out.println("Price cannot be negative");
                else {
                    Producto producto = new Producto(id, nombre, categoria, precio);
                    productList.add(producto);
                    System.out.println(producto.productoToString() + "\nprod add: ok");
                }
            }
            else System.out.println("A Product with the same ID already exists:\n" + existente.productoToString());
        }
        else System.out.println("ID cannot be negative");
    }

    /**
     * Metodo que busca e imprime por pantalla todos los productos disponibles en la tienda
     * @param listaProductos ArrayList de productos del catalogo de la tienda
     */
    public static void hacerList(ArrayList<Producto> listaProductos) {
        System.out.println("Catalog:");
        Iterator<Producto> iterator = listaProductos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            System.out.println("    " + producto.productoToString());
        }
        System.out.println("prod list: ok");
    }

    /**
     * Metodo para buscar un producto en el catalogo de la tienda y cambiar uno de sus datos
     * El dato a cambiar puede ser el nombre, la categoria o el precio
     * Si no encuentra el producto, informa al usuario de que no existe en la lista
     * @param id Entero que sirve como ID del producto que se quiere buscar
     * @param campo Dato del producto que se quiere actualizar (nombre, categoria o precio)
     * @param valor Nuevo valor que se quiere asignar al dato seleccionado en el parametro campo
     */
    public static void hacerUpdate(int id, String campo, String valor) {
        Producto producto = busquedaProductoPorID(id);

        if (producto != null) {
            switch (campo.toUpperCase()) {
                case "NAME":
                    producto.setNombre(valor);
                    break;
                case "CATEGORY":
                    Producto.Categoria categoria = Producto.Categoria.getCategoria(valor);
                    if (categoria != null) producto.setCategoria(categoria);
                    else System.out.println("Category is wrong");
                    break;
                case "PRICE":
                    try {
                        producto.setPrecio(Double.parseDouble(valor));
                    } catch (NumberFormatException e) {
                        System.out.println("Input Price is not a number");
                    }
                    break;
                default:
                    System.out.println("Field to change must be NAME|CATEGORY|PRICE");
            }
            System.out.println(producto.productoToString() + "\nprod update: ok");
        }

        else System.out.println("Product not found");
    }

    /**
     * Metodo que busca un producto en la tienda y si lo encuentra lo borra e informa al usuario
     * Si no lo encuentra, informa al usuario de que no existe el producto
     * @param arrayProductos Array que contiene los productos en stock de la tienda
     * @param id Entero que indica el ID del producto que se quiere añadir
     */
    public static void hacerRemoveProd(ArrayList<Producto> arrayProductos, int id) {
        Producto producto = busquedaProductoPorID(id);
        if (producto != null) {
            arrayProductos.remove(producto);
            System.out.println("prod remove: ok");
        }
        else System.out.println("Product not found");
    }

    /**
     * Metodo que busca un Producto en concreto en la tienda con el ID proporcionado y, si lo encuentra, lo devuelve
     * Si no encuentra ningun producto con ese ID, devuelve null
     * @param id Entero que indica el ID del producto que se quiere buscar en la tienda
     * @return Objeto de clase Producto, es null si no se encuentra el Producto buscado
     */
    public static Producto busquedaProductoPorID(int id) {
        Producto producto = null;
        Iterator<Producto> iterator = productList.iterator();
        boolean encontrado = false;
        while (iterator.hasNext() && !encontrado) {
            producto = iterator.next();
            if (producto.getID() == id) {
                encontrado = true;
            }
        }
        return producto;
    }
}
