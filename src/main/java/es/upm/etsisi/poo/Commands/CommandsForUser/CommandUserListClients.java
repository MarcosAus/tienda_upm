package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandUserListClients implements Command {
    private UserHandler userHandler;
    String name;
    public CommandUserListClients(String name, UserHandler productHandler) {
        this.name = name;
        this.userHandler = productHandler;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            userHandler.listClientRecord();
            System.out.println(Comments.CLIENT_LIST);
        } else {
            System.out.println(Comments.LENGTH_WRONG);
        }
    }
}
