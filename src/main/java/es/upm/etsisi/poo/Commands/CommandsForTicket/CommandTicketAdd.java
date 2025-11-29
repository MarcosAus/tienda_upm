package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductBasic;
import es.upm.etsisi.poo.Products.ProductPers;
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

    //fixme Si quereis podeis poner los errores en Utilities. Hacer ctr f System.out y vais cambiando. Yo no lo veo necesario.

    @Override
    public void execute(String[] args) {
       if (args.length >= 6) {
           try{
               //Se separan todas las variables que se dan en el comando
               int amount = Integer.parseInt(args[5]);
               Ticket actTicket = ticketHandler.getTicket(Integer.parseInt(args[2]));
               Product actProduct = productHandler.getProduct(Integer.parseInt(args[4]));
               Cashier actCashier = userhandler.getUserById(args[3]).getThisCash();

               if (actCashier != null) {// Se comprueban posibles errores que se pueden dar con las variables dadas por el usuario.
                   if (actProduct != null) {
                       if (actTicket != null) {
                           //Se comprueba si se quieren poner personalizaciones.
                           if ((args.length > 6) && productHandler.capacityLeft() > 0) {//Se comprueba si el producto se puede personalizar. Si es el caso se crea un producto personalizado nuevo
                               if(actProduct.getProductPers() != null){
                                   ProductPers actProductPers = (ProductPers) actProduct.getProductPers();
                                   int newID = actProductPers.getId() + 1, counter = productHandler.getCapacity();
                                   while(productHandler.getProduct(newID)!= null && counter > 0) {
                                       counter--;
                                       newID++;
                                       if(newID > productHandler.getCapacity()){
                                           newID = 0;
                                       }
                                   }
                                   ProductPers newProductP = new ProductPers(actProductPers.getCategoria(),newID,
                                           actProductPers.getNombre(), actProductPers.getPrecio(), actProductPers.getMaxTextos());
                                   for(int i = 6; i < args.length ; i++){
                                       if(args[i].startsWith("--p")){
                                           newProductP.addTexto(args[i].substring(3));
                                       }
                                   }

                                   actTicket.addProduct(newProductP, amount);

                               }
                               else if(actProduct.getProductBasic() != null){
                                   ProductBasic actProductBasic = (ProductBasic) actProduct.getProductBasic();
                                   int newID = actProductBasic.getId() + 1, counter = productHandler.getCapacity();
                                   while(productHandler.getProduct(newID)!= null && counter > 0) {
                                       counter--;
                                       newID++;
                                       if(newID > productHandler.getCapacity()){
                                           newID = 0;
                                       }
                                   }
                                   ProductPers newProductP = new ProductPers(actProductBasic.getCategoria(),newID,
                                           actProductBasic.getNombre(), actProductBasic.getPrecio());
                                   for(int i = 6; i < args.length ; i++){
                                       if(args[i].startsWith("--p")){
                                           newProductP.addTexto(args[i].substring(3));
                                       }
                                   }
                                   actTicket.addProduct(newProductP, amount);

                               }
                               else{
                                   System.out.println("This tipe of product cant be personalice.");
                               }
                           }
                           else{//En caso de que no sea un producto personalizado.
                               actTicket.addProduct(actProduct, amount);
                           }

                           actTicket.addProduct(actProduct,amount);
                           System.out.println("Ticket added successfully.");

                       }
                       else{
                           System.out.println(Utilities.TICKET_ID_NOT_FOUND);
                       }
                   }
                   else {
                       System.out.println(Utilities.PRODUCT_NOT_FOUND);
                   }
               }
               else  {
                   System.out.println(Utilities.CASHIER_ID_NOT_EXISTS);
               }
           } catch (Exception e) {
               System.out.println(Utilities.INT_NOT_NUMBER);
           }



       } else {
              System.out.println(Utilities.LENGTH_WRONG);
       }
    }
}
