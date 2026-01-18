package es.upm.etsisi.poo.Strategies;

import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Ticket.TicketParam;
import es.upm.etsisi.poo.TicketItem;

import java.util.ArrayList;
import java.util.Map;

public class BusinessPrintStrategyCombined implements PrintStrategy<Vendible>{
    @Override
    public void printTicket(TicketParam<Vendible> ticketParam) {
        int cantidadCategoria;
        double totalPrice = 0;
        ArrayList<TicketItem<Vendible>> productsInTicket = new ArrayList<>();
        Product product;
        double totalDiscount = 0;
        double numServices = 0;
        Map<Category, Integer> cantidadProductoCategoria = ticketParam.getCantidadProductoCategoria();
        StringBuilder sb = new StringBuilder("Ticket: ").append(ticketParam.getId());
        if (ticketParam.getTicketDateClosed() != null) {
            sb.append("-").append(ticketParam.getTicketDateClosed());
        }
        sb.append("\n");
        sb.append("Services Included:\n");
        for (TicketItem<Vendible> tI : ticketParam.getTicketItems()) {
            Vendible vendible = tI.getProduct();
            char ultimaLetra = vendible.getId().charAt(vendible.getId().length() - 1);
            if (Character.isDigit(ultimaLetra)) {
                productsInTicket.add(tI);
            } else {
                numServices++;
                sb.append((tI.getProduct()).toString()).append("\n");
            }
        }
        System.out.println(sb);
        if(!productsInTicket.isEmpty()){
            double porcentajeDescuentoServicios = numServices * 0.15;
            if(porcentajeDescuentoServicios>1.0) porcentajeDescuentoServicios=1.0;
            sb.append("Products Included:\n");
            for (TicketItem<Vendible> tI : productsInTicket) {
                product = (Product) tI.getProduct();
                cantidadCategoria = cantidadProductoCategoria.getOrDefault(product.getCategory(), 0);
                sb.append(product.toString(tI.getAmount(), cantidadCategoria));
                if (cantidadCategoria >= 2) {

                    double discount = product.getDiscount() + porcentajeDescuentoServicios;
                    if(discount>1.0) discount=1.0;
                    totalDiscount += product.TotalPrice() * discount * tI.getAmount()  ;
                }
                totalPrice += product.TotalPrice() * tI.getAmount();
            }
            System.out.print(sb);
            System.out.println("Total price: "+ totalPrice);
            System.out.println("Extra discount from services: " + (totalPrice * porcentajeDescuentoServicios  )+ "**discount -" + (totalPrice *porcentajeDescuentoServicios));
            System.out.println("Total discount: "+ totalDiscount);
            System.out.println("Final price: " + (totalPrice - totalDiscount));
        }
    }
}
