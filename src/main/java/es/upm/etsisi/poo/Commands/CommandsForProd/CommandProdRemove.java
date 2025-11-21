package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandProdRemove extends Command {
    private ProductHandler productHandler;
    public CommandProdRemove(String name, ProductHandler productHandler) {
        super(name);
        this.productHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
        int id;
        if (args.length == 3) {
            try {
                id = Integer.parseInt(args[2]);
                productHandler.removeProduct(id);
            } catch (NumberFormatException e) {
                System.out.println(Utilities.ID_NOT_NUMBER);
            }
        }
        else System.out.println(Utilities.LENGTH_WRONG);
    }
}
