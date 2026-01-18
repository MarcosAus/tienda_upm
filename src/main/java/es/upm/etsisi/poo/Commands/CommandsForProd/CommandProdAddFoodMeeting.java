package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.Persistence.PersistenceManager;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Products.Event;
// import es.upm.etsisi.poo.Products.CampusMeals; Comentada porque ya no se usa
// import es.upm.etsisi.poo.Products.Meetings; Comentada porque ya no se usa
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Utilities;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class CommandProdAddFoodMeeting implements Command {
    private ProductHandler productHandler;
    String name;
    public CommandProdAddFoodMeeting(String name, ProductHandler productHandler) {
        this.name = name;
        this.productHandler = productHandler;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
    }


    @Override
    public void execute(String[] args) {
        int id;
        String name;
        double price;
        String date;
        int maxParticipantes;
        Product product = null;
        boolean add = true;
        try{
            if (productHandler.getHandlerSize() == Utilities.MAX_LIST) {
                System.out.println(Comments.PRODUCT_LIST_FULL);
            } else {
                LocalDateTime now = LocalDateTime.now();
                if(args.length == 6) {
                    id = Utilities.idAleatorio(productHandler);
                    name =  args[2];
                    price = Double.parseDouble(args[3]);
                    date = args[4];
                    maxParticipantes = Integer.parseInt(args[5]);
                    LocalDateTime fechaProducto;
                    if(Event.getMAXPEOPLEALLOWED() >= maxParticipantes) {
                        if (name.length() >=3 && name.startsWith("\"") && name.endsWith("\"")) { // Se verifica que el nombre tenga el formato correcto
                            name = name.substring(1, name.length()-1);
                            if (args[1].equals("addFood")) {
                                product = new Event(Integer.toString(id), name, price, date, maxParticipantes, Utilities.getMinTimeMels(), "CampusMeals");
                                fechaProducto = product.getStartDate();
                                if (fechaProducto.isBefore(now) || maxParticipantes >= Utilities.getMinTimeMels() || maxParticipantes <= 0) {
                                    add = false;
                                }
                            } else if (args[1].equals("addMeeting")) {
                                product = new Event(Integer.toString(id), name, price, date, maxParticipantes, Utilities.getMinTimeMeetings(), "Meetings");
                                fechaProducto = product.getStartDate();
                                if (fechaProducto.isBefore(now) || maxParticipantes >= Utilities.getMinTimeMels() || maxParticipantes <= 0) {
                                    add = false;
                                }
                            }

                            if (add && product != null) {
                                productHandler.addProduct(product);
                                PersistenceManager.saveProducts(productHandler);
                                System.out.println(product);
                                if (args[1].equals("addFood")) {
                                    System.out.println(Comments.PROD_ADDFOOD);
                                } else if (args[1].equals("addMeeting")) {
                                    System.out.println(Comments.PROD_ADDMEETINGS);
                                }
                            } else {
                                if (maxParticipantes >= Utilities.MAX_PPOPLE_EVENT || maxParticipantes <= 0) {
                                    System.out.println(Comments.MAXPEOPLE_EXCEDED);
                                } else {
                                    System.out.println(Comments.DATE_NOT_VALID);
                                }
                            }
                        } else{
                            System.out.println(Comments.NAME_HAS_WRONG_FORMAT);
                        }
                    }
                    else{
                        System.out.println(Comments.MAXPEOPLE_EXCEDED);
                    }
                } else if(args.length == 7) {
                    id = Integer.parseInt(args[2]);
                    if(id>0 && id<=99999){
                        name =  args[3];
                        price = Double.parseDouble(args[4]);
                        date = args[5]; ;
                        maxParticipantes = Integer.parseInt(args[6]);
                        LocalDateTime fechaProducto;
                        if(Event.getMAXPEOPLEALLOWED() >= maxParticipantes) {
                            if (name.length() >=3 && name.startsWith("\"") && name.endsWith("\"")) {
                                if (args[1].equals("addFood")) {
                                    product = new Event(Integer.toString(id), name, price, date, maxParticipantes, Utilities.getMinTimeMeetings(), "CampusMeals");
                                    fechaProducto = product.getStartDate();
                                    if (fechaProducto.isBefore(now) || maxParticipantes >= Utilities.getMinTimeMels() || maxParticipantes <= 0) {
                                        add = false;
                                    }

                                } else if (args[1].equals("addMeeting")) {
                                    product = new Event(Integer.toString(id), name, price, date, maxParticipantes, Utilities.getMinTimeMels(),"Meetings");
                                    fechaProducto = product.getStartDate();
                                    if (fechaProducto.isBefore(now) || maxParticipantes >= Utilities.getMinTimeMels() || maxParticipantes <= 0) {
                                        add = false;
                                    }
                                }

                                if (add && product != null) {
                                    productHandler.addProduct(product);
                                    PersistenceManager.saveProducts(productHandler);
                                    System.out.println(product);
                                    if (args[1].equals("addFood")) {
                                        System.out.println(Comments.PROD_ADDFOOD);
                                    } else if (args[1].equals("addMeeting")) {
                                        System.out.println(Comments.PROD_ADDMEETINGS);
                                    }
                                }
                                else {
                                    if (maxParticipantes >= Utilities.getMinTimeMels() || maxParticipantes <= 0){
                                        System.out.println(Comments.MAXPEOPLE_EXCEDED);
                                    } else {
                                        System.out.println(Comments.DATE_NOT_VALID);
                                    }
                                }
                            }else{
                                System.out.println(Comments.NAME_HAS_WRONG_FORMAT);
                            }
                        }else{
                            System.out.println(Comments.MAXPEOPLE_EXCEDED);
                        }
                    }else{
                        System.out.println(Comments.ID_NOT_IN_BOUNDARIES);
                    }

                } else System.out.println(Comments.LENGTH_WRONG);

            }
        } catch (NumberFormatException e) {
        System.out.println(Comments.ID_PRICE_AMOUNT_NOT_NUMBER);
        }catch (DateTimeParseException e){
            System.out.println(Comments.INVALID_DATE);
        }

    }
}
