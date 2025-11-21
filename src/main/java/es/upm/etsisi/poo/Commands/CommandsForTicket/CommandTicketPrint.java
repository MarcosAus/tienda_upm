package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Commands.CommandTicket;
import es.upm.etsisi.poo.TicketHandler;

public class CommandTicketPrint extends Command {
    private TicketHandler ticketHandler;
    public CommandTicketPrint(String name, TicketHandler ticketHandler) {
        super(name);
        this.ticketHandler = ticketHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
        System.out.print("Does nothing and wins");
    }
}
