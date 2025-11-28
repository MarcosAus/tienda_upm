package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.CashierHandler;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.TicketHandler;
import es.upm.etsisi.poo.Utilities;
import jdk.jshell.execution.Util;

public class CommandTicketRemove extends Command {
    private TicketHandler ticketHandler;
    private CashierHandler cashierHandler;
    public CommandTicketRemove(String name, TicketHandler ticketHandler, CashierHandler cashierHandler) {
        super(name);
        this.ticketHandler = ticketHandler;
        this.cashierHandler = cashierHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 5) {
            cashierHandler.removeTicket(Integer.parseInt(args[2]), args[3], Integer.parseInt(args[4]));
            ticketHandler.removeTicket(Integer.parseInt(args[2]));
        } else {
            System.out.println(Utilities.LENGTH_WRONG);
        }
    }
}
