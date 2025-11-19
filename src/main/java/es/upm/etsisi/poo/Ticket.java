package es.upm.etsisi.poo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductBasic;
import es.upm.etsisi.poo.Products.ProductPers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;


public class Ticket {
    /**
     * Array de productos actuales en el ticket
     */
    private final ArrayList<Product> products;
    private String id;
    private State stateTicket;
    private final String fechaApertura;
    private  String fechaCierre;


    /**
     * Constructor de la clase Ticket
     */
    public Ticket(String id) {
        this.id = id;
        this.products = new ArrayList<>();
        this.stateTicket = State.EMPTY;
        this.fechaApertura = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm"));
    }

    public String getId() {return id;}
    public String getFechaApertura(){ return fechaApertura;}
    public String getFechaCierre(){ return fechaCierre;}
    public State getStateTicket() {
        return stateTicket;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setFechaDeCierre(String fecha){this.fechaCierre = fecha;}
    public void setStateTicket(State stateTicket) {
        this.stateTicket = stateTicket;
    }

    /**
     * Metodo que devuelve el tamaño del ticket
     * @return Devuelve el numero de productos que contiene el ticket
     */
    public int getNumeroProductos() {
        return products.size();
    }

    public void updateState(State stateTicket) {
        this.stateTicket = stateTicket;
    }

    //Todo lo que sigue debería de estar en el handler


    /**
     * Metodo que añade productos al ticket
     * @param product Objeto clase Producto que se quiere meter al ticket
     * @param cantidad El numero de productos que se deben añadir al ticket
     */
    public void addProduct(Product product, int cantidad) {
        if (product != null) {
            for (int i = 0; i < cantidad; i++) {
                products.add(product);
            }
            products.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
            System.out.println("ticket add: ok");
        }
        else System.out.println("This product does not exist. No products were added");
    }


    /**
     * Metodo que revisa la cantidad de productos de una misma categoria que hay en el ticket
     * @return Devuelve un Array de enteros donde cada elemento representa el numero de productos de una categoria
     */
    public Map<Category,Integer> getCantidadProductoCategoria() {
        Map<Category,Integer> resultado = new HashMap<>();
        Product productGeneric;
        for(int i = 0; i < products.size(); i++){
            productGeneric = products.get(i);
            if(productGeneric instanceof ProductBasic){
                ProductBasic pb = (ProductBasic)productGeneric;
                resultado.put(pb.getCategoria(),resultado.getOrDefault(pb.getCategoria(),0)+1);
            }
        }
        return resultado;
    }

    /**
     * Metodo que revisa si hay descuento de alguna categoria en especifico
     * @param categoria Categoria de la que se quiere comprobar si hay descuento
     * @param cantidadProductos Array de enteros que contiene la cantidad de productos de cada categoria
     * @return Devuelve true si hay descuento de la categoria parametro y false si no lo hay
     */
    public boolean tieneDescuento(String categoria, int[] cantidadProductos) {
        switch (categoria) {
            case "STATIONERY":
                return cantidadProductos[1] > 1;
            case "CLOTHES":
                return cantidadProductos[2] > 1;
            case "BOOK":
                return cantidadProductos[3] > 1;
            case "ELECTRONICS":
                return cantidadProductos[4] > 1;
            default:
                return false;
        }
    }

    /**
     * Metodo que imprime cada producto del ticket por una linea y, si tiene descuento, lo imprime junto al producto
     */
    public void printTicket() {
        double precio = calcularPrecio();
        double descuentos = calcularDescuentoTotal();
        Map<Category, Integer> descuentoPorCategoria = getCantidadProductoCategoria();

        ArrayList<Product> products = this.getProducts();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();

            System.out.print(product);
            if (product instanceof ProductBasic) {
                ProductBasic pb = (ProductBasic)product;
                if (pb.getCategoria().getDiscount()!=0) { // Solo tendrá en cuenta los descuentos >0
                    System.out.print("**discount -"+ pb.getCategoria().getDiscount());
                }
            }

            System.out.println();
        }

        System.out.println("Total price: "+ precio);
        System.out.println("Total discount: "+ descuentos);
        System.out.println("Final price: " + (precio - descuentos));
    }

    /**
     * Metodo que calcula el precio total de todos los articulos sin descuento
     * @return Devuelve un double que representa el precio sin descuento
     */
    public double calcularPrecio() {
        double precio = 0;
        Iterator<Product> iterator = this.getProducts().iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();

            precio += product.precioTotal();
        }
        return Math.round(precio);
    }

    /**
     * Metodo que calcula el descuento a aplicarle al precio total del ticket
     * @return Devuelve un double que representa el descuento que se deba restar del precio total
     */
    public double calcularDescuentoTotal() {
        double descuento = 0;
        Map<Category, Integer> cantidadProductos = this.getCantidadProductoCategoria();
        Iterator<Product> iterator = this.getProducts().iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if(product instanceof ProductBasic){
                ProductBasic pb = (ProductBasic)product;
                if (pb.getCategoria().getDiscount()!=0) {
                    descuento += pb.getCategoria().getDiscount();
                }
            }



        }
        return ((double) Math.round(descuento * 1000) /1000);
    }

    /**
     * Metodo que elimina por completo un producto y todas sus apariciones del ticket
     * @param id Numero Identificador del producto que se quiere buscar y eliminar
     */
    public boolean removeProduct(int id) {
        boolean resultado = false;
        Product product = Utilities.busquedaProductoPorID(products, id);
        if  (product != null) {
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product product1 = iterator.next();
                if (product1.equals(product)) {
                    System.out.println(product1);
                    iterator.remove();
                    resultado = true;
                }
            }
        }
        return resultado;
    }
    public State getEstadoTicket() {
        return stateTicket;
    }
}