package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket.Ticket;

public abstract class User {
    private String dni;
    private String nombre;
    private String correo;

    public User(String dni, String nombre, String correo) {
        this.dni = dni;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getName() {
        return this.nombre;
    }

    public String getMail() {
        return this.correo;
    }

    public String getId() {
        return this.dni;
    }


    public Cashier getThisCash(){   return null;}

    public Client getThisCli(){    return null;}

    public boolean isCash() {   return false;}

    public void addTicket(Ticket ticket) {}
    public void removeTicket() {}
}
