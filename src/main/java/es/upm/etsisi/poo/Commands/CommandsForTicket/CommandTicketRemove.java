package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.TicketHandler;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;

public class CommandTicketRemove extends Command {
    private TicketHandler ticketHandler;
    private UserHandler userHandler;
    public CommandTicketRemove(String name, TicketHandler ticketHandler, UserHandler userHandler) {
        super(name);
        this.ticketHandler = ticketHandler;
        this.userHandler = userHandler;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 5) {
            try {
                //Primero se comprueban los inputs del usuario.
                int idTicket = Integer.parseInt(args[2]);
                int idProduct = Integer.parseInt(args[4]);
                Cashier cashier = userHandler.getUserById(args[3]).getThisCash();
                if (cashier != null) {
                    if(ticketHandler.getTicket(idTicket).removeProduct(idProduct)){
                        System.out.println(Comments.TICKET_REMOVED_SUCCESSFULLY);
                    }
                    else{
                        System.out.println(Comments.PRODUCT_NOT_FOUND);
                    }
                }else{
                    System.out.println(Comments.ID_NOT_OF_A_CASHIER);
                }
            } catch (NullPointerException e) {
                System.out.println(Comments.INT_NOT_NUMBER);
            }
        } else {
            System.out.println(Comments.LENGTH_WRONG);
        }
    }
}
