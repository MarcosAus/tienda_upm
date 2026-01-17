package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Utilities;
import jdk.jshell.execution.Util;

public class CommandUserListCash implements Command {
    private UserHandler userHandler;
    String name;
    public CommandUserListCash(String name, UserHandler productHandler) {
        this.name = name;
        this.userHandler = productHandler;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            userHandler.listCashierRecord();
            System.out.println(Comments.CASH_LIST);
        } else {
            System.out.println(Comments.LENGTH_WRONG);
        }
    }
}
