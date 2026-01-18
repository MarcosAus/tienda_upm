package es.upm.etsisi.poo.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        return this.nombre;}

    public String getMail() {
        return this.correo;
    }

    public String getId() {
        return this.dni;
    }


    @JsonIgnore
    public Cashier getThisCash(){   return null;}
    @JsonIgnore
    public Client getThisCli(){    return null;}
    @JsonIgnore
    public boolean isCash() {   return false;}
    @JsonIgnore
    public void addTicket(TicketParam<?> ticket) {}
    @JsonIgnore
    public void removeTicket() {}
}
