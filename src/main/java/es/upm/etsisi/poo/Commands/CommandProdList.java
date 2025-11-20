package es.upm.etsisi.poo.Commands;

import es.upm.etsisi.poo.Products.Inventory;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductBasic;
import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Utilities;

public class CommandProdList extends Command {
    private Inventory productList;

    public CommandProdList(String name, Inventory productList) {
        super(name);
        this.productList = productList;
    }


    public void execute(String[] args) {
        if (args.length == 2) {
            productList.printList();
        }
        else System.out.println(Utilities.LENGTH_WRONG);
    }

}
