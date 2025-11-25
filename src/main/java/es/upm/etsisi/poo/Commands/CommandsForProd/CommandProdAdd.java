package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
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
    public boolean isThisCommand(String name){
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args){
        int id;
        double price;
        String name;
        int idRandom = -1;
        try {
            if (productHandler.getHandlerSize() == Utilities.MAX_LIST) {
                System.out.println(Utilities.PRODUCT_LIST_FULL);
            } else {
                if(args.length == 5) {
                    while(idRandom == -1 || productHandler.getProduct(idRandom) != null) {
                        double generate = (double) idRandom;
                        generate = Math.random() * (200);
                        idRandom = (int) generate;
                    }
                    id = (int) idRandom;
                    name = args[2];
                    Category category;
                    try {
                        category = Category.valueOf(args[3]);
                    } catch (IllegalArgumentException e) {
                        category = null;
                        System.out.println("Category added is invalid");
                    }
                    if (category != null) {
                        price = Double.parseDouble(args[4]);
                        // IMPORTANTE: ESTO NO FUNCIONA. Antes le solicitaba a product handeler que creara el producto. Esto
                        // no lo podemos hacer. Tenemos que detectar que tipo de producto esta creando el usuario y crearlo en
                        // el propio comando. Por ahora crea solo un Product basic para que no de error
                        Product product;
                        product = new ProductBasic(category, name, id, price);

                        productHandler.addProduct(product);
                    } else System.out.println(Utilities.CATEGORY_WRONG);
                }else if(args.length == 7){
                    id = Integer.parseInt(args[2]);
                    name = args[3];
                    Category category;
                    try {
                        category = Category.valueOf(args[4]);
                    } catch (IllegalArgumentException e) {
                        category = null;
                        System.out.println("Category added is invalid");
                    }
                    if (category != null) {
                        price = Double.parseDouble(args[5]);
                        int maxText = Integer.parseInt(args[6]);
                        Product product;
                        product = new ProductPers(category, id, name, price, maxText);

                        productHandler.addProduct(product);
                    }else System.out.println(Utilities.CATEGORY_WRONG);
                }else if(args.length == 6){
                    try{
                        id = Integer.parseInt(args[2]);
                    }catch(IllegalArgumentException e){
                        while(idRandom == -1 || productHandler.getProduct(idRandom) != null) {
                            double generate = (double) idRandom;
                            generate = Math.random() * (200);
                            idRandom = (int) generate;
                        }
                        id = (int) idRandom;
                        name = args[2];
                        Category category;
                        try {
                            category = Category.valueOf(args[3]);
                        } catch (IllegalArgumentException ex) {
                            category = null;
                            System.out.println("Category added is invalid");
                        }
                        if (category != null) {
                            price = Double.parseDouble(args[4]);
                            if (idRandom != -1){
                                int MaxText = Integer.parseInt(args[5]);
                            }

                            Product product;
                            product = new ProductBasic(category, name, id, price);

                            productHandler.addProduct(product);
                        } else System.out.println(Utilities.CATEGORY_WRONG);
                    }


                }


            }
        } catch (NumberFormatException e) {
            System.out.println(Utilities.ID_PRICE_NOT_NUMBER);
        }
    }
}
