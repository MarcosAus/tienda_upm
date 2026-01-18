package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket.TicketClient;

import java.util.HashMap;

public class ClientUser extends Client {
    private HashMap<Integer, TicketClient> tickets = new HashMap<>();

    public ClientUser() {}
    public ClientUser(String dni, String nombre, String correo, Cashier cash) {
        super(dni, nombre, correo, cash);
    }

    public void addTicket(TicketClient ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    public TicketClient getTicket(String id) {
        return tickets.get(Integer.parseInt(id));
    }

    public HashMap<Integer, TicketClient> getTicketsUser() { return tickets;}

    public boolean isNormal () {   return true; }
}
