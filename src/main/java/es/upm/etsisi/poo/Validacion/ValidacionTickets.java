package es.upm.etsisi.poo.Validacion;

import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Ticket.TicketParam;

public interface ValidacionTickets<T extends Vendible> {

    boolean add(T vendible);

    boolean close(TicketParam<T> ticket);

}
