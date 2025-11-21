package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserHandler {
    private HashMap<String, User> usersRecord;

    public UserHandler() {
        this.usersRecord = new HashMap<>();
    }
    public void signUp(User user) {
        usersRecord.put(user.getId(), user);
    }

    public void registerUser(User user) {
        // Lógica para registrar un usuario
    }

    public User getUserById(String id) {
        return usersRecord.get(id);
    }
    public ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>();
        for (User user : usersRecord.values()) {
            if (user instanceof Client) {
                clients.add((Client) user);
            }
        }
        return clients;
    }
}
