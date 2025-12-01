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
    private String ticketDateOpen;
    private String ticketDateClosed;

    public Ticket(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
    }
    public Ticket() {
        this.id = Utilities.numGenerator(5);
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
        this.ticketDateOpen = LocalDate.now().toString();
    }

    public String getTicketDateOpen() {
        return ticketDateOpen;
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

    public void addProduct(Product product, int cantidad) { //
        if (this.stateTicket != State.CLOSED) {
            stateTicket = State.OPEN;
            if (cantidad + this.getNumeroProductos() < MAXSIZE) {
                if (product != null) {
                    TicketItem tI = busquedaProductoPorID(items,product.getId());
                    if (tI != null) {
                        if ( product.isPersonalizable()) {
                            List<String> textosA= ((ProductPers)product).getTextos();
                            List<String> textosB= ((ProductPers)tI.getProduct()).getTextos();
                            if(new HashSet<>(textosA).equals(new HashSet<>(textosB))){
                                tI.addAmount(cantidad);
                                printTicket();
                            }else{
                                items.add(new TicketItem(product,cantidad));
                                printTicket();
                            }
                        } else if (product.getMinTime().isZero()) {
                            tI.addAmount(cantidad);
                            printTicket();
                        } else {
                            System.out.println(Comments.DUPLICATE_ACTIVITY_IN_TICKET);
                        }
                    } else {
                        items.add(new TicketItem(product, cantidad));
                        printTicket();

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
            Product product = items.get(i).getProduct();
            Category category = product.getCategory();
            int amount = items.get(i).getAmount();
            resultado.put(category,resultado.getOrDefault(category,0)+amount);
        }
        return resultado;
    }

    public void printTicket() {
        int cantidadCategoria;
        double precioTotal = 0;
        Product product;
        double descuentoTotal = 0;
        Map<Category, Integer> cantidadProductoCategoria = getCantidadProductoCategoria();
        StringBuilder sb = new StringBuilder("Ticket: ").append(id);
        if (ticketDateClosed != null) {
            sb.append("-").append(ticketDateClosed);
        }
        sb.append("\n");
        for (TicketItem tI : items) {
            cantidadCategoria =  cantidadProductoCategoria.getOrDefault(tI.getProduct().getCategory(),0);
            product = tI.getProduct();
            sb.append(product.toString(tI.getAmount(),cantidadCategoria));
            if (cantidadCategoria>=2) {
                descuentoTotal += product.TotalPrice() * product.getDiscount() * tI.getAmount();
            }
            precioTotal += product.TotalPrice() * tI.getAmount();
        }
        System.out.print(sb);
        System.out.println("Total price: "+ precioTotal);
        System.out.println("Total discount: "+ descuentoTotal);
        System.out.println("Final price: " + (precioTotal - descuentoTotal));
    }
    public void closeTicket() {
        if(checkIfTicketCanClose()){
            ticketDateClosed = LocalDate.now().toString();
            printTicket();
            stateTicket = State.CLOSED;
        }else{
            System.out.println(Comments.ACTIVITY_IS_EXPIRED);
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
        if (ticketDateClosed != null) {
            sb.append(id).append(ticketDateClosed).append("->").append(stateTicket.toString());
        } else if (ticketDateOpen != null) {
            sb.append(ticketDateOpen).append('-').append(id).append("->").append(stateTicket.toString());
        } else {
        sb.append(id).append("->").append(stateTicket.toString());
        }
        return sb.toString();
    }
}
