package es.upm.etsisi.poo.Validation;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.Service;
import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Ticket.TicketParam;
import es.upm.etsisi.poo.TicketItem;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ValidacionCloseC implements ValidacionCloseTickets<Vendible>{

    @Override
    public boolean close(TicketParam<Vendible> ticket) {
        HashMap<String,Integer> map = new HashMap<>();
        ArrayList<TicketItem<Vendible>> ticketItem = ticket.getTicketItems();
        LocalDateTime now =  LocalDateTime.now();

        int i=0;
        boolean fechaErronea = false;

        while(i<ticketItem.size() && !fechaErronea){
            TicketItem<Vendible> item  = ticketItem.get(i);
            if(ticketItem.get(i).getProduct().getId().endsWith("S")){
                map.put("S",map.getOrDefault("S",0)+item.getAmount());
                Service service = (Service) item.getProduct();
                if(!service.validDate(LocalDate.now())){
                    fechaErronea = true;
                }
            }
            else {
                map.put("N",map.getOrDefault("N",0)+item.getAmount());

                Product p = (Product)item.getProduct();
                Duration minTime = p.getMinTime();
                LocalDateTime eventDate = p.getStartDate();
                if (!(eventDate == null || minTime.isZero())) {
                    Duration timeLeft = Duration.between(now, eventDate);
                    if (timeLeft.compareTo(minTime) < 0) {
                        return false;
                    }
                }
            }
            i++;
        }
        if(map.get("S")>0 && map.get("N")>0 && !fechaErronea){
            return true;
        }
        else{
            return false;
        }

    }
}
