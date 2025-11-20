package es.upm.etsisi.poo.Commands;

import es.upm.etsisi.poo.Products.Inventory;
import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.Utilities;

public class CommandTicketNew extends Command {
    private Ticket ticket;
    private Inventory productList;
    public CommandTicketNew(String name, Ticket ticket, Inventory productList) {
        super(name);
        this.ticket = ticket;
        this.productList = productList;
    }
    public void execute(String[] args) {
        if (args.length == 2) {
            ticket = new Ticket(ticket.getId());
            System.out.println(Utilities.TICKET_NEW_OK);
        } else System.out.println(Utilities.LENGTH_WRONG);
    }
}
