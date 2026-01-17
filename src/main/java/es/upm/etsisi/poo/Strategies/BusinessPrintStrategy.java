package es.upm.etsisi.poo.Strategies;

import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Ticket.TicketParam;

import java.util.Map;

public class BusinessPrintStrategy implements PrintStrategy<Vendible>{
    @Override
    public void printTicket(TicketParam<Vendible> ticketParam) {
        int cantidadCategoria;
        double precioTotal = 0;
        Product product;
        double descuentoTotal = 0;
        Map<Category, Integer> cantidadProductoCategoria = ticketParam.getCantidadProductoCategoria();
        StringBuilder sb = new StringBuilder("Ticket: ").append(ticketParam.getId());
        if (ticketParam.getTicketDateClosed() != null) {
            sb.append("-").append(ticketParam.getTicketDateClosed());
        }
        sb.append("\n");
        //Todo
    }
}
