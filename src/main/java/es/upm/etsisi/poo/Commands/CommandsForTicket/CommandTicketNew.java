package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Ticket.Ticket;
import es.upm.etsisi.poo.Ticket.TicketBusiness;
import es.upm.etsisi.poo.Ticket.TicketClient;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Users.User;

public class CommandTicketNew extends Command {
    private final TicketHandler ticketHandler;
    private final UserHandler userHandler;

    public CommandTicketNew(String name, TicketHandler ticketHandler, UserHandler userHandler) {
        super(name);
        this.ticketHandler = ticketHandler;
        this.userHandler = userHandler;
    }

    @Override
    public void execute(String[] args) {
        Ticket ticket = null;
        int idTry = 0, idChosen = 0;
        //El metodo se subdivide en 4 comandos distintos. Uno de longitud 4 y otro de 6 y los 2 restantes en funcion de args[2].
        if (args.length == 5) {
            Leght5(args, idTry, idChosen, ticket);
        } else if (args.length == 4) {
            Legth4(args, idTry, idChosen, ticket);
        } else if (args.length == 6) {
            Legth6(args, idTry, idChosen, ticket);
        } else System.out.println(Comments.LENGTH_WRONG);
    }

    private void Legth4(String[] args, int idTry, int idChosen, Ticket ticket){
        //Comando: ticket new <cash> <user>
        User actUser;
        Client actClient;
        Cashier actCashier;

        actUser = userHandler.getUserById(args[2]);
        if (actUser != null) {
            actCashier = actUser.getThisCash();
            actUser = userHandler.getUserById(args[3]);
            if (actUser != null) {
                actClient = actUser.getThisCli();
                if (actCashier == null || actClient == null) {
                    System.out.println(Comments.ID_NOT_MATCHES_COMMAND);
                }
                else {
                    if (actClient.getId().endsWith("s")) {
                        idChosen = ticketHandler.newTicketBusiness();
                        ticket = ticketHandler.getTicket(idChosen);
                        actCashier.addTicket(ticket);
                        actClient.addTicket(ticket);
                        System.out.println(Comments.TICKET_NEW);
                        ticket.printTicket();
                    }
                    else {
                        idChosen = ticketHandler.newTicketClient();
                        ticket = ticketHandler.getTicket(idChosen);
                        actCashier.addTicket(ticket);
                        actClient.addTicket(ticket);
                        System.out.println(Comments.TICKET_NEW);
                        ticket.printTicket();
                    }
                }

            } else{
                System.out.println(Comments.USER_NOT_FOUND);
            }
        } else {
            System.out.println(Comments.USER_NOT_FOUND);
        }
    }

    private void Leght5(String[] args, int idTry, int idChosen, Ticket ticket){
        try  {
            //Comando: ticket new <id> <cash> <user>
            idTry = Integer.parseInt(args[2]);
            if (idTry <= 1 || idTry >= 99999 ){

                User actUser;
                Client actClient;
                Cashier actCashier;

                actUser = userHandler.getUserById(args[3]);
                if (actUser != null) {
                    actCashier = actUser.getThisCash();
                    actUser = userHandler.getUserById(args[4]);
                    if (actUser != null) {
                        actClient = actUser.getThisCli();
                        if (actCashier == null || actClient == null) {
                            System.out.println(Comments.ID_NOT_MATCHES_COMMAND);
                        } else{

                            if (actClient.getId().endsWith("s")) {
                                idChosen = ticketHandler.newTicketBusiness(idTry);
                                ticket = ticketHandler.getTicket(idChosen);
                                if(idChosen !=idTry){
                                    System.out.println("A ticket already exists with that id. The ticket "+ idChosen +" has been automatically selected instead.");
                                }
                                actCashier.addTicket(ticket);
                                actClient.addTicket(ticket);
                                System.out.println(Comments.TICKET_NEW);
                                ticket.printTicket();
                            }
                            else{
                                idChosen = ticketHandler.newTicketClient(idTry);
                                ticket = ticketHandler.getTicket(idChosen);

                                if(idChosen !=idTry){
                                    System.out.println("A ticket already exists with that id. The ticket "+ idChosen +" has been automatically selected instead.");
                                }
                                actCashier.addTicket(ticket);
                                actClient.addTicket(ticket);
                                ticket.printTicket();
                                System.out.println(Comments.TICKET_NEW);
                            }
                        }

                    } else{
                        System.out.println(Comments.USER_NOT_FOUND);
                    }
                }
                else {
                    System.out.println(Comments.USER_NOT_FOUND);
                }

            }
            else{
                System.out.println(Comments.ID_NOT_IN_BOUNDARIES);
            }
        } catch (Exception noIdTry){
            //Comando: ticket new <cash> <user> <c|p|s>
            User actUser;
            Client actClient;
            Cashier actCashier;

            actUser = userHandler.getUserById(args[2]);
            if (actUser != null) {
                actCashier = actUser.getThisCash();
                actUser = userHandler.getUserById(args[3]);
                if (actUser != null) {
                    actClient = actUser.getThisCli();
                    if (actCashier == null || actClient == null) {
                        System.out.println(Comments.ID_NOT_MATCHES_COMMAND);
                    }
                    else {

                        if (actClient.getId().endsWith("s")) {
                            char type = args[4].charAt(0);
                            if (type != 'c' && type != 'p' && type != 's') {
                                System.out.println("Wrong types for ticket selected.");
                            }
                            else{
                                idChosen = ticketHandler.newTicketBusiness();
                                TicketBusiness ticketBusinessadd = (TicketBusiness) ticketHandler.getTicket(idChosen);
                                ticketBusinessadd.setTicketType(type);
                                ticket = ticketHandler.getTicket(idChosen);
                                actCashier.addTicket(ticket);
                                actClient.addTicket(ticket);
                                System.out.println(Comments.TICKET_NEW);
                                ticket.printTicket();
                            }
                        }
                        else {
                            System.out.println(Comments.CLIENTS_CANT_SELECT_TYPE);
                        }
                    }

                } else{
                    System.out.println(Comments.USER_NOT_FOUND);
                }
            } else {
                System.out.println(Comments.USER_NOT_FOUND);
            }

        }
    }

    private void Legth6(String[] args, int idTry, int idChosen, Ticket ticket){
        //Comando: ticket new <id> <cash> <user> <c|p|s>
        try {
            idTry = Integer.parseInt(args[2]);
            if (idTry <= 1 || idTry >= 99999 ){

                User actUser;
                Client actClient;
                Cashier actCashier;

                actUser = userHandler.getUserById(args[3]);
                if (actUser != null) {
                    actCashier = actUser.getThisCash();
                    actUser = userHandler.getUserById(args[4]);
                    if (actUser != null) {
                        actClient = actUser.getThisCli();
                        if (actCashier == null || actClient == null) {
                            System.out.println(Comments.ID_NOT_MATCHES_COMMAND);
                        } else{

                            if (actClient.getId().endsWith("s")) {
                                idChosen = ticketHandler.newTicketBusiness(idTry);
                                ticket = ticketHandler.getTicket(idChosen);

                                if(idChosen !=idTry){
                                    System.out.println("A ticket already exists with that id. The ticket "+ idChosen +" has been automatically selected instead.");
                                }
                                actCashier.addTicket(ticket);
                                actClient.addTicket(ticket);
                                ticket.printTicket();
                                System.out.println(Comments.TICKET_NEW);
                            }
                            else{
                                System.out.println(Comments.CLIENT_IS_NOT_BUSINESS);
                            }


                        }

                    } else{
                        System.out.println(Comments.USER_NOT_FOUND);
                    }
                }
                else {
                    System.out.println(Comments.USER_NOT_FOUND);
                }

            }
            else{
                System.out.println(Comments.ID_NOT_IN_BOUNDARIES);
            }

        }
        catch(Exception e){
            System.out.println(Comments.ID_NOT_NUMBER);
        }
    }
}
