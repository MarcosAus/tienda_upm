package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Commands.CommandUser;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandUserRemove extends Command {
    private UserHandler userHandler;
    public CommandUserRemove(String name, UserHandler productHandler) {
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
            if (args[2].contains("UW")) {
                userHandler.getCashiersRecord().remove(args[3]);
            } else {
                userHandler.getClientsRecord().remove(args[3]);
            }
        } catch (Exception e) {
            System.out.println(Utilities.LENGTH_WRONG);
        }
    }
}
