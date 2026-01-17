package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket.Ticket;
import es.upm.etsisi.poo.Ticket.TicketBusiness;

import java.util.HashMap;

public class ClientBusiness extends Client {
    private HashMap<Integer, TicketBusiness> tickets;

    public ClientBusiness() {}
    public ClientBusiness(String dni, String nombre, String correo, Cashier cash){
        super(dni,nombre,correo, cash);
        this.tickets = new HashMap<>();
    }

    public void addTicket(TicketBusiness ticket) {
        tickets.put(ticket.getId(), ticket);
    }


    public TicketBusiness getTicketBusiness(String id) {
        return tickets.get(Integer.parseInt(id));
    }

    public HashMap<Integer, TicketBusiness> getTicketsBusiness() { return tickets;}

    public boolean isBusiness () {   return true; }

}
