package es.upm.etsisi.poo.Ticket;

import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Strategies.PrintStrategy;
import es.upm.etsisi.poo.Validation.ValidacionCloseTickets;
import es.upm.etsisi.poo.Validation.ValidacionAddTickets;

public class TicketBusiness<T extends Vendible> extends TicketParam<T> {

    private char ticketType;

    public TicketBusiness() { super(); }

    //Constructor Services
    public TicketBusiness(int id, PrintStrategy<T> printStrategy, ValidacionAddTickets validacion, ValidacionCloseTickets<T> validacionCloseTickets) {
        super(id, printStrategy,validacion,validacionCloseTickets);

    }

    public TicketBusiness(PrintStrategy<T> printStrategy, ValidacionAddTickets validacion, ValidacionCloseTickets<T> validacionCloseTickets) {
        super(printStrategy,validacion,validacionCloseTickets);
    }
    //Constructor Combined


}
