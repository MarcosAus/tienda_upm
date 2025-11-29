package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.CashierHandler;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.TicketHandler;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Utilities;
import jdk.jshell.execution.Util;

public class CommandTicketRemove extends Command {
    private TicketHandler ticketHandler;
    private UserHandler userHandler;
    public CommandTicketRemove(String name, TicketHandler ticketHandler, UserHandler userHandler) {
        super(name);
        this.ticketHandler = ticketHandler;
        this.userHandler = userHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
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
                        System.out.println("Ticket removed successfully");
                    }
                    else{
                        System.out.println(Utilities.PRODUCT_NOT_FOUND);
                    }
                }else{
                    System.out.println(Utilities.ID_NOT_OF_A_CASIER);
                }
            } catch (NullPointerException e) {
                System.out.println(Utilities.INT_NOT_NUMBER);
            }
        } else {
            System.out.println(Utilities.LENGTH_WRONG);
        }
    }
}
