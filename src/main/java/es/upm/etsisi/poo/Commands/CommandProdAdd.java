package es.upm.etsisi.poo.Commands;

import es.upm.etsisi.poo.Products.Inventory;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductBasic;
import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Utilities;

public class CommandProdAdd extends Command{
    private Inventory productList;

    public CommandProdAdd(String name,  Inventory productList) {
        super(name);
        this.productList = productList;
    }

    public void execute(String[] args) throws Exception{
        int id;
        try {
            if (args.length == 6 && productList.getCapacidad() < Utilities.MAX_LIST) {
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
                    productList.addProduct(id, name, category, precio);
                } else System.out.println(Utilities.CATEGORY_WRONG);

            } else {

                if (args.length != 6) {
                    System.out.println(Utilities.LENGTH_WRONG);
                }
                if (productList.getCapacidad() == Utilities.MAX_LIST) {
                    System.out.println(Utilities.PRODUCT_LIST_FULL);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(Utilities.ID_PRICE_NOT_NUMBER);
        }
    }
}
