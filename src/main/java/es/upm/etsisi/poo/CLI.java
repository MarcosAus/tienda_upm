package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Commands.CommandProd;
import es.upm.etsisi.poo.Commands.CommandTicket;
import es.upm.etsisi.poo.Commands.CommandUser;

import javax.smartcardio.CommandAPDU;
import java.util.Scanner;

//Interfaz de commandos de la aplicación. Pide comandos nuevos al usuario en bucle

public class CLI {
    private final CommandProd commandsProducts;
    private final CommandTicket commandsTickets;
    private final CommandUser commandsUser;

    public CLI(CommandProd commandsProducts, CommandTicket commandsTickets, CommandUser commandsUser) {
        this.commandsProducts = commandsProducts;
        this.commandsTickets = commandsTickets;
        this.commandsUser = commandsUser;
    }



    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the ticket module App.\n" +
                "Ticket module. Type 'help' to see commands");
        boolean repeat = true;


        while (repeat) {
            System.out.print("> ");
            String actCommand = sc.nextLine(); //Usamos act para comando actual. Usamos div para cuando el comando esté dividido

            //Se comprueban los comandos "simples". Ejemplo: exit o help
            if (actCommand.equalsIgnoreCase("exit")) repeat = false;
            else if (actCommand.equalsIgnoreCase("help")) {
                mostrarComandos();
            }
            else if (actCommand.startsWith("echo " )) { //IMPORTANTE
                System.out.print(actCommand.substring(5));
            }

            //Se comprueban los comandos que necesitan ser divididos por palabras.
            else{
                dispatcherCentralCommand(actCommand);
            }



        }

        //Mensaje de salida de la aplicación
        System.out.println("Closing application.\n" +
                "Goodbye!");

    }

    //Metodo privado de CLI que se encarga de dirigir la solicitud del usuario a la cadena de comandos correspondiente en función de la primera palabra
    // que escriba el usuario.
    private void dispatcherCentralCommand(String actCommand) {

        String[] commanddiv =  sliceCommand(actCommand);


        switch (commanddiv[0].toLowerCase()) {
            case "client":
            case "cash"://Comandos user
                commandsUser.checkCommand(commanddiv, actCommand);
                break;

            case "ticket"://Comandos ticket
                commandsTickets.checkCommand(commanddiv, actCommand);
                break;

            case "product"://Comandos prod
                commandsProducts.checkCommand(commanddiv, actCommand);
                break;

        }
    }

    //Divide el comando en secciones. Cada punto del array guarda una palabra.
    public String[] sliceCommand(String args) {
        /* La siguiente línea esta sacada de chat gpt ya que no hemos dado esto en la carrera actualmente
            Dividimos la cadena por espacios, pero ignorando los espacios que están dentro de comillas.
            El lookahead (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$) comprueba que después del espacio
            haya un número par de comillas, lo que significa que estamos fuera de comillas.     */
        return args.trim().split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    //Muestra todos los comandos en la app
    //IMPORTANTE ACTUALIZAR CON LOS NUEVOS COMANDOS
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

}
