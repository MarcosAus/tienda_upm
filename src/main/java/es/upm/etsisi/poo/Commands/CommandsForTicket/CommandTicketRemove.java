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
                Cashier cashier = userHandler.getCashiersRecord().get(args[3]);
                cashier.removeTicket(Integer.parseInt(args[2]));
                ticketHandler.removeTicket(Integer.parseInt(args[2]));
            } catch (NullPointerException e) {
                System.out.println(Utilities.CASHIER_ID_NOT_EXISTS);
            }
        } else {
            System.out.println(Utilities.LENGTH_WRONG);
        }
    }
}
