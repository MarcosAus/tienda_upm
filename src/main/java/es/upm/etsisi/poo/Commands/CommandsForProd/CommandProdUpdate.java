package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandProdUpdate extends Command {
    private ProductHandler productHandler;

    public CommandProdUpdate(String name, ProductHandler productHandler) {
        super(name);
        this.productHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return name != null && name.toLowerCase().startsWith(this.name);
    }

    @Override
    public void execute(String[] args) {
        int id;
        if (args.length == 5) {
            try {
                id = Integer.parseInt(args[2]);
                String campo = args[3];
                String valor = args[4];
                productHandler.updateProduct(id, campo, valor);
            } catch (NumberFormatException e) {
                System.out.println(Comments.ID_NOT_NUMBER);
            }
        } else System.out.println(Comments.LENGTH_WRONG);
    }
}
