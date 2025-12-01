package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Products.CampusMeals;
import es.upm.etsisi.poo.Products.Meetings;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Utilities;

import java.time.LocalDate;

public class CommandProdAddFoodMeeting extends Command {
    private ProductHandler productHandler;
    public CommandProdAddFoodMeeting(String name, ProductHandler productHandler) {
        super(name);
        this.productHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
//        int id;
//        String name;
//        double price;
//        String date;
//        int maxParticipantes;
//        Product product = null;
//        boolean add = true;
//        try{
//            if (productHandler.getHandlerSize() == Utilities.MAX_LIST) {
//                System.out.println(Utilities.PRODUCT_LIST_FULL);
//            } else {
//                if(args.length == 6) {
//                    id = Utilities.idAleatorio(productHandler);
//                    name =  args[2];
//                    price = Double.parseDouble(args[3]);
//                    date = args[4];
//                    maxParticipantes = Integer.parseInt(args[5]);
//                    LocalDate now = LocalDate.now();
//                    LocalDate fechaProducto;
//                    if (args[1].equals("addFood")) { //
//                        fechaProducto = LocalDate.parse(date).minus(CampusMeals.minTime);
//                        if (fechaProducto.isBefore(now)) {
//                            add = false;
//                        }
//                        product = new CampusMeals(id, name, price, date, maxParticipantes);
//                    } else if (args[1].equals("addMeeting")) {
//                        fechaProducto = LocalDate.parse(date).minus(Meetings.minTime);
//                        if (fechaProducto.isBefore(now)) {
//                            add = false;
//                        }
//                        product = new Meetings(id, name, price, date, maxParticipantes);
//                    }
//                    if (add && product != null) {
//                        productHandler.addProduct(product);
//                    } else {
//                        System.out.println(Comments.DATE_NOT_VALID);
//                    }
//
//                } else if(args.length == 7) {
//                    id = Integer.parseInt(args[2]);
//                    name =  args[3];
//                    price = Double.parseDouble(args[4]);
//                    date = args[5];
//                    maxParticipantes = Integer.parseInt(args[6]);
//
//                    if(args[1].equals("addFood")) {
//                        product = new CampusMeals(id, name, price, date, maxParticipantes);
//                    }else product = new Meetings(id, name, price, date, maxParticipantes);
//                    productHandler.addProduct(product);
//
//                } else System.out.println(Comments.LENGTH_WRONG);
//
//            }
//        } catch (NumberFormatException e) {
//            System.out.println(Comments.ID_PRICE_AMOUNT_NOT_NUMBER);
//        }
        }
}
