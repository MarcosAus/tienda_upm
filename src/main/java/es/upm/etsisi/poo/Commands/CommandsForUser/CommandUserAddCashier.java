package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Commands.CommandUser;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
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
        try{
            if(args.length != 5){
                int id;
                id = Integer.parseInt(args[2]);
                String nombre;
                nombre = args[3];
                String email;
                email = args[4];
                Cashier cashier = new Cashier();
                userHandler.registerUser();
            }
            else {
                System.out.println("Error: wrong comand legth ");
            }

        } catch (Exception e) {
            System.out.println(Utilities.ID_NOT_NUMBER);
        }

    }
}
