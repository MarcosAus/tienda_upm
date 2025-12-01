package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Commands.Command;
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
    public boolean isThisCommand(String name) {
        return name != null && name.toLowerCase().startsWith(this.name);
    }


    @Override
    public void execute(String[] args) {
        Ticket ticket;
        int idTry, idChosen;
        if (args.length == 5) {
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
                                System.out.println("The selected ids dont mach with the command. Please insert the ids of a cashier and a client.");
                            }
                            else{
                                idChosen = ticketHandler.newTicket(idTry);
                                ticket = ticketHandler.getTicket(idChosen);

                                if(idChosen !=idTry){
                                    System.out.println("A ticket already exists with that id. The ticket "+ idChosen +" has been automatically selected instead.");
                                }
                                actCashier.addTicket(ticket);
                                actClient.addTicket(ticket);
                                System.out.println(Comments.TICKET_NEW);
                            }

                        }
                        else{
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

        } else if (args.length == 4) {


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
                        System.out.println("The selected ids dont mach with the command. Please insert the ids of a cashier and a client.");
                    }
                    else {

                        idChosen = ticketHandler.newTicket();
                        ticket = ticketHandler.getTicket(idChosen);
                        actCashier.addTicket(ticket);
                        actClient.addTicket(ticket);
                        System.out.println("The ticket have" + idChosen + " as his id.");
                        System.out.println(Comments.TICKET_NEW);
                    }

                }
                else{
                    System.out.println(Comments.USER_NOT_FOUND);
                }
            }
            else {
                System.out.println(Comments.USER_NOT_FOUND);
            }
        }
        else System.out.println(Comments.LENGTH_WRONG);
    }
}
