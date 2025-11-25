package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Users.User;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class UserHandler {
    private HashMap<String, Client> clientsRecord;
    private HashMap<String, Cashier> cashiersRecord;

    public UserHandler() {
        this.clientsRecord = new HashMap<>();
        this.cashiersRecord = new HashMap<>();
    }
    public HashMap<String, Client> getClientsRecord() {
        return clientsRecord;
    }
    public HashMap<String, Cashier> getCashiersRecord() {
        return cashiersRecord;
    }
    public void registerUser(User user) {
        if (user instanceof Client) {
            clientsRecord.putIfAbsent(user.getId(), (Client) user);
        } else if (user instanceof Cashier) {
            cashiersRecord.putIfAbsent(user.getId(), (Cashier) user);
        }
    }

    public User getUserById(String id) {
        if (id.contains("UW")) {
            return cashiersRecord.get(id);
        } else {
            return clientsRecord.get(id);
        }
    }
    public void listCashierRecord() {
        for (Cashier cash : cashiersRecord.values()) {
            System.out.println("Cashier{identifier = " + cash.getId() + ", name = " + cash.getName() + ", email = " + cash.getMail());
        }
    }
    public void listClientRecord() {
        for (Client client : clientsRecord.values()) {
            System.out.println("Client{identifier = " + client.getId() + ", name = " + client.getName() + ", email = " + client.getMail());
        }
    }
}
