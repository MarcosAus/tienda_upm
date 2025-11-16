package es.upm.etsisi.poo;

import java.util.Scanner;
import java.io.*;

public class App {
    private static Inventory productList; // Array de productos disponibles en la Tienda
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
        ticket = new Ticket(); // Al haber modificado el constructor, hay que rehacer todas las instancias de Ticket /Marcos
        productList = new Inventory();

        System.out.println("Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.");

        if (args.length > 0) {
            leerFicheros(args[0]);
            return;
        }

        boolean continuar;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("tUPM> ");
            String entrada = sc.nextLine();
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
        if (comando.length < 2) {
            if (primeraPalabra.equals("help")) {
                mostrarComandos();
            }
            else if (primeraPalabra.equals("exit")) {
                System.out.println("Closing application. \nGoodbye!");
                continuar = false;
            }
            else {
                System.out.println(Utilities.UNKNOWN_COMMAND);
            }
        }

        else {
            switch (primeraPalabra) {
                case "prod":
                    comandosProdAux(comando);
                    break;
                case "ticket":
                    comandosTicketAux(comando);
                    break;

                case "echo":
                    System.out.println("echo " + comando[1]);
                    break;

                default:
                    System.out.println(Utilities.UNKNOWN_COMMAND);
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
    /* public static void comandosProdAux(String[] comando) {
        int id;
        String segundaPalabra = comando[1].toLowerCase();

        switch (segundaPalabra) {
            case "add":
                try {
                    if (comando.length == 6 && productList.getCapacidad() < Utilities.MAX_LIST) {
                        id = Integer.parseInt(comando[2]);
                        String nombre = comando[3];
                        Product.Categoria categoria = Product.Categoria.getCategoria(comando[4]);

                        if (categoria != null) {
                            double precio = Double.parseDouble(comando[5]);
                            productList.addProduct(id, nombre, categoria, precio);
                        } else System.out.println("Category is wrong");
                    }

                    else {
                        if (comando.length != 6) {
                            System.out.println("Command length is wrong");
                        }
                        if (productList.getCapacidad() == Utilities.MAX_LIST) {
                            System.out.println("List of Products is full, cannot add any more products");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID or Price is not a number");
                }
                break;

            case "list":
                if (comando.length == 2) {
                    productList.printList();
                }
                else System.out.println("Command length is wrong");
                break;

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
    */

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

    /* public static void comandosTicketAux(String[] comando) {
        int id;
        String segundaPalabra = comando[1].toLowerCase();

        switch (segundaPalabra) {
            case "new":
                if (comando.length == 2) {
                    ticket = new Ticket();
                    System.out.println("ticket new: ok");
                } else System.out.println("Command length is wrong");
                break;

            case "add":
                try {
                    if (comando.length == 4 && productList.getCapacidad() <= Utilities.MAX_IN_TICKET) {
                        id = Integer.parseInt(comando[2]);
                        int cantidad = Integer.parseInt(comando[3]);
                        if (cantidad<=Utilities.MAX_IN_TICKET-ticket.getNumeroProductos()){
                            ticket.addProduct(Utilities.busquedaProductoPorID(productList.getLista(), id), cantidad);
                            ticket.printTicket();
                        }
                        else System.out.println("You cannot add more product than the ticket capacity allows");

                    }
                    else {
                        if (comando.length != 4) {
                            System.out.println("Command length is wrong");
                        }
                        if (ticket.getNumeroProductos() == Utilities.MAX_IN_TICKET) {
                            System.out.println("Ticket is full, cannot add any more products");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID or amount is not a number");
                }
                break;

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

            case "print":
                if (comando.length == 2) {
                    ticket.printTicket();
                    System.out.println("ticket print: ok");
                } else System.out.println("Command length is wrong");
                break;

            default:
                System.out.println("Unknown ticket command");
                break;
        }
    }
    */

    public static void comandosClienteAux(String[] comando) {
        String id;
        String segundaPalabra = comando[1].toLowerCase();
        switch(segundaPalabra) {
            case  "add":
                try {
                  if (comando.length == 6) {
                      String clientName = comando[2].toLowerCase();
                      String clientDNI = comando[3].toLowerCase();
                      String clientMail = comando[4].toLowerCase();
                      String cashId = comando[5].toLowerCase();
                      Clientes.addClient(clientName, clientDNI, clientMail, cashId); //Provisional, no sabemos dónde irá el metodo "addClient" /M
                  } else {
                      System.out.println("Command length is wrong");
                  }
                } catch(IllegalArgumentException e) {
                    System.out.println("Name is not text");
                }
                break;
            case "remove":
                if (comando.length == 3) {
                    String clientDNI = comando[2].toLowerCase();
                    Clientes.removeClient(clientDNI);
                } else System.out.println("Command length is wrong");
                break;
            case "list":
                if (comando.length == 2) {
                    Clientes.printClientList();
                } else System.out.println("Command length is wrong");
                break;
        }
    }

    public static void comandosCashierAux(String[] comando) { //Copiado y pegado de Cliente no hecho nada aún
        String id;
        String segundaPalabra = comando[1].toLowerCase();
        switch(segundaPalabra) {
            case  "add":
                try {
                    if (comando.length == 6) {
                        String clientName = comando[2].toLowerCase();
                        String clientDNI = comando[3].toLowerCase();
                        String clientMail = comando[4].toLowerCase();
                        String cashId = comando[5].toLowerCase();
                        Clientes.addClient(clientName, clientDNI, clientMail, cashId); //Provisional, no sabemos dónde irá el metodo "addClient" /M
                    } else {
                        System.out.println("Command length is wrong");
                    }
                } catch(IllegalArgumentException e) {
                    System.out.println("Name is not text");
                }
                break;
            case "remove":
                if (comando.length == 3) {
                    String clientDNI = comando[2].toLowerCase();
                    Clientes.removeClient(clientDNI);
                } else System.out.println("Command length is wrong");
                break;
            case "list":
                if (comando.length == 2) {
                    Clientes.printClientList();
                } else System.out.println("Command length is wrong");
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
            System.out.println(e.getMessage());
        }
    }
}
