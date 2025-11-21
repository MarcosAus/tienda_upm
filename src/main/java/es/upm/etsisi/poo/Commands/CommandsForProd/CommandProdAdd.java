package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.ProductHandler;
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
        try {
            if (args.length == 6 && productHandler.getHandlerSize() < Utilities.MAX_LIST) {
                id = Integer.parseInt(args[2]);
                String name = args[3];
                Category category;
                try {
                    category = Category.valueOf(args[4]);
                } catch (IllegalArgumentException e) {
                    category = null;
                    System.out.println("Category added is invalid");
                }
                if (category != null) {
                    double precio = Double.parseDouble(args[5]);
                    // IMPORTANTE: Para quien lo arregle que cree un producto dentro de este metodo a ser posible. Si es necesario podéis modificar el constructor para que guarde
                    // otro handler o añadir una función al handler que cree el producto.
                    productHandler.addProduct(id, name, category, precio);
                } else System.out.println(Utilities.CATEGORY_WRONG);

            } else {

                if (args.length != 6) {
                    System.out.println(Utilities.LENGTH_WRONG);
                }
                if (productHandler.getHandlerSize() == Utilities.MAX_LIST) {
                    System.out.println(Utilities.PRODUCT_LIST_FULL);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(Utilities.ID_PRICE_NOT_NUMBER);
        }
    }
}
