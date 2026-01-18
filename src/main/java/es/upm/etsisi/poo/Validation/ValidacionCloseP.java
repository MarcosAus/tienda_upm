package es.upm.etsisi.poo.Validation;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Ticket.TicketParam;
import es.upm.etsisi.poo.TicketItem;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ValidacionCloseP implements ValidacionCloseTickets<Product> {

    @Override
    public boolean close(TicketParam<Product> ticketParam) {
        LocalDateTime now = LocalDateTime.now();
        ArrayList<TicketItem<Product>> ticketItems = ticketParam.getTicketItems();
        for (TicketItem<Product> ticketItem : ticketItems) {
            Product p = ticketItem.getProduct();
            Duration minTime = p.getMinTime();
            LocalDateTime eventDate = p.getStartDate();
            if (eventDate == null || minTime.isZero()) {
                continue;
            }
            Duration timeLeft = Duration.between(now, eventDate);
            if (timeLeft.compareTo(minTime) < 0) {
                return false;
            }
        }
        return true;
    }
}
