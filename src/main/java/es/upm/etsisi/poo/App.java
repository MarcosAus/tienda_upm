package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class App {
    private static ArrayList<Producto> productList = new ArrayList<>();
    private static int MAX_LIST = 200;
    private static ArrayList<Producto> ticketProductos = new ArrayList<>();
    private static int MAX_IN_TICKET = 100;

    public static void main(String[] args) {
        System.out.println("Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.");
        boolean continuar = true;
        do {
            System.out.print("tUPM> ");
            Scanner sc = new Scanner(System.in); // Scanner sc = new Scanner(New File((args))
            String entrada = sc.nextLine();
            // Aunque pueda dar miedo, este split separa por espacios
            // ignorando los espacios que haya dentro de las comillas
            String[] comando = entrada.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            continuar = ejecutarComando(comando, true);
        } while (continuar);

    }

    public static boolean ejecutarComando(String[] comando, boolean continuar) {
        int id;
        if (comando.length < 2) {
            if (comando[0].equalsIgnoreCase("help")) {
                mostrarComandos();
            } else if (comando[0].equalsIgnoreCase("exit")) {
                continuar = false;
            } else {
                System.out.println("Command not valid");
            }
        } else {
            // Comprueba si el comando es prod o list
            switch (comando[0]) {
                case "prod":
                    // Comprueba las opciones de prod
                    switch (comando[1]) {
                        case "add":
                            try {
                                if (comando.length == 6 && productList.size() < MAX_LIST) {
                                    id = Integer.parseInt(comando[2]);
                                    String nombre = comando[3];
                                    Producto.Categoria categoria = Producto.Categoria.getCategoria(comando[4]);
                                    if  (categoria != null) {
                                        double precio = Double.parseDouble(comando[5]);
                                        hacerAddProd(id, nombre, categoria, precio);
                                    } else System.out.println("Category is wrong");
                                } else if (comando.length != 6) {
                                    System.out.println("Command format wrong");
                                } else {
                                    System.out.println("List of Products is full, cannot add any more Products");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("ID or Price is not a number");
                            }
                            break;
                        case "list":
                            hacerList(productList);

                            break;
                        case "update":
                            id = Integer.parseInt(comando[2]);
                            String campo = comando[3];
                            String valor = comando[4];
                            hacerUpdate(id, campo, valor);
                            break;
                        case "remove": {
                            id = Integer.parseInt(comando[2]);
                            hacerRemoveProd(productList, id);
                        }
                    }
                    break;
                case "ticket":
                    if (comando[1].equalsIgnoreCase("new")) {

                    } else if (comando[1].equalsIgnoreCase("list")) {

                    } else if (comando[1].equalsIgnoreCase("remove")) {

                    } else if (comando[1].equalsIgnoreCase("print")) {

                    }
                    break;
                case "echo":
                    System.out.println("echo " + comando[1]);
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
                    exit
                   \s
                Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS 
                Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.""");
    }


    /**
     * @param id        Numero Identificador del producto a añadir
     * @param nombre    String nombre del producto a añadir
     * @param categoria Atributo de enum Categoria de Producto
     * @param precio    Precio unitario del producto
     * Metodo que añade Productos a la tienda
     */
    public static void hacerAddProd(int id, String nombre, Producto.Categoria categoria, double precio) {
        if (id > 0) {
            boolean encontrado = false;
            Iterator<Producto> iterator = productList.iterator();
            while (iterator.hasNext() && !encontrado) {
                Producto producto = iterator.next();
                if (producto.getID() == id) {
                    encontrado = true;
                    System.out.println("A Product with the same ID already exists");
                }
            }
            if (!encontrado) {
                if (nombre.isEmpty() || nombre.length() > 100) System.out.println("Name length is incorrect");
                else if (precio < 0) System.out.println("Price cannot be negative");
                else {
                    Producto producto = new Producto(id, nombre, categoria, precio);
                    productList.add(producto);
                    System.out.println(producto.productoToString() + "\nprod add: ok");
                }
            }
        } else System.out.println("ID cannot be negative");
    }

    // Muestra la lista de productos.
    public static void hacerList(ArrayList<Producto> listaProductos) {
        System.out.println("Catalog:");
        Iterator<Producto> iterator = listaProductos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            System.out.println("    " + producto.productoToString());
        }
    }

    //Actualiza los campos del producto seleccionado
    public static void hacerUpdate(int id, String campo, String valor) {
        for (Producto producto : productList) {
            if (producto.getID() == id) {
                switch (campo.toUpperCase()) {
                    case ("NAME"):
                        producto.setNombre(valor);
                        break;
                    case ("CATEGORY"):
                        producto.setCategoria(Producto.Categoria.valueOf(campo.toUpperCase()));
                        break;
                    case ("PRICE"):
                        producto.setPrecio(Double.parseDouble(valor));
                        break;
                }
            }
        }
    }

    //Elimina uno de los productos de la tienda
    public static void hacerRemoveProd(ArrayList<Producto> arrayProductos, int id) {
        Iterator<Producto> iterator = arrayProductos.iterator();
        boolean encontrado = false;
        while (iterator.hasNext() && !encontrado) {
            Producto producto = iterator.next();
            if (producto.getID() == id) {
                arrayProductos.remove(producto);
                encontrado = true;
            }
        }
    }
}
