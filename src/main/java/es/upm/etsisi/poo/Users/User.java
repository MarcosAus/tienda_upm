package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket.Ticket;
import es.upm.etsisi.poo.Ticket.TicketParam;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cashier.class, name = "Cashier"),
        @JsonSubTypes.Type(value = ClientUser.class, name = "ClientUser"),
        @JsonSubTypes.Type(value = ClientBusiness.class, name = "ClientBusiness")
})

public abstract class User {
    private String dni;
    private String nombre;
    private String correo;

    public User() {}
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

    public void addTicket(TicketParam<?> ticket) {}
    public void removeTicket() {}
}
