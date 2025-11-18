package es.upm.etsisi.poo;

import javax.smartcardio.CommandAPDU;
import java.util.Scanner;

//Interfaz de commandos de la aplicación. Pide comandos nuevos al usuario en bucle

public class CLI {
    private final CommandProd commandsProducts;
    private final CommandTicket commandsTickets;
    private final CommandAPDU commandsAPDU;

    public CLI(CommandProd commandsProducts, CommandTicket commandsTickets, CommandAPDU commandsAPDU) {
        this.commandsProducts = commandsProducts;
        this.commandsTickets = commandsTickets;
        this.commandsAPDU = commandsAPDU;
    }


    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the ticket module App.\n" +
                "Ticket module. Type 'help' to see commands");
        boolean repeat = true;


        while (repeat) {
            System.out.print("> ");
            String actCommand = sc.nextLine(); //Usamos act para comando actual. Usamos div para cuando el comando esté dividido

            /* El siguiente comando esta sacado de chat gpt ya que no hemos dado esto en la carrera actualmente
            Dividimos la cadena por espacios, pero ignorando los espacios que están dentro de comillas.
            El lookahead (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$) comprueba que después del espacio
            haya un número par de comillas, lo que significa que estamos fuera de comillas.     */
            String[] divCommand = actCommand.trim().split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");


            //Se comprueban los comandos "simples". Ejemplo: exit o help
            if (actCommand.equalsIgnoreCase("exit")) repeat = false;
            else if (actCommand.equalsIgnoreCase("help")) {
                mostrarComandos();
            }
            else if (divCommand[0].equalsIgnoreCase("echo")) {
                System.out.println("echo " + divCommand[1]);
            }

            //Se comprueban los comandos específicos pertenecientes a la clase abstracta Command
            else{
                //
            }



        }

        //Mensaje de salida de la aplicación
        System.out.println("Closing application.\n" +
                "Goodbye!");

    }

/*
    //Mostrará el mensaje de error correspondiente de error para la primera palabra del comando. Los mensajes especificos como letras en ints o numeros
    // incorrectos se gestionan en el propio comando.
    public void dispatchCentral(String[] divCommand) {
         El siguiente comando esta sacado de chat gpt ya que no hemos dado esto en la carrera actualmente
        Dividimos la cadena por espacios, pero ignorando los espacios que están dentro de comillas.
        El lookahead (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$) comprueba que después del espacio
        haya un número par de comillas, lo que significa que estamos fuera de comillas.


        //IMPORTANTE: He usado un switch que es en pare lo que queriamos evitar pero no se me ha ocurrido otra manera de clasificar los comandos

        switch (divCommand[0]) {
            case "help":
                mostrarComandos();
                break;
            case ""
        }
    }
*/
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
