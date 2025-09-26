package es.upm.etsisi.poo;

import java.util.Scanner;

public class App {
    private static Producto[] productList;
    private static int listSize = 0;

    public App(int capacidadProductos) {
        productList = new Producto[capacidadProductos];
        listSize = 0;
    }

    public static void main(String[] args) {
        App app = new App(100);
        System.out.println("Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.");
        boolean continuar = true;
        do {
            System.out.print("tUPM> ");
            Scanner sc = new Scanner(System.in);
            String entrada = sc.nextLine();
            // Aunque pueda dar miedo, este split separa por espacios
            // ignorando los espacios que haya dentro de las comillas
            String[] comando = entrada.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            continuar = ejecutarComando(comando, true);
        } while (continuar);

    }

    public static boolean ejecutarComando(String[] comando, boolean continuar) {
        if (comando.length < 2) {
            if (comando[0].equals("help")) {
                mostrarComandos();
            } else if (comando[0].equals("exit")) {
                continuar = false;
            } else {
                System.out.println("Command not valid");
            }
        }
        else{
            switch (comando[0]) {
                case "prod":
                    switch (comando[1]) {
                        case "add":
                            if (comando.length == 6) {
                                int id  = Integer.parseInt(comando[2]);
                                String nombre = comando[3];
                                Producto.Categoria categoria = Producto.Categoria.valueOf(comando[4]);
                                double precio = Double.parseDouble(comando[5]);
                                hacerAdd(id,nombre,categoria,precio);
                            }
                        break;
                        case "list":
                            hacerList();

                        break;
                        case"update":
                            hacerUpdate();
                        break;
                        case "remove": {
                            hacerRemove();
                        }
                    }
                    break;
                case "ticket":
                    if (comando[1].equals("new")) {
                    }
                    else if (comando[1].equals("list")) {
                    }
                    else if (comando[1].equals("remove")) {
                    }
                    else if (comando[1].equals("print")) {
                    }
                    break;
                default:
                        System.out.println("Command not valid");
                }
        }
        return continuar;
    }

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
                exit""");
    }


    /**
     * @param id Numero Identificador del producto a añadir
     * @param nombre String nombre del producto a añadir
     * @param categoria
     * @param precio
     */
    public static void hacerAdd(int id, String nombre, Producto.Categoria categoria, double precio) {
        if (id < 0) System.out.println("ID cannot be negative");
        else if (nombre.isEmpty()) System.out.println("Name cannot be empty");
        else if (nombre.length() > 100) System.out.println("Name cannot be longer than 100 characters");
        else if (precio < 0) System.out.println("Price cannot be negative");
        else {
            Producto producto = new Producto(id, nombre, categoria, precio);
            productList[listSize] = producto;
            listSize++;
        }
    }

    // Muestra la lista de productos.
    public static void hacerList (){

    }

    //
    public static void hacerUpdate (){

    }

    //Elimina uno de los productos del ticket
    public static void hacerRemove (){

    }
}
