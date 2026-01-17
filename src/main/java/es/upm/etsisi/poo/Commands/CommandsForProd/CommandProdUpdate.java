package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandProdUpdate implements Command {
    private ProductHandler productHandler;
    String name;

    public CommandProdUpdate(String name, ProductHandler productHandler) {
        this.name = name;
        this.productHandler = productHandler;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
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
                System.out.println(Comments.PROD_UPDATE);
            } catch (NumberFormatException e) {
                System.out.println(Comments.ID_NOT_NUMBER);
            } catch (NullPointerException e) {
                System.out.println(Comments.PRODUCT_NOT_FOUND);
            }
        } else System.out.println(Comments.LENGTH_WRONG);
    }
}
