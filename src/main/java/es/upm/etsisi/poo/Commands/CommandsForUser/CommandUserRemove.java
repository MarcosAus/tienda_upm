package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Commands.CommandUser;
import es.upm.etsisi.poo.UserHandler;

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
        System.out.print("Does nothing and wins");
    }
}
