package es.upm.etsisi.poo.Ticket;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Strategies.ClientPrintStrategy;
import es.upm.etsisi.poo.Strategies.PrintStrategy;
import es.upm.etsisi.poo.Validacion.ValidacionP;
import es.upm.etsisi.poo.Validacion.ValidacionS;
import es.upm.etsisi.poo.Validacion.ValidacionTickets;

import java.lang.reflect.Array;

public class TicketClient extends TicketParam<Product> {

    public TicketClient() { super(); }

    public TicketClient(int id, PrintStrategy<Product> printStrategy, ValidacionTickets<Product> validacion) {
        super(id, printStrategy,validacion);
    }

    public TicketClient(PrintStrategy<Product> printStrategy,ValidacionTickets<Product> validacion) {
        super(printStrategy,validacion);
    }
}
