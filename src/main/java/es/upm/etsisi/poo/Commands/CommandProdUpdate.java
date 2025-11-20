package es.upm.etsisi.poo.Commands;

import es.upm.etsisi.poo.Products.Inventory;
import es.upm.etsisi.poo.Utilities;

public class CommandProdUpdate extends Command {
    private Inventory productList;

    public CommandProdUpdate(String name, Inventory inventory) {
        super(name);
        this.productList = inventory;
    }

    public void execute(String[] args) {
        int id;
        if (args.length == 5) {
            try {
                id = Integer.parseInt(args[2]);
                String campo = args[3];
                String valor = args[4];
                productList.updateProduct(id, campo, valor);
            } catch (NumberFormatException e) {
                System.out.println(Utilities.ID_NOT_NUMBER);
            }
        } else System.out.println(Utilities.LENGTH_WRONG);
    }
}
