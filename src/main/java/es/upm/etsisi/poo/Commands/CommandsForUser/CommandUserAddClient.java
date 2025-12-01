package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Users.User;

public class CommandUserAddClient extends Command {
    private UserHandler userHandler;
    public CommandUserAddClient(String name, UserHandler productHandler) {
        super(name);
        this.userHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return name != null && name.toLowerCase().startsWith(this.name);
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 6) {
                String nombre = args[2];
                String id = args[3];
                String email = args[4];
                try {
                    Cashier cashier = userHandler.getCashiersRecord().get(args[5]);
                    User client = new Client(id, nombre, email, cashier);
                    userHandler.registerUser(client);
                } catch (Exception e) {
                    System.out.println(Comments.CASH_NOT_FOUND);
                    }
            } else {
                System.out.println(Comments.LENGTH_WRONG);
            }
        } catch (Exception e) {
            System.out.println(Comments.ID_NOT_NUMBER);
        }
    }
}
