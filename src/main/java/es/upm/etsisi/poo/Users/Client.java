package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket;

public class Client extends User {
    private final Cashier cashier;
    private String dni;

    public Client(String dni, String nombre, String correo, Cashier cash){
        super(dni,nombre,correo);
        this.cashier = cash;
    }

    public String getdni(){
        return dni;
    }

    public Cashier getCashier() {
        return cashier;
    }

    //De alguna manera se tiene que guardar constancia de su ticket en el Client dejo estos
    //métodos como sugerencia.

    public void asociarTicket(Ticket ticket){

    }

    public Ticket getTicket(){
      Ticket ticket = null;
      return ticket;
    }
}
