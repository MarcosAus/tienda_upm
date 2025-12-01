package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Users.Cashier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CommandTicketList extends Command {
    private TicketHandler ticketHandler;
    private UserHandler userHandler;

    public CommandTicketList(String name, TicketHandler ticketHandler, UserHandler userHandler) {
        super(name);
        this.ticketHandler = ticketHandler;
        this.userHandler = userHandler;
    }


    //fixme No recorre los cajeros de manera ordenada. Lo suyo sería que se ordenasen en UserHandler, se le pasase una lista, y este metodo solo la recorriese.
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            ArrayList<Cashier> cashiers = new ArrayList<>(userHandler.getCashiersRecord().values());

            cashiers.sort((c1, c2) -> {
                int id1 = Integer.parseInt(c1.getId().substring(2));
                int id2 = Integer.parseInt(c2.getId().substring(2));
                return Integer.compare(id1, id2);
            });
            System.out.println("Ticket List:");
            for (Cashier cashier : cashiers) {
                cashier.listAllTickets();
            }
            System.out.println(Comments.TICKET_LIST);
        } else {
            System.out.println(Comments.LENGTH_WRONG);
        }
    }
}
