package es.upm.etsisi.poo.Strategies;

import es.upm.etsisi.poo.Products.Service;
import es.upm.etsisi.poo.Ticket.TicketParam;
import es.upm.etsisi.poo.TicketItem;

public class BusinessPrintStrategyService implements PrintStrategy<Service>{
    @Override
    public void printTicket(TicketParam<Service> ticketParam) {
        StringBuilder sb = new StringBuilder("Ticket: ").append(ticketParam.getId());
        if (ticketParam.getTicketDateClosed() != null) {
            sb.append("-").append(ticketParam.getTicketDateClosed());
        }
        sb.append("\n");
        sb.append("Services Included:\n");
        for (TicketItem<Service> tI : ticketParam.getTicketItems()) {
            sb.append((tI.getProduct()).toString()).append("\n");
        }
        System.out.println(sb);
    }
}
