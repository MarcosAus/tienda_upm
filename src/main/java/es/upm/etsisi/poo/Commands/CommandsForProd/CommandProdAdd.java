package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductBasic;
import es.upm.etsisi.poo.Products.ProductPers;
import es.upm.etsisi.poo.Utilities;

public class CommandProdAdd extends Command {
    private ProductHandler productHandler;

    public CommandProdAdd(String name,  ProductHandler productList) {
        super(name);
        this.productHandler = productList;
    }


    @Override
    public void execute(String[] args) { // fixme hay que modificarlo porque no maneja el tiempo minimo de creacion de 72 o 12 horas
        Category category;
        int id;
        String name;
        double price;
        int MaxText;
        Product product;
        try {
            if (productHandler.getHandlerSize() == Utilities.MAX_LIST) {
                System.out.println(Comments.PRODUCT_LIST_FULL);
            } else {
                if(args.length == 5) {
                    id = Utilities.idAleatorio(productHandler);
                    name = args[2];
                    if (name.length() >=3 && name.startsWith("\"") && name.endsWith("\"")) {
                        name = name.substring(1, name.length()-1);
                        try {
                            category = Category.valueOf(args[3].toUpperCase());
                        } catch (IllegalArgumentException e) {
                            category = null;
                            System.out.println(Comments.CATEGORY_INVALID);
                        }
                        if (category != null) {
                            price = Double.parseDouble(args[4]);

                            product = new ProductBasic(category, name, id, price);
                            productHandler.addProduct(product);
                            System.out.println(product.toString());
                            System.out.println(Comments.PROD_ADD);
                        } else System.out.println(Comments.CATEGORY_WRONG);
                    } else{
                        System.out.println(Comments.NAME_HAS_WRONG_FORMAT);
                    }

                }else if(args.length == 7){
                    id = Integer.parseInt(args[2]);
                    name = args[3];
                    if(id>0 && id<=99999){
                        if (name.length() >=3 && name.startsWith("\"") && name.endsWith("\"")) {
                            name = name.substring(1, name.length()-1);
                            try {
                                category = Category.valueOf(args[4]);
                            } catch (IllegalArgumentException e) {
                                category = null;
                                System.out.println(Comments.CATEGORY_INVALID);
                            }
                            if (category != null) {
                                price = Double.parseDouble(args[5]);
                                MaxText = Integer.parseInt(args[6]);

                                product = new ProductPers(category, id, name, price, MaxText);

                                productHandler.addProduct(product);
                                System.out.println(product.toString());
                                System.out.println(Comments.PROD_ADD);
                            } else System.out.println(Comments.CATEGORY_WRONG);
                        }
                        else {
                            System.out.println(Comments.NAME_HAS_WRONG_FORMAT);
                        }
                    } else{
                        System.out.println(Comments.ID_NOT_IN_BOUNDARIES);
                    }
                } else if(args.length == 6) {
                    try {
                        id = Integer.parseInt(args[2]);
                        if (id>0 && id<=99999) {
                            name = args[3];
                            try {
                                category = Category.valueOf(args[4]);
                            } catch (IllegalArgumentException e) {
                                category = null;
                            }
                            if (category != null) {
                                price = Double.parseDouble(args[5]);
                                product = new ProductBasic(category, name, id, price);
                                productHandler.addProduct(product);
                                System.out.println(product.toString());
                                System.out.println(Comments.PROD_ADD);
                            } else System.out.println(Comments.CATEGORY_WRONG);
                        }else{
                            System.out.println(Comments.ID_NOT_IN_BOUNDARIES);
                        }
                    } catch(IllegalArgumentException e){
                        id = Utilities.idAleatorio(productHandler);
                        name = args[2];
                        try {
                            category = Category.valueOf(args[3].toUpperCase());
                        } catch (IllegalArgumentException ex) {
                            category = null;
                        }
                        if (category != null) {
                            price = Double.parseDouble(args[4]);
                            if (!Integer.toString(id).equals(args[2])) {
                                MaxText = Integer.parseInt(args[5]);
                                product = new ProductPers(category, id, name, price, MaxText);
                            } else{
                                product = new ProductBasic(category, name, id, price);

                            }

                            productHandler.addProduct(product);
                            System.out.println(product.toString());
                            System.out.println(Comments.PROD_ADD);
                        } else System.out.println(Comments.CATEGORY_WRONG);
                    }
                } else System.out.println(Comments.LENGTH_WRONG);
            }
        } catch (NumberFormatException e) {
            System.out.println(Comments.ID_PRICE_NOT_NUMBER);
        } catch (IllegalArgumentException e) {
            System.out.println(Comments.MAX_PERS_EXCEEDED);
        }
    }
}
