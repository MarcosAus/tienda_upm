package es.upm.etsisi.poo.Users;

public class Client extends User {
    private final Cashier cashier;
    private final String dni;

    public Client(String id, String nombre, String correo, Cashier cash, String dni){
        super(id,nombre,correo);
        this.cashier = cash;
        this.dni = dni;
    }

    public String getdni(){
        return dni;
    }

    public Cashier getCashier() {
        return cashier;
    }
}
