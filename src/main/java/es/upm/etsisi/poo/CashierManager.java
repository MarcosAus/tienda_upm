package es.upm.etsisi.poo;

import java.util.HashMap;

public class CashierManager extends UserManager {
    private Cashier cashier;
    private HashMap<String, User> userRecord;
    public CashierManager(Cashier cashier) {
        super(cashier);
        this.cashier = cashier;
        this.userRecord = new HashMap<>();
    }
    public void addTicket(Ticket ticket) {
        cashier.tickets.push(ticket);
    }
    public void registerClient(Client client) {
        registerUser(client);
    }

}
