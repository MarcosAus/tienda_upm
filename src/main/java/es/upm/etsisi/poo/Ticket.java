package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class Ticket {
    private ArrayList<TicketItem> items;
    private int id;
    private State stateTicket;
    private static final int MAXSIZE = 100;
    private String ticketDate;

    public Ticket(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
    }
    public Ticket() {
        this.id = Utilities.numGenerator(5);
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
        this.ticketDate = LocalDate.now().toString();
    }

    public String getTicketDate() {
        return ticketDate;
    }
    public int getId() {return id;}
    public ArrayList<TicketItem> getProducts() {
        return items;
    }
    public int getNumeroProductos() {
        int resultado = 0;
        for(TicketItem item : items ) {
            resultado+= item.getProduct().amountTicket(item.getAmount());
        }
        return resultado;
    }

    public boolean ticketIsFull() {
        return this.getNumeroProductos() == MAXSIZE;
    }

    public State getTicketState() {
        return this.stateTicket;
    }

    public void updateState(State stateTicket) {
        this.stateTicket = stateTicket;
    }

    public void addProduct(Product product, int cantidad) {
        if (this.stateTicket != State.CLOSED) {
            stateTicket = State.ACTIVE;
            if (cantidad + this.getNumeroProductos() < MAXSIZE) {
                if (product != null) {
                    TicketItem tI = busquedaProductoPorID(items,product.getId());
                    if (tI != null) {
                        if (product instanceof ProductBasic) {
                            tI.addAmount(cantidad);
                        }
                        else if(product instanceof CampusMeals || product instanceof Meetings) {
                            System.out.println("no se puede añadir mas de una misma comida o reunion al ticket");
                        }
                    }
                    else {
                        if (product instanceof CampusMeals || product instanceof Meetings){

                        }
                        items.add(new TicketItem(product, cantidad));
                    }
                }
            } else {
                System.out.println(Comments.CAPACITY_REACHED);
            }
        }
    }
    public boolean removeProduct(int id) {
        if (this.stateTicket != State.CLOSED) {
            boolean resultado = false;
            TicketItem tI = busquedaProductoPorID(items, id);
            if (tI != null) {
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
        while (indice<products.size() && products.get(indice).getProduct().getId()!=id) {
            indice++;
        }
        if (indice<products.size()) {
            resultado = products.get(indice);
        }
        return resultado;
    }

    public Map<Category,Integer> getCantidadProductoCategoria() {
        Map<Category,Integer> resultado = new HashMap<>();
        Product productGeneric;
        for (int i = 0; i < items.size(); i++) {
            productGeneric = items.get(i).getProduct();
            resultado.put(productGeneric.getCategory(),resultado.getOrDefault(productGeneric.getCategory(),0)+1);
        }
        return resultado;
    }

    public void printTicket() {
        if(checkIfTicketCanClose()){
            int cantidadCategoria;
            double precioTotal = 0;
            Product product;
            double descuentoTotal = 0;
            Map<Category, Integer> cantidadProductoCategoria = getCantidadProductoCategoria();
            StringBuilder sb = new StringBuilder("Ticket: ").append(id);
            for (TicketItem tI : items) {
                cantidadCategoria =  cantidadProductoCategoria.getOrDefault(tI.getProduct().getCategory(),0);
                product = tI.getProduct();
                sb.append(product.toString(tI.getAmount(),cantidadCategoria));
                if (cantidadCategoria>=2){
                    descuentoTotal += product.TotalPrice()* product.getDiscount();
                }
                precioTotal += product.TotalPrice();
            }
            System.out.println(sb);
            System.out.println("Total price: "+ precioTotal);
            System.out.println("Total discount: "+ descuentoTotal);
            System.out.println("Final price: " + (precioTotal - descuentoTotal));
            stateTicket = State.CLOSED;
            ticketDate = LocalDate.now().toString();
        }
        else{
            System.out.println("The meals or meeting is expired ......");
        }
    }
    public boolean checkIfTicketCanClose() {
        LocalDateTime now = LocalDateTime.now();

        for (TicketItem item : items) {
            Product p = item.getProduct();

            Duration minTime = p.getMinTime();
            LocalDateTime eventDate = p.getStartDate();

            if (eventDate == null || minTime.isZero()) {
                continue;
            }

            Duration timeLeft = Duration.between(now, eventDate);

            if (timeLeft.compareTo(minTime) < 0) {
                return false;
            }
        }
        return true;
    }

    public String listTicket() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("->").append(stateTicket.toString());
        return sb.toString();
    }
}
