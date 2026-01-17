package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandProdRemove implements Command {
    private ProductHandler productHandler;
    String name;

    public CommandProdRemove(String name, ProductHandler productHandler) {
        this.name = name;
        this.productHandler = productHandler;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
    }


    @Override
    public void execute(String[] args) {
        int id;
        if (args.length == 3) {
            try {
                id = Integer.parseInt(args[2]);
                productHandler.removeProduct(id);
                System.out.println(Comments.PROD_REMOVE);
            } catch (NumberFormatException e) {
                System.out.println(Comments.ID_NOT_NUMBER);
            }
        }
        else System.out.println(Comments.LENGTH_WRONG);
    }
}
