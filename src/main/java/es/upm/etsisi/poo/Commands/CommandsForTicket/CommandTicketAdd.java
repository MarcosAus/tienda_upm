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
           String idTicket = args[2];
           String idCash = args[3];
           Product product = productHandler.getProduct(Integer.parseInt(args[4]));
           int amount = Integer.parseInt(args[5]);
           cashierHandler.addTicket(idTicket, idCash, product, amount);
       }else System.out.println(Utilities.LENGTH_WRONG);
    }
}
