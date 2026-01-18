package es.upm.etsisi.poo.Validation;

import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Ticket.TicketParam;

public interface ValidacionCloseTickets <T extends Vendible> {

    boolean close(TicketParam<T> ticket);
}
