package es.upm.etsisi.poo.Commands;

import java.util.ArrayList;
import es.upm.etsisi.poo.Products.Inventory;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Utilities;
public class CommandProd extends Command {
    private ArrayList<Command> commands;

    public CommandProd(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public void checkCommand(String[] commanddiv, String actCommand) {
        int counter = 0;
        boolean flag = false;
        while (counter < commands.size() && !flag) {
            flag = commands.get(counter).isThisCommand(actCommand);
            counter++;
        }
        if (flag) {
            commands.get(counter).execute(commanddiv);
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
    }
    private Inventory productList;

    public CommandProd(String args, Inventory productList) {
        super(args);
        this.productList = productList;
    }
    public void prodAdd() {
        int id;
        try {
            if (slicedCommand.length == 6 && productList.getCapacidad() < Utilities.MAX_LIST) {
                id = Integer.parseInt(slicedCommand[2]);
                String nombre = slicedCommand[3];
                Product.Category categoria = Product.Categoria.getCategoria(slicedCommand[4]);

                if (categoria != null) {
                    double precio = Double.parseDouble(slicedCommand[5]);
                    productList.addProduct(id, nombre, categoria, precio);
                } else System.out.println(Utilities.CATEGORY_WRONG);

            } else {

                if (slicedCommand.length != 6) {
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
    public void prodList() {
        if (slicedCommand.length == 2) {
            productList.printList();
        }
        else System.out.println(Utilities.LENGTH_WRONG);
    }

    public void prodUpdate() {
        int id;
        if (slicedCommand.length == 5) {
            try {
                id = Integer.parseInt(slicedCommand[2]);
                String campo = slicedCommand[3];
                String valor = slicedCommand[4];
                productList.updateProduct(id, campo, valor);
            } catch (NumberFormatException e) {
                System.out.println(Utilities.ID_NOT_NUMBER);
            }
        } else System.out.println(Utilities.LENGTH_WRONG);
    }
    public void prodRemove() {
        int id;
        if (slicedCommand.length == 3) {
            try {
                id = Integer.parseInt(slicedCommand[2]);
                productList.removeProduct(id);
            } catch (NumberFormatException e) {
                System.out.println(Utilities.ID_NOT_NUMBER);
            }
        }
        else System.out.println(Utilities.LENGTH_WRONG);
    }
}
