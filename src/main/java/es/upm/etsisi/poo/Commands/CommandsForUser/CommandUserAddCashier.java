package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Commands.CommandUser;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.User;
import es.upm.etsisi.poo.Utilities;

public class CommandUserAddCashier extends Command {
    private UserHandler userHandler;
    public CommandUserAddCashier(String name, UserHandler productHandler) {
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
            if (args.length == 5) {
                String id = args[2];
                String nombre = args[3];
                String email = args[4];
                User cashier = new Cashier(id, nombre, email);
                userHandler.registerUser(cashier);
            }
            else if (args.length == 4) {
                String nombre = args[2];
                String email = args[3];
                User cashier = new Cashier(null, nombre, email);
                userHandler.registerUser(cashier);
            } else {
                System.out.println(Comments.LENGTH_WRONG);
            }

        } catch (Exception e) {
            System.out.println(Comments.ID_NOT_NUMBER);
        }

    }
}
