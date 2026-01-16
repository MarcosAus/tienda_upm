package es.upm.etsisi.poo.Strategies;

import es.upm.etsisi.poo.Ticket.TicketParam;

public interface PrintStrategy {
    void printTicket(TicketParam<?> ticketParam);
}
