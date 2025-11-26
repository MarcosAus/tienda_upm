package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket;

public class Client extends User {
    private final Cashier cashier;
    private Ticket ticket;

    public Client(String dni, String nombre, String correo, Cashier cash){
        super(dni,nombre,correo);
        this.cashier = cash;
    }

    public Cashier getCashier() {
        return cashier;
    }

    //De alguna manera se tiene que guardar constancia de su ticket en el Client dejo estos
    //métodos como sugerencia.

    public void setTicketToClient(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        if (ticket != null) {
            return ticket;
        } else {
            return null;
        }
    }
}
