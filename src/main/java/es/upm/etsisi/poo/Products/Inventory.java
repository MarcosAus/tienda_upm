package es.upm.etsisi.poo.Products;

import es.upm.etsisi.poo.Utilities;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
    private ArrayList<Product> listaProducts;
    private static final int MAXPRODS  =200;


    public Inventory() {
        this.listaProducts = new ArrayList<>();
    }

    public int getCapacidad() {
        return listaProducts.size();
    }

    public ArrayList<Product> getLista() {
        return listaProducts;
    }

    /**
     * Metodo que busca si existe un producto en el inventario y, en caso contrario, lo añade
     * Si ya existe en la lista, informa al usuario de ello
     * @param id Numero identificador del producto
     * @param nombre String que se quiere asignar como nombre al producto
     * @param categoria Categorio a la que pertenece el producto
     * @param precio Precio del producto
     */
    /*public void addProduct(int id, String nombre, Category categoria, double precio) {
        if (id >= 0) {
            Product existente = Utilities.busquedaProductoPorID(listaProducts, id);

            if (existente == null) {
                if (nombre.isEmpty() || nombre.length() > 100) System.out.println("Name length is incorrect");
                else if (precio < 0) System.out.println("Price cannot be negative");
                else {
                    Product product = new Product(id, nombre, categoria, precio);
                    listaProducts.add(product);
                    System.out.println(product + "\nprod add: ok");
                }
            }
            else System.out.println("A Product with the same ID already exists:\n" + existente);
        }

        else System.out.println("ID cannot be negative");
    }*/

    /**
     * Metodo que busca un producto dentro del inventario y cambia el atributo deseado
     * Si no encuentra el producto, informa al usuario de ello
     * @param id Numero identificador del producto que se quiere actualizar
     * @param campo Atributo que se quiere actualizar, puede ser el nombre, la categoria o el precio
     * @param valor Nuevo valor del atributo que se quiere actualizar
     */
    /*public void updateProduct(int id, String campo, String valor) {
        Product product = Utilities.busquedaProductoPorID(listaProducts, id);

        if (product != null) {
            switch (campo.toUpperCase()) {
                case "NAME":
                    product.setName(valor);
                    break;

                case "CATEGORY":
                    Category category = Category.getCategory(valor);
                    if (category != null) {
                        product.setCategoria(category);
                    } else {
                        System.out.println("Category is wrong");
                    }
                    break;

                case "PRICE":
                    try {
                        double nuevoPrecio = Double.parseDouble(valor);
                        if (nuevoPrecio < 0) {
                            System.out.println("Price cannot be negative");
                        }
                        product.setPrice(nuevoPrecio);
                    } catch (NumberFormatException e) {
                        System.out.println("Input Price is not a number");
                    }
                    break;

                default:
                    System.out.println("Field to change must be NAME|CATEGORY|PRICE");
                    break;
            }
            System.out.println(product + "\nprod update: ok");
        }
        else {
            System.out.println("Product not found");
        }
    }*/

    /**
     * Metodo que busca un producto en el inventario y lo elimina de la lista
     * Si no lo encuentra, informa al usuario de ello
     * @param id Numero identificador del producto que se desea eliminar
     */
    /*public void removeProduct(int id) {
        Product product = Utilities.busquedaProductoPorID(listaProducts, id);

        if (product != null) {
            System.out.println(product);
            listaProducts.remove(product);
            System.out.println("prod remove: ok");
        } else {
            System.out.println("Product not found");
        }
    }*/

    /**
     * Metodo que imprime la lista de productos del inventario por pantalla
     */
    /*public void printList() {
        System.out.println("Catalog:");
        Iterator<Product> iterator = listaProducts.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            System.out.println("    " + product);
        }
        System.out.println("prod list: ok");
    }*/
}
