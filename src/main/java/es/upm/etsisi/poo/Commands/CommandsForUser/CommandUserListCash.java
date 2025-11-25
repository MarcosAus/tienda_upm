package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandUserListCash extends Command {
    private UserHandler userHandler;
    public CommandUserListCash(String name, UserHandler productHandler) {
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
            if (args.length == 2) {
                userHandler.listCashierRecord();
            }
        } catch (Exception e) {
            System.out.println(Utilities.LENGTH_WRONG);
        }
    }
}
