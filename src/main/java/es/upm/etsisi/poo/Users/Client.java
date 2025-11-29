package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket;

import java.util.HashMap;

public class Client extends User {
    private final Cashier cashier;
    private HashMap<String, Ticket> tickets;

    public Client(String dni, String nombre, String correo, Cashier cash){
        super(dni,nombre,correo);
        this.cashier = cash;
        this.tickets = new HashMap<>();
    }

    public Cashier getCashier() {
        return cashier;
    }

    //De alguna manera se tiene que guardar constancia de su ticket en el Client dejo estos
    //métodos como sugerencia.

    public void setTicketToClient(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public Client getThisCli(){
        return this;
    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.put(String.format("%05d", ticket.getId()), ticket);
    }

    public void removeTicketFromClient(Ticket ticket) {

    }

    public Ticket getTicket(String id) {
        return tickets.get(id);
    }
}
