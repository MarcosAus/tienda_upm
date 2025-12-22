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
                           if(actCashier.ticketExists(actTicket.getId())) {
                               if (args.length == 6){//CASO PRODUCT BASIC , MEETING , CAMPUSMEALS y PRODUCTO PERS SIN PERSONALIZAR
                                   if (actProduct.isPersonalizable()){
                                       Product newProduct = actProduct.copyProduct();
                                       actTicket.addProduct(newProduct,amount);
                                   } else {
                                       if (actProduct.getMinTime().isZero()) {
                                           Product newProduct = actProduct.copyProduct();
                                           actTicket.addProduct(newProduct,amount);
                                       } else {
                                           if (actProduct.getMinTime().compareTo(Duration.ofHours(72))==0){
                                               Event newProduct=
                                                       (Event) actProduct.copyProduct();
                                               if(newProduct.getMaxParticipantes()>=amount){
                                                   actTicket.addProduct(newProduct,amount);
                                               }else{
                                                   System.out.println(Comments.MAXPARTICIPANTS_EXCEDED);
                                               }

                                           } else {
                                               Event newProduct= (Event) actProduct.copyProduct();
                                               if(newProduct.getMaxParticipantes()>=amount){
                                                   actTicket.addProduct(newProduct,amount);
                                               }else{
                                                   System.out.println(Comments.MAXPARTICIPANTS_EXCEDED);
                                               }
                                           }
                                       }
                                       System.out.println(Comments.TICKET_ADD);
                                   }
                               } else {
                                   if (actProduct.isPersonalizable()){
                                       ProductPers newProduct = (ProductPers) actProduct.copyProduct();
                                       for (int i = 6; i < args.length; i++){ //CASO PRODUCTO PERS
                                           if (args[i].startsWith("--p")){
                                               if (!newProduct.isFull() && !newProduct.getTextos().contains(args[i].substring(3))) newProduct.addTexto(args[i].substring(3));
                                               else {
                                                   System.out.println("No se pueden añadir mas textos");
                                               }
                                           }
                                       }
                                       actTicket.addProduct(newProduct,amount);
                                       System.out.println(Comments.TICKET_ADD);
                                   }
                               }
                           }else{
                               System.out.println(Comments.TICKET_IS_NOT_IN_CASH);
                           }
                       } else {
                           System.out.println(Comments.TICKET_ID_NOT_FOUND);
                       }
                   } else {
                       System.out.println(Comments.PRODUCT_NOT_FOUND);
                   }
               } else {
                   System.out.println(Comments.CASH_NOT_FOUND);
               }
           } catch (Exception e) {
               System.out.println(Comments.INT_NOT_NUMBER);
           }
       } else {
              System.out.println(Comments.LENGTH_WRONG);
       }
    }
}
