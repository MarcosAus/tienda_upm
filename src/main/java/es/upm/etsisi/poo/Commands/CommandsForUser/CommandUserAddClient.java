package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
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
                String id = args[2];
                String nombre = args[3];
                String email = args[4];
                Cashier cashier = new Cashier(id, nombre, email);
                userHandler.registerUser(cashier);
            } else {
                System.out.println(Utilities.LENGTH_WRONG);
            }
        } catch (Exception e) {
            System.out.println(Utilities.ID_NOT_NUMBER);
        }
    }
}
