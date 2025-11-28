package es.upm.etsisi.poo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.upm.etsisi.poo.Products.*;

import java.util.*;


public class Ticket {
    private ArrayList<TicketItem> items;
    private int id;
    private State stateTicket;
    private static final int MAXSIZE = 100;

    public Ticket(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
    }
    public Ticket() {
        this.id = (int)(Math.random()*100000);
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
    }

    public int getId() {return id;}
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
        if (this.stateTicket != State.CLOSED) {
            boolean resultado = false;
            TicketItem tI = busquedaProductoPorID(items, id);
            if  (tI != null) {
                items.remove(tI);
                resultado = true;
            }
            return resultado;
        }
        else return false;

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

    public String printTicket() {
        if (stateTicket != State.CLOSED) {
            double precioTotal = 0;
            double precio;
            double descuento;
            StringBuilder sb = new StringBuilder("Ticket: ").append(id);
            Product product;
            double descuentoTotal = 0;
            Map<Category, Integer> cantidadProductoCategoria = getCantidadProductoCategoria();
            for(TicketItem tI : items) {
                product = tI.getProduct();
                precio= calcularPrecioProducto(tI);
                descuento=calcularDescuentoProducto(tI,cantidadProductoCategoria);
                sb.append("{");
                sb.append(tI.getProduct().toString());
                if(product instanceof Meetings || product instanceof CampusMeals) {
                    sb.append(", actual people in event:");
                    sb.append(tI.getAmount());
                }
                sb.append("}");
                if(product instanceof ProductBasic && descuento>0) {
                    sb.append(" **discount -").append(precio*descuento);
                    sb.append("\n");
                }
            }
            System.out.println("Total price: "+ precioTotal);
            System.out.println("Total discount: "+ descuentoTotal);
            System.out.println("Final price: " + (precioTotal - descuentoTotal));
            stateTicket = State.CLOSED;
            return sb.toString();
        }
        else return "ERROR";

    }

    private double calcularPrecioProducto(TicketItem tI ) {
        double precioTotal = 0;
        Product product = tI.getProduct();
        if(product instanceof Meetings || product instanceof CampusMeals) {
            precioTotal = product.getPrecio()*tI.getAmount();
        } else if (product instanceof ProductPers) {
            precioTotal =product.TotalPrice();
        }
        else precioTotal= product.getPrecio();
        return precioTotal;
    }

    private double calcularDescuentoProducto(TicketItem tI,Map<Category,Integer> cantidad) {
        Product product = tI.getProduct();
        double descuento = 0;
        if(product instanceof ProductBasic) {
            if(cantidad.getOrDefault(((ProductBasic) product).getCategoria(),0)>=2) {
                return ((ProductBasic) product).getCategoria().getDiscount();
            }
        }
        return descuento;
    }
}
