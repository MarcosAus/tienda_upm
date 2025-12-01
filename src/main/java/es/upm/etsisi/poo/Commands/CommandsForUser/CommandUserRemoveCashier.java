package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;

public class CommandUserRemoveCashier extends Command {
    private UserHandler userHandler;
    public CommandUserRemoveCashier(String name, UserHandler productHandler) {
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
            try {
                Cashier cashier = userHandler.getCashiersRecord().get(args[2]);
                for (int i = 0; i < cashier.getTickets().size(); i++) {
                    cashier.removeTicket(cashier.getTickets().get(i).getId());
                }
                userHandler.getCashiersRecord().remove(cashier.getId());
                System.out.println(Comments.CASHIER_REMOVED);
            } catch (NullPointerException e) {
                System.out.println(Comments.CASH_NOT_FOUND);
            }
            } else {
            System.out.println(Comments.LENGTH_WRONG);
        }
    }
}
