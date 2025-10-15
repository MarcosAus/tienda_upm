package es.upm.etsisi.poo;

import java.util.Scanner;
import java.io.*;

public class App {
    private static final int MAX_LIST = 200; // Número máximo de productos en la Tienda
    private static final int MAX_IN_TICKET = 100; //Número máximo de productos en el Ticket
    private static Inventario productList; // Array de productos disponibles en la Tienda
    private static Ticket ticket; //Ticket donde se añadirán los productos deseados

    /**
     * Metodo principal del programa, inicializa las listas que contendran los productos de inventario y ticket
     * Saluda al usuario y comprueba si el programa ha recibido un archivo de texto como argumento
     * Si no hay argumentos, inicia una sesion interactiva donde el usuario debe introducir comandos manualmente
     * La variable continuar depende de los comandos que introduzca el usuario
     * @param args Puede contener un fichero que posea comandos predeterminados que se ejecutaran al inicio
     *             de la app. Si este parametro esta vacio el programa se inicializara normalmente.
     */
    public static void main(String[] args) {
        ticket = new Ticket();
        productList = new Inventario();

        System.out.println("Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.");

        if (args.length > 0) {
            leerFicheros(args[0]);
            System.out.println("Finished reading commands from file: " + args[0]);
            return;
        }

        boolean continuar;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("tUPM> ");
            String entrada = sc.nextLine();
            // Aunque pueda dar miedo, este split separa por espacios
            // ignorando los espacios que haya dentro de las comillas dobles
            String[] comando = entrada.trim().split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
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
        String primeraPalabra = comando[0].toLowerCase();
        // El comando solo tiene una palabra que DEBE ser help o exit, en caso contrario esta mal
        if (comando.length < 2) {
            if (primeraPalabra.equals("help")) {
                mostrarComandos();
            }
            else if (primeraPalabra.equals("exit")) {
                System.out.println("Closing application. \nGoodbye!");
                continuar = false;
            }
            else {
                System.out.println("Unknown command");
            }
        }

        // El comando tiene 2 o mas palabras por lo que tiene que ser prod, ticket o echo
        else {
            switch (primeraPalabra) {
                // Comandos tipo prod
                case "prod":
                    comandosProdAux(comando);
                    break;
                // Comandos tipo ticket
                case "ticket":
                    comandosTicketAux(comando);
                    break;

                // El usuario quiere que la consola haga de papagayo (que repita lo que ponga)
                case "echo":
                    System.out.println("echo " + comando[1]);
                    break;

                default:
                    System.out.println("Unknown command");
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
     * @param comando Array de Strings que contiene las instrucciones para prod, se revisa el segundo elemento
     *                para ver si es un add, list, update o remove
     */
    public static void comandosProdAux(String[] comando) {
        int id;
        String segundaPalabra = comando[1].toLowerCase();

        switch (segundaPalabra) {
            // Añadir un producto a la tienda si no existe
            case "add":
                try {
                    // Para ver si el comando esta bien Y hay espacio en la lista
                    if (comando.length == 6 && productList.getCapacidad() <= MAX_LIST) {
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
                            System.out.println("List of Products is full, cannot add any more products");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID or Price is not a number");
                }
                break;

            // Listar los productos de la tienda
            case "list":
                if (comando.length == 2) {
                    productList.printList();
                }
                else System.out.println("Command length is wrong");
                break;

            // Actualizar un producto de la lista de la tienda
            case "update":
                if (comando.length == 5) {
                    try {
                        id = Integer.parseInt(comando[2]);
                        String campo = comando[3];
                        String valor = comando[4];
                        productList.updateProduct(id, campo, valor);
                    } catch (NumberFormatException e) {
                        System.out.println("ID is not a number");
                    }
                } else System.out.println("Command length is wrong");
                break;

            // Eliminar un producto de la lista de la tienda
            case "remove":
                if (comando.length == 3) {
                    try {
                        id = Integer.parseInt(comando[2]);
                        productList.removeProduct(id);
                    } catch (NumberFormatException e) {
                        System.out.println("ID is not a number");
                    }
                }
                else System.out.println("Command length is wrong");
                break;
            default:
                System.out.println("Unknown prod command");
                break;
        }
    }

    /**
     * Metodo auxiliar para manejar el repertorio de comandos relacionados con los ticket
     * Revisa el segundo campo del array comando para decidir que comando debe ejecutar
     * Si es "new" debe iniciar un ticket nuevo (borrando asi el anterior)
     * Si es "add" debe añadir productos al ticket, tras añadirlos mostrara al usuario el ticket actual
     * Si es "print" debe mostrar el estado actual del ticket, con todos los precios calculados
     * Si es "remove" debe quitar todas las existencias de un producto del ticket
     * @param comando Array de Strings que contiene las instrucciones para el comando ticket, se revisa el segundo
     *                elemento del array para decidir si es un new, add, print o remove
     */
    public static void comandosTicketAux(String[] comando) {
        int id;
        String segundaPalabra = comando[1].toLowerCase();

        switch (segundaPalabra) {
            // Se crea un ticket nuevo, si existe uno se reinicia
            case "new":
                if (comando.length == 2) {
                    ticket = new Ticket();
                    System.out.println("ticket new: ok");
                } else System.out.println("Command length is wrong");
                break;

            // Se añade un producto al ticket de los que existen en la tienda
            case "add":
                try {
                    if (comando.length == 4 && productList.getCapacidad() <= MAX_IN_TICKET) {
                        id = Integer.parseInt(comando[2]);
                        int cantidad = Integer.parseInt(comando[3]);
                        if (cantidad<=MAX_IN_TICKET-ticket.getNumeroProductos()){
                            ticket.addProduct(Producto.busquedaProductoPorID(productList.getLista(), id), cantidad);
                            ticket.printTicket();
                        }
                        else System.out.println("You cannot add more product than the ticket capacity allows");

                    }
                    else {
                        if (comando.length != 4) {
                            System.out.println("Command length is wrong");
                        }
                        if (ticket.getNumeroProductos() == MAX_IN_TICKET) {
                            System.out.println("Ticket is full, cannot add any more products");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID or amount is not a number");
                }
                break;

            // Se quitan todas las existencias de un producto del ticket
            case "remove":
                if (comando.length == 3) {
                    try {
                        id  = Integer.parseInt(comando[2]);
                        boolean eliminado = ticket.removeProduct(id);
                        if (eliminado) System.out.println("ticket remove: ok");
                        else System.out.println("No products with that ID were found in ticket");
                    } catch (NumberFormatException e) {
                        System.out.println("ID is not a number");
                    }
                } else System.out.println("Command length is wrong");
                break;

            // Se imprime el ticket con lo que lleve hasta el momento y con los descuentos aplicados
            case "print":
                if (comando.length == 2) {
                    ticket.printTicket();
                    System.out.println("ticket print: ok");
                } else System.out.println("Command length is wrong");
                break;

            // Es un comando ticket que no existe en el programa
            default:
                System.out.println("Unknown ticket command");
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
                
                Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS
                Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.""");
    }

    /**
     * Metodo que procesa un fichero de texto linea por linea y ejecuta los comandos que tenga en cascada.
     * @param fichero String que sirve de nombre para identificar el fichero que se debe leer
     */
    public static void leerFicheros(String fichero) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty() || linea.trim().startsWith("#")) continue;

                String[] comando = linea.trim().split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                boolean continuar = ejecutarComando(comando, true);
                if (!continuar) {
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
