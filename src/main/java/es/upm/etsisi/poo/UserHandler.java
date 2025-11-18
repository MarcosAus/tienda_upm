package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Users.User;

import java.util.HashMap;

public class UserHandler {
    private User user;
    private HashMap<String, User> usersRecord;

    public UserHandler(User user) {
        this.user = user;
        this.usersRecord = new HashMap<>();
    }
    public void signUp(User user) {
        usersRecord.put(user.getId(), user);
    }
    public void registerUser(User user) {
        // Lógica para registrar un usuario
    }


}
