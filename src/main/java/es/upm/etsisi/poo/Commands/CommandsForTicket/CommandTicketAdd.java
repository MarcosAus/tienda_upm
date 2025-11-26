package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.TicketHandler;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.User;

public class CommandTicketAdd extends Command {
    private UserHandler userHandler;
    private TicketHandler ticketHandler;
    public CommandTicketAdd(String name, UserHandler userHandler, TicketHandler ticketHandler) {
        super(name);
        this.userHandler = userHandler;
        this.ticketHandler = ticketHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
       if (args.length == 6) {
           Cashier cashier = userHandler.getCashiersRecord().get(args[3]);

       }
    }
}
