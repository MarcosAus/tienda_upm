package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandProdList extends Command {
    private ProductHandler productHandler;

    public CommandProdList(String name, ProductHandler productHandler) {
        super(name);
        this.productHandler = productHandler;
    }


    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            productHandler.listProducts();
        }
        else System.out.println(Utilities.LENGTH_WRONG);
    }

}
