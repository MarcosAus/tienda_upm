package es.upm.etsisi.poo;

import java.util.HashMap;

public class UserManager {
    private User user;
    private HashMap<String, User> usersRecord;

    public UserManager(User user) {
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
