package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Users.User;
import es.upm.etsisi.poo.Utilities;

public class CommandUserAddClient extends Command {
    private UserHandler userHandler;
    public CommandUserAddClient(String name, UserHandler productHandler) {
        super(name);
        this.userHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 6) {
                String nombre = args[2];
                String id = args[3];
                String email = args[4];
                Cashier cashier = userHandler.getCashiersRecord().get(args[5]);
                User client = new Client(id, nombre, email, cashier);
                userHandler.registerUser(client);
            } else {
                System.out.println(Comments.LENGTH_WRONG);
            }
        } catch (Exception e) {
            System.out.println(Comments.ID_NOT_NUMBER);
        }
    }
}
