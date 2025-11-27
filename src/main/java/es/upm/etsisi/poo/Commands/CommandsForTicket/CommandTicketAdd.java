package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.User;

public class CommandTicketAdd extends Command {
    private CashierHandler cashierHandler;
    private ProductHandler productHandler;
    public CommandTicketAdd(String name, CashierHandler cashierHandler, ProductHandler productHandler) {
        super(name);
        this.cashierHandler = cashierHandler;
        this.productHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
       if (args.length == 6) {
           Ticket ticket = new Ticket(args[2]);
           Cashier cashier = cashierHandler.getCashiersRecord().get(args[3]);
           Product product =
           cashierHandler.addTicket(args[2], args[3], );



       }else System.out.println(Utilities.LENGTH_WRONG);
    }
}
