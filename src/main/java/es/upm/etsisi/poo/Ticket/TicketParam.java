package es.upm.etsisi.poo.Ticket;

import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductPers;
import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.State;
import es.upm.etsisi.poo.Strategies.PrintStrategy;
import es.upm.etsisi.poo.TicketItem;
import es.upm.etsisi.poo.Utilities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public abstract class TicketParam <T extends Vendible> {
    protected ArrayList<TicketItem> items;
    private int id;
    protected State stateTicket;
    private static final int MAXSIZE = 100;
    private String ticketDateOpen;
    private String ticketDateClosed;
    private PrintStrategy printStrategy;

    public TicketParam(int id, PrintStrategy printStrategy) {
        this.id = id;
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
        this.printStrategy = printStrategy;
    }

    public TicketParam(PrintStrategy printStrategy) {
        this.id = Utilities.numGenerator(5);
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
        this.ticketDateOpen = LocalDate.now().toString();
        this.printStrategy = printStrategy;
    }

    public String getTicketDateOpen() {
        return ticketDateOpen;
    }
    public int getId() {return id;}
    public ArrayList<TicketItem> getProducts() {
        return items;
    }

    public State getTicketState() {
        return stateTicket;
    }

    public ArrayList<TicketItem> getTicketItems() {
        return items;
    }

    public int getNumeroProductos() {
        int resultado = 0;
        for(TicketItem item : items ) {
            resultado+= item.getProduct().amountTicket(item.getAmount());
        }
        return resultado;
    }

    public String getTicketDateClosed() {
        return ticketDateClosed;
    }

    public TicketItem busquedaProductoPorID(ArrayList<TicketItem> products, int id) {
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

    // Método addProduct actualizado con validación de cliente
    public boolean addProduct(T element, int cantidad) {
        boolean resultado = false;
        if (this.stateTicket != State.CLOSED) {
            stateTicket = State.OPEN;
            if (cantidad + this.getNumeroProductos() < MAXSIZE) {
                if (element != null) {
                    TicketItem tI = busquedaProductoPorID(items,element.getId());
                    if (tI != null) {
                        if (element.isPersonalizable()) {
                            List<String> textosA= ((ProductPers)element).getTextos();
                            List<String> textosB= ((ProductPers)tI.getProduct()).getTextos();
                            if(new HashSet<>(textosA).equals(new HashSet<>(textosB))){
                                tI.addAmount(cantidad);
                                printTicket();
                            }else{
                                items.add(new TicketItem(element,cantidad));
                                printTicket();
                            }
                        } else if (element.getMinTime().isZero()) {
                            tI.addAmount(cantidad);
                            printTicket();
                        } else {
                            System.out.println(Comments.DUPLICATE_ACTIVITY_IN_TICKET);
                        }
                    } else {
                        items.add(new TicketItem(element, cantidad));
                        resultado = true;
                        printTicket();

                    }
                }
            } else {
                System.out.println(Comments.CAPACITY_REACHED);
            }
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
        printStrategy.print();
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


}
