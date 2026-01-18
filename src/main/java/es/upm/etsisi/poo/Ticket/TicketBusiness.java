package es.upm.etsisi.poo.Ticket;

import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductPers;
import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.State;
import es.upm.etsisi.poo.Strategies.PrintStrategy;
import es.upm.etsisi.poo.TicketItem;
import es.upm.etsisi.poo.Validacion.ValidacionS;
import es.upm.etsisi.poo.Validacion.ValidacionTickets;

import java.util.HashSet;
import java.util.List;

public class TicketBusiness extends TicketParam<Vendible> {

    private char ticketType;

    public TicketBusiness() { super(); }

    public TicketBusiness(int id, PrintStrategy<Vendible> printStrategy,ValidacionTickets<Vendible> validacion) {
        super(id, printStrategy,validacion);

    }

    public TicketBusiness(PrintStrategy<Vendible> printStrategy,ValidacionTickets<Vendible> validacion) {
        super(printStrategy,validacion);
    }


    @Override
    public boolean addProduct(Vendible element, int cantidad) {
        // Delegamos la decisión a la clase de validación
        if ()) {
            System.out.println();
            return false;
        }
        return super.addProduct(element, cantidad);
    }

}
