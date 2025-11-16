package es.upm.etsisi.poo;

public class Client extends User {
    private String cashId;
    public Client(String id, String nombre, String correo, String cashId){
        super(id,nombre,correo);
        this.cashId = cashId;
    }
}
