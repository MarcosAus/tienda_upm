package es.upm.etsisi.poo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.upm.etsisi.poo.Products.*;

import java.util.*;


public class Ticket {
    private final ArrayList<TicketItem> items;
    private String id;
    private State stateTicket;
    private static final int MAXSIZE = 100;

    public Ticket(String id) {
        this.id = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm"))+"-"+id ;
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
    }

    public String getId() {return id;}
    public State getStateTicket() {
        return stateTicket;
    }
    public ArrayList<TicketItem> getProducts() {
        return items;
    }
    public int getNumeroProductos() {
        int resultado = 0;
        for(TicketItem item : items ) {
            if(item.getProduct() instanceof CampusMeals || item.getProduct() instanceof Meetings){
                resultado++;
            }
            else {
                resultado += item.getAmount();
            }
        }
        return resultado;
    }

    public State getTicketState() {
        return this.stateTicket;
    }

    public void updateState(State stateTicket) {
        this.stateTicket = stateTicket;
    }

    public void addProduct(Product product, int cantidad) {
        if(this.stateTicket != State.CLOSED) {
            stateTicket = State.ACTIVE;
            if(cantidad+this.getNumeroProductos()< MAXSIZE) {
                if(product!=null) {
                    TicketItem tI = busquedaProductoPorID(items,product.getId());
                    if (tI != null) {
                        if(product instanceof ProductBasic) {
                            tI.addAmount(cantidad);
                        }
                        else if(product instanceof CampusMeals || product instanceof Meetings) {
                            System.out.println("no se puede añadir mas de una misma comida o reunion al ticket");
                        }
                    }
                    else{
                        items.add(new TicketItem(product, cantidad));
                    }
                }
            }
        }
    }
    public boolean removeProduct(int id) {
        boolean resultado = false;
        Product product = busquedaProductoPorID(products, id);
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
    public  TicketItem busquedaProductoPorID(ArrayList<TicketItem> products, int id) {
        TicketItem resultado = null;
        int indice=0;
        while(indice<products.size() && products.get(indice).getProduct().getId()!=id) {
            indice++;
        }
        if(indice<products.size()) {
            resultado = products.get(indice);
        }
        return resultado;
    }

    public Map<Category,Integer> getCantidadProductoCategoria() {
        Map<Category,Integer> resultado = new HashMap<>();
        Product productGeneric;
        for (int i = 0; i < products.size(); i++) {
            productGeneric = products.get(i);
            if (productGeneric instanceof ProductBasic) { // *** EXPLICAR
                ProductBasic pb = (ProductBasic)productGeneric;
                resultado.put(pb.getCategoria(),resultado.getOrDefault(pb.getCategoria(),0)+1);
                //
            }
        }
        return resultado;
    }


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


    public double calcularPrecio() {
        double precio = 0;
        Iterator<Product> iterator = this.getProducts().iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();

            precio += product.TotalPrice();
        }
        return Math.round(precio);
    }


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

}