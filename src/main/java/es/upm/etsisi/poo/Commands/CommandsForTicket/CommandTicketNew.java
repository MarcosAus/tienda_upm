package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Persistence.PersistenceManager;
import es.upm.etsisi.poo.Ticket.TicketParam;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Users.User;

public class CommandTicketNew implements Command {
    private final TicketHandler ticketHandler;
    private final UserHandler userHandler;
    String name;

    public CommandTicketNew(String name, TicketHandler ticketHandler, UserHandler userHandler) {
        this.name = name;
        this.ticketHandler = ticketHandler;
        this.userHandler = userHandler;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 4 || args.length > 6) {
            System.out.println(Comments.LENGTH_WRONG);
            return;
        }

        int idx = 2;
        Integer customId = null;

        if (Utilities.isNumeric(args[idx])) {
            customId = Integer.parseInt(args[idx]);
            if (customId <= 1 || customId >= 999999) {
                System.out.println(Comments.ID_NOT_IN_BOUNDARIES);
                return;
            }
            idx++;
        }

        if (idx + 1 >= args.length) {
            System.out.println(Comments.LENGTH_WRONG);
            return;
        }

        String cashierId = args[idx];
        String userId = args[idx + 1];
        idx += 2;

        char ticketType = 'p';
        if (idx < args.length) {
            String flag = args[idx];
            if (flag.startsWith("-") && flag.length() == 2) {
                ticketType = flag.charAt(1);
            } else {
                System.out.println("Syntax error: Type must be -c, -p or -s");
                return;
            }
        }

        processTicketCreation(customId, cashierId, userId, ticketType);
    }

    private void processTicketCreation(Integer customId, String cashId, String userId, char type) {
        User userObj = userHandler.getUserById(userId);
        User cashObj = userHandler.getUserById(cashId);

        if (userObj == null || cashObj == null) {
            System.out.println(Comments.USER_NOT_FOUND);
            return;
        }

        Client client = userObj.getThisCli();
        Cashier cashier = cashObj.getThisCash();

        if (client == null || cashier == null) {
            System.out.println(Comments.ID_NOT_MATCHES_COMMAND);
            return;
        }

        // Detectar tipo de cliente (Si ID empieza por 'B' es Business)
        boolean isBusiness = client.getId().startsWith("B");
        int finalId = 0;

        // Validaciones Específicas
        if (isBusiness) {
            if (type == 'p') {
                System.out.println("Error: Business tickets accept only Services (-s) or Combined (-c).");
                return;
            }
        } else {
            // Cliente normal
            if (type != 'p') {
                System.out.println(Comments.CLIENTS_CANT_SELECT_TYPE);
                return;
            }
        }

        // Creación del Ticket (Usando Handler)
        if (isBusiness) {
            if (customId != null ){
                if(type=='s'){
                    finalId= ticketHandler.newTicketBusinessService(customId);
                }else{
                    finalId = ticketHandler.newTicketBusinessCombined(customId);
                }
            }else{
                if(type=='s'){
                    finalId = ticketHandler.newTicketBusinessService();
                }else{
                    finalId = ticketHandler.newTicketBusinessCombined();
                }
            }
        } else {
            finalId = (customId != null) ? ticketHandler.newTicketClient(customId) : ticketHandler.newTicketClient();
        }

        // Aviso si el ID estaba ocupado
        if (customId != null && finalId != customId) {
            System.out.println("ID requested was busy. Assigned ID: " + finalId);
        }

        TicketParam<?> ticket = ticketHandler.getTicket(finalId);

        if (ticket != null) {

            // Finalizar operación
            cashier.addTicket(ticket);
            client.addTicket(ticket);

            PersistenceManager.saveTickets(ticketHandler);
            PersistenceManager.saveUsers(userHandler);

            System.out.println(Comments.TICKET_NEW);
            ticket.printTicket();
        }
    }








        //El metodo se subdivide en 4 comandos distintos. Uno de longitud 4 y otro de 6 y los 2 restantes en funcion de args[2].
       /* if (args.length == 5) {
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

                            if (actClient.getId().startsWith("B")) {
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

                        if (actClient.getId().startsWith("B")) {
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
        }*/
}
