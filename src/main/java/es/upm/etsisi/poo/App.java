package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Commands.*;
import es.upm.etsisi.poo.Commands.CommandsForProd.*;
import es.upm.etsisi.poo.Commands.CommandsForTicket.*;
import es.upm.etsisi.poo.Commands.CommandsForUser.*;

import java.util.ArrayList;

public class App {

    private TicketHandler ticketHandler;
    private UserHandler userHandler;
    private ProductHandler productHandler;

    /**
     * Metodo principal del programa, inicializa las listas que contendran los productos de inventario y ticket
     * Saluda al usuario y comprueba si el programa ha recibido un archivo de texto como argumento
     * Si no hay argumentos, inicia una sesion interactiva donde el usuario debe introducir comandos manualmente
     * La variable continuar depende de los comandos que introduzca el usuario
     * @param args Puede contener un fichero que posea comandos predeterminados que se ejecutaran al inicio
     *             de la app. Si este parametro esta vacio el programa se inicializara normalmente.
     */
    public static void main(String[] args) {

        //Se crean las clases que contienen todos los comoandos. Están divididas en función del handler principal que usan.
        CommandProd commandsProducts = new CommandProd();
        CommandTicket commandsTickets = new CommandTicket();
        CommandUser commandsUser = new CommandUser();

        //Se crean los handlers
        ProductHandler productHandler = new ProductHandler();
        TicketHandler ticketHandler = new TicketHandler();
        UserHandler userHandler = new UserHandler();

        //Se crean los commandos
        //Los comandos deben tener un nombre igual en minusculas al comando en sí. Ej: TickedAdd tiene que tener name = "ticket add"
        //El espacio al final es importante para que no haya conflictos con otros comandos que empiecen igual.
        commandsProducts.addCommand(new CommandProdAdd("prod add ", productHandler));
        commandsProducts.addCommand(new CommandProdAddFoodMeeting("prod addFood ", productHandler));
        commandsProducts.addCommand(new CommandProdAddFoodMeeting("prod addMeeting ", productHandler));
        commandsProducts.addCommand(new CommandProdList("prod list ", productHandler));
        commandsProducts.addCommand(new CommandProdRemove("prod remove ", productHandler));
        commandsProducts.addCommand(new CommandProdUpdate("prod update ", productHandler));

        commandsTickets.addCommand(new CommandTicketAdd("ticket add ", ticketHandler, userHandler, productHandler));
        commandsTickets.addCommand(new CommandTicketList("ticket list ", ticketHandler, userHandler));
        commandsTickets.addCommand(new CommandTicketNew("ticket new ", ticketHandler, userHandler));
        commandsTickets.addCommand(new CommandTicketPrint("ticket print ", ticketHandler, userHandler));
        commandsTickets.addCommand(new CommandTicketRemove("ticket remove ", ticketHandler, userHandler));

        commandsUser.addCommand(new CommandUserRemoveCashier("cash remove ", userHandler));
        commandsUser.addCommand(new CommandUserAddCashier("cash add ", userHandler));
        commandsUser.addCommand(new CommandUserAddClient("client add ", userHandler));
        commandsUser.addCommand(new CommandUserListCash("cash list", userHandler));
        commandsUser.addCommand(new CommandUserListClients("client list", userHandler));
        commandsUser.addCommand(new CommandUserListTicketsCashier("cash tickets ", userHandler));
        commandsUser.addCommand(new CommandUserRemoveClient("client remove ", userHandler));


        CLI cli = new CLI(commandsProducts, commandsTickets, commandsUser);
        if (args.length != 0) {
            cli.start(args[0]);
        } else cli.start();

        //FALTA TOMAR EN CUENTA LOS ARCHIVOS EXTERNOS
    }
}
