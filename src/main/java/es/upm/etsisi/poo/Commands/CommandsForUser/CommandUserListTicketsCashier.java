package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Utilities;
import jdk.jshell.execution.Util;

public class CommandUserListTicketsCashier implements Command {
    private UserHandler userHandler;
    String name;
    public CommandUserListTicketsCashier(String name, UserHandler productHandler) {
        this.name = name;
        this.userHandler = productHandler;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 3) {
            userHandler.listTicketsCashier(args[2]);
            System.out.println(Comments.CASH_TICKETS);
        } else {
            System.out.println(Comments.LENGTH_WRONG);
        }
    }

}
