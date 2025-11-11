package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
    /**
     * Array de productos que contiene los productos disponibles en la tienda
     */
    private ArrayList<Producto> listaProductos;

    /**
     * Constructor de la clase inventario
     */
    public Inventory() {
        this.listaProductos = new ArrayList<>();
    }

    /**
     * Metodo que devuelve la cantidad de productos disponibles en la tienda
     * @return Devuelve un entero que representa el numero total de productos disponibles en la tienda
     */
    public int getCapacidad() {
        return listaProductos.size();
    }

    /**
     * Metodo que permite acceder a la lista de productos de la tienda
     * @return Devuelve una lista con los productos disponibles en la tienda hasta el momento
     */
    public ArrayList<Producto> getLista() {
        return listaProductos;
    }

    /**
     * Metodo que busca si existe un producto en el inventario y, en caso contrario, lo añade
     * Si ya existe en la lista, informa al usuario de ello
     * @param id Numero identificador del producto
     * @param nombre String que se quiere asignar como nombre al producto
     * @param categoria Categorio a la que pertenece el producto
     * @param precio Precio del producto
     */
    public void addProduct(int id, String nombre, Producto.Categoria categoria, double precio) {
        if (id >= 0) {
            Producto existente = Utilities.busquedaProductoPorID(listaProductos, id);

            if (existente == null) {
                if (nombre.isEmpty() || nombre.length() > 100) System.out.println("Name length is incorrect");
                else if (precio < 0) System.out.println("Price cannot be negative");
                else {
                    Producto producto = new Producto(id, nombre, categoria, precio);
                    listaProductos.add(producto);
                    System.out.println(producto + "\nprod add: ok");
                }
            }
            else System.out.println("A Product with the same ID already exists:\n" + existente);
        }

        else System.out.println("ID cannot be negative");
    }

    /**
     * Metodo que busca un producto dentro del inventario y cambia el atributo deseado
     * Si no encuentra el producto, informa al usuario de ello
     * @param id Numero identificador del producto que se quiere actualizar
     * @param campo Atributo que se quiere actualizar, puede ser el nombre, la categoria o el precio
     * @param valor Nuevo valor del atributo que se quiere actualizar
     */
    public void updateProduct(int id, String campo, String valor) {
        Producto producto = Utilities.busquedaProductoPorID(listaProductos, id);

        if (producto != null) {
            switch (campo.toUpperCase()) {
                case "NAME":
                    producto.setNombre(valor);
                    break;

                case "CATEGORY":
                    Producto.Categoria categoria = Producto.Categoria.getCategoria(valor);
                    if (categoria != null) {
                        producto.setCategoria(categoria);
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
                        producto.setPrecio(nuevoPrecio);
                    } catch (NumberFormatException e) {
                        System.out.println("Input Price is not a number");
                    }
                    break;

                default:
                    System.out.println("Field to change must be NAME|CATEGORY|PRICE");
                    break;
            }
            System.out.println(producto + "\nprod update: ok");
        }
        else {
            System.out.println("Product not found");
        }
    }

    /**
     * Metodo que busca un producto en el inventario y lo elimina de la lista
     * Si no lo encuentra, informa al usuario de ello
     * @param id Numero identificador del producto que se desea eliminar
     */
    public void removeProduct(int id) {
        Producto producto = Utilities.busquedaProductoPorID(listaProductos, id);

        if (producto != null) {
            System.out.println(producto);
            listaProductos.remove(producto);
            System.out.println("prod remove: ok");
        } else {
            System.out.println("Product not found");
        }
    }

    /**
     * Metodo que imprime la lista de productos del inventario por pantalla
     */
    public void printList() {
        System.out.println("Catalog:");
        Iterator<Producto> iterator = listaProductos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            System.out.println("    " + producto);
        }
        System.out.println("prod list: ok");
    }
}
