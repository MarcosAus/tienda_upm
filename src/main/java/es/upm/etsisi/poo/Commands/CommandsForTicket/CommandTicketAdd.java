package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.CashierHandler;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.TicketHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandTicketAdd extends Command {
    private CashierHandler cashierHandler;
    private TicketHandler ticketHandler;
    private ProductHandler productHandler;
    public CommandTicketAdd(String name, TicketHandler ticketHandler, CashierHandler cashierHandler, ProductHandler productHandler) {
        super(name);
        this.cashierHandler = cashierHandler;
        this.ticketHandler = ticketHandler;
        this.productHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
       if (args.length == 6) {
           Product productToAdd = productHandler.getProduct(Integer.parseInt(args[4]));
           cashierHandler.addToTicket(Integer.parseInt(args[2]), args[3], productToAdd, Integer.parseInt(args[5]));
           ticketHandler.addTicket(Integer.parseInt(args[2]), productToAdd, Integer.parseInt(args[5]));
       } else if (args.length > 6) {
           // Rellenar con añadir productos personalizables
       } else {
              System.out.println(Utilities.LENGTH_WRONG);
       }
    }
}
