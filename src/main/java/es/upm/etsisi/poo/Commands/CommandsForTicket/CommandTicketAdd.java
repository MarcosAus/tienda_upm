package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.User;

public class CommandTicketAdd extends Command {
    private UserHandler userhandler;
    private TicketHandler ticketHandler;
    private ProductHandler productHandler;
    public CommandTicketAdd(String name, TicketHandler ticketHandler, UserHandler userHandler, ProductHandler productHandler) {
        super(name);
        this.userhandler = userHandler;
        this.ticketHandler = ticketHandler;
        this.productHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    //fixme poner errores

    @Override
    public void execute(String[] args) {
       if (args.length == 6) {
           //Se compruban que existen los ids
           if (ticketHandler.getTicket(Integer.parseInt(args[2])) != null && userhandler.getUserById(args[3]) != null && productHandler.getProduct(Integer.parseInt(args[4])) != null) {
               //Se comprueba si los ids de cashier y client son cashier y client
               User cashier = userhandler.getUserById(args[3]);
               try { //Se comprueba si el usuario ha puesto una cantidad correcta.
                   if (cashier.isCash()) {
                       Product productToAdd = productHandler.getProduct(Integer.parseInt(args[4]));
                       Ticket currentTicket =  ticketHandler.getTicket(Integer.parseInt(args[2]));
                       Product currentProduct = productHandler.getProduct(Integer.parseInt(args[4]));
                       currentTicket.addProduct(currentProduct, Integer.parseInt(args[5]));
                       ticketHandler.addTicket(Integer.parseInt(args[2]), productToAdd, Integer.parseInt(args[5]));
                   } else {
                       System.out.println(Utilities.CASHIER_ID_NOT_EXISTS);
                   }
               } catch (Exception e) {

               }
           } else {
               System.out.println(Utilities.ID_NOT_IN_BOUNDARIES);
           }
       } else if (args.length > 6) {
           // Rellenar con añadir productos personalizables

       } else {
              System.out.println(Utilities.LENGTH_WRONG);
       }
    }
}
