package es.upm.etsisi.poo.Strategies;

import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Ticket.TicketParam;
import es.upm.etsisi.poo.TicketItem;

import java.util.Map;

public class ClientPrintStrategy implements PrintStrategy<Product> {

    @Override
    public void printTicket(TicketParam<Product> ticketParam) {
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
        for (TicketItem<Product> tI : ticketParam.getTicketItems()) {
            cantidadCategoria =  cantidadProductoCategoria.getOrDefault(tI.getProduct().getCategory(),0);
            product = tI.getProduct();
            sb.append(product.toString(tI.getAmount(),cantidadCategoria));
            if (cantidadCategoria>=2) {
                descuentoTotal += product.TotalPrice() * product.getDiscount() * tI.getAmount();
            }
            precioTotal += product.TotalPrice() * tI.getAmount();
        }
        System.out.print(sb);
        System.out.println("Total price: "+ precioTotal);
        System.out.println("Total discount: "+ descuentoTotal);
        System.out.println("Final price: " + (precioTotal - descuentoTotal));
    }
}
