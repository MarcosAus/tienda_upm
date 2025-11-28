package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.CashierHandler;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Commands.CommandTicket;
import es.upm.etsisi.poo.TicketHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandTicketPrint extends Command {
    private TicketHandler ticketHandler;
    private CashierHandler cashierHandler;
    public CommandTicketPrint(String name, TicketHandler ticketHandler, CashierHandler cashierHandler) {
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
        if (args.length == 4) {
            cashierHandler.printTicket(Integer.parseInt(args[2]), args[3]);
            ticketHandler.removeTicket(Integer.parseInt(args[2]));
        } else {
            System.out.println(Utilities.LENGTH_WRONG);
        }
    }
}
