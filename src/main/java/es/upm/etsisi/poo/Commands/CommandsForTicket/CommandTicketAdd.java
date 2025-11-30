package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.*;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Products.*;
import es.upm.etsisi.poo.Users.Cashier;

import java.time.Duration;

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
           try {
               //Se separan todas las variables que se dan en el comando
               int amount = Integer.parseInt(args[5]);
               Ticket actTicket = ticketHandler.getTicket(Integer.parseInt(args[2]));
               Cashier actCashier = userhandler.getUserById(args[3]).getThisCash();
               Product actProduct = productHandler.getProduct(Integer.parseInt(args[4]));

               if (actCashier != null) {// Se comprueban posibles errores que se pueden dar con las variables dadas por el usuario.
                   if (actProduct != null) {
                       if (actTicket != null) {
                           if (args.length==6){//CASO PRODUCT BASIC , MEETING , CAMPUSMEALS y PRODUCTO PERS SIN PERSONALIZAR
                               if (actProduct.isPersonalizable()){
                                   ProductPers newProduct =
                                           new ProductPers(actProduct.getCategory(),actProduct.getId(),actProduct.getName(),
                                                   actProduct.getPrecio(),((ProductPers) actProduct).getMaxTextos());
                                   actTicket.addProduct(newProduct,amount);
                               }
                               else {
                                   if (actProduct.getMinTime().isZero()) {
                                       ProductBasic newProduct =
                                               new ProductBasic(actProduct.getCategory(),actProduct.getName(),actProduct.getId(),
                                                       actProduct.getPrecio());
                                        actTicket.addProduct(newProduct,amount);
                                   }
                                   else if (actProduct.getMinTime().compareTo(Duration.ofHours(72))==0){
                                       CampusMeals newProduct=
                                               new CampusMeals(actProduct.getId(),actProduct.getName(),actProduct.getPrecio(),
                                                       ((CampusMeals) actProduct).getDateOfEnd(),((CampusMeals) actProduct).getMaxParticipantes());
                                       actTicket.addProduct(newProduct,amount);
                                   }
                                   else{
                                       Meetings newProduct=
                                               new Meetings(actProduct.getId(),actProduct.getName(),actProduct.getPrecio(),
                                                       ((Meetings) actProduct).getDateOfEnd(),((Meetings) actProduct).getMaxParticipantes());
                                       actTicket.addProduct(newProduct,amount);
                                   }
                               }
                           }
                           else {
                               if(actProduct.isPersonalizable()){
                                   ProductPers newProduct =
                                           new ProductPers(actProduct.getCategory(),actProduct.getId(),actProduct.getName(),
                                                   actProduct.getPrecio(),((ProductPers) actProduct).getMaxTextos());
                                   for (int i = 6; i < args.length; i++){ //CASO PRODUCTO PERS
                                        if(args[i].contains("--p"))
                                   }
                               }

                           }
                       }
                       else {
                           System.out.println(Utilities.TICKET_ID_NOT_FOUND);
                       }
                   }
                   else {
                       System.out.println(Utilities.PRODUCT_NOT_FOUND);
                   }
               }
               else {
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
