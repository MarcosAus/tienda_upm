package es.upm.etsisi.poo.Strategies;

import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Ticket.TicketParam;

public interface PrintStrategy<T extends Vendible> {
    void printTicket(TicketParam<T> ticketParam);
}
