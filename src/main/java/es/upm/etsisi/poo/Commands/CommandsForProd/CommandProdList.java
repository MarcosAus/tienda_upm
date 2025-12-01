package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandProdList extends Command {
    private ProductHandler productHandler;

    public CommandProdList(String name, ProductHandler productHandler) {
        super(name);
        this.productHandler = productHandler;
    }



    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            productHandler.listProducts();
            System.out.println(Comments.PROD_LIST);
        }
        else System.out.println(Comments.LENGTH_WRONG);
    }

}
