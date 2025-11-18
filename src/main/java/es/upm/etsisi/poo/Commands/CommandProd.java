package es.upm.etsisi.poo.Commands;

import es.upm.etsisi.poo.Inventory;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Utilities;

public class CommandProd extends Command {

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
                Product.Categoria categoria = Product.Categoria.getCategoria(slicedCommand[4]);

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
