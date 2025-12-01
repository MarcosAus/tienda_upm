package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandUserListClients extends Command {
    private UserHandler userHandler;
    public CommandUserListClients(String name, UserHandler productHandler) {
        super(name);
        this.userHandler = productHandler;
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
