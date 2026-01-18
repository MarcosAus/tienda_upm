package es.upm.etsisi.poo.Ticket;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Strategies.PrintStrategy;
import es.upm.etsisi.poo.Validation.ValidacionCloseTickets;
import es.upm.etsisi.poo.Validation.ValidacionAddTickets;

public class TicketClient extends TicketParam<Product> {

    public TicketClient() { super(); }

    public TicketClient(int id, PrintStrategy<Product> printStrategy, ValidacionAddTickets validacion, ValidacionCloseTickets<Product> validacionCloseTickets) {
        super(id, printStrategy,validacion,validacionCloseTickets);
    }

    public TicketClient(PrintStrategy<Product> printStrategy, ValidacionAddTickets validacion, ValidacionCloseTickets<Product> validacionCloseTickets) {
        super(printStrategy,validacion,validacionCloseTickets);
    }
}
