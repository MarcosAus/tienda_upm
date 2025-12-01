package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Utilities;
import jdk.jshell.execution.Util;

public class CommandUserListTicketsCashier extends Command {
    private UserHandler userHandler;
    public CommandUserListTicketsCashier(String name, UserHandler productHandler) {
        super(name);
        this.userHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return name != null && name.toLowerCase().startsWith(this.name);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 3) {
            userHandler.listTicketsCashier(args[2]);
        } else {
            System.out.println(Comments.LENGTH_WRONG);
        }
    }

}
