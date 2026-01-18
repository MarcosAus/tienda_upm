package es.upm.etsisi.poo.Validation;

import es.upm.etsisi.poo.Products.Service;
import es.upm.etsisi.poo.Ticket.TicketParam;
import es.upm.etsisi.poo.TicketItem;

import java.time.LocalDate;
import java.util.ArrayList;

public class ValidacionCloseS implements ValidacionCloseTickets<Service> {

    @Override
    public boolean close(TicketParam<Service> ticketParam) {
        boolean fechaErronea=false;
        int i = 0;
        ArrayList<TicketItem<Service>> items = ticketParam.getTicketItems();
        while(i<items.size() && !fechaErronea){
            Service service = items.get(i).getProduct();
            if(!service.validDate(LocalDate.now())){
                fechaErronea=true;
            }
            i++;
        }
        if (!fechaErronea) return true;
        else return false;
    }
}
