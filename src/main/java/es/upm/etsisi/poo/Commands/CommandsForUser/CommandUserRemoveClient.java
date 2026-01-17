package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Utilities;

public class CommandUserRemoveClient implements Command {
    private UserHandler userHandler;
    String name;
    public CommandUserRemoveClient(String name, UserHandler productHandler) {
        this.name = name;
        this.userHandler = productHandler;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 3) {
            Client client = userHandler.getClientsRecord().get(args[2]);
            for (int i = 0; i < client.getTickets().size(); i++) {
                client.removeTicketFromClient(client.getTickets().get(i).getId());
            }
            userHandler.getClientsRecord().remove(args[2]);
            System.out.println(Comments.CLIENT_REMOVED);
        } else {
            System.out.println(Comments.LENGTH_WRONG);
        }
    }
}
