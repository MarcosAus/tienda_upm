package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Utilities;

public class CommandsUserRemoveCashier extends Command {
    private UserHandler userHandler;
    public CommandsUserRemoveCashier(String name, UserHandler productHandler) {
        super(name);
        this.userHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 3) {
            Cashier cashier = userHandler.getCashiersRecord().get(args[2]);
            for (int i = 0; i < cashier.getTickets().size(); i++){
                cashier.removeTicket(cashier.getTickets().get(i).getId());
            }
            userHandler.getCashiersRecord().remove(cashier.getId());
        } else {
            System.out.println(Utilities.LENGTH_WRONG);
        }
    }
}
