package es.upm.etsisi.poo.Commands;

import es.upm.etsisi.poo.Products.Inventory;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductBasic;
import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Utilities;

public class CommandProdRemove extends Command {
    private Inventory productList;
    public CommandProdRemove(String name, Inventory productList) {
        super(name);
        this.productList = productList;
    }

    public void execute(String[] args) {
        int id;
        if (args.length == 3) {
            try {
                id = Integer.parseInt(args[2]);
                productList.removeProduct(id);
            } catch (NumberFormatException e) {
                System.out.println(Utilities.ID_NOT_NUMBER);
            }
        }
        else System.out.println(Utilities.LENGTH_WRONG);
    }
}
