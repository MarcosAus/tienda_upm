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
                        if (product instanceof CampusMeals || product instanceof Meetings){

                        }
                        items.add(new TicketItem(product, cantidad));
                    }
                }
            }
        }
    }
    public boolean removeProduct(int id) {
        boolean resultado = false;
        TicketItem tI = busquedaProductoPorID(items, id);
        if  (tI != null) {
            items.remove(tI);
            resultado = true;
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
        for (int i = 0; i < items.size(); i++) {
            productGeneric = items.get(i).getProduct();
            if (productGeneric instanceof ProductBasic) { // *** EXPLICAR
                ProductBasic pb = (ProductBasic)productGeneric;
                resultado.put(pb.getCategoria(),resultado.getOrDefault(pb.getCategoria(),0)+1);
                //
            }
        }
        return resultado;
    }


    public boolean tieneDescuento(Category categoria,Map<Category,Integer> cantidad) {
        return (cantidad.getOrDefault(categoria,0)>=2);
    }

    public void printTicket() {
        double precioTotal = 0;
        double descuentoTotal = 0;
        Map<Category, Integer> cantidadProductoCategoria = getCantidadProductoCategoria();




        System.out.println("Total price: "+ precio);
        System.out.println("Total discount: "+ descuentos);
        System.out.println("Final price: " + (precio - descuentos));
    }


    public double calcularPrecioProducto(TicketItem tI ) {
        double precioTotal = 0;
        Product product = tI.getProduct();
        if(product instanceof Meetings || product instanceof CampusMeals) {
            precioTotal = pro
        }
    }


    public double calcularDescuentoProducto(Product product,Map<Category,Integer> cantidad) {
        double descuento = 0;
        if(product instanceof ProductBasic) {
            if(tieneDescuento(((ProductBasic) product).getCategoria(),cantidad)) {
                return ((ProductBasic) product).getCategoria().getDiscount()*product.getPrecio();
            }
        }
        return descuento;
    }

}
