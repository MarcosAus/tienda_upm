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
import es.upm.etsisi.poo.Validation.ValidacionCloseTickets;
import es.upm.etsisi.poo.Validation.ValidacionAddTickets;
import java.time.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TicketClient.class, name = "TicketClient"),
        @JsonSubTypes.Type(value = TicketBusiness.class, name = "TicketBusiness")
})

public abstract class TicketParam <T extends Vendible> {
    protected ArrayList<TicketItem<T>> items;
    private int id;
    protected State stateTicket;
    private static final int MAXSIZE = 100;
    private String ticketDateOpen;
    private String ticketDateClosed;
    @JsonIgnore
    private PrintStrategy<T> printStrategy;
    @JsonIgnore
    private ValidacionAddTickets validacionTickets;
    @JsonIgnore
    private ValidacionCloseTickets<T> validacionCloseTickets;

    public TicketParam() {
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
    }

    public TicketParam(int id, PrintStrategy<T> printStrategy, ValidacionAddTickets validacionTickets, ValidacionCloseTickets<T> validacionCloseTickets) {
        this.id = id;
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
        this.printStrategy = printStrategy;
        this.validacionTickets = validacionTickets;
        this.validacionCloseTickets = validacionCloseTickets;
    }

    public TicketParam(PrintStrategy<T> printStrategy, ValidacionAddTickets validacionTickets, ValidacionCloseTickets<T> validacionCloseTickets) {
        this.id = Utilities.numGenerator(5);
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
        this.ticketDateOpen = LocalDate.now().toString();
        this.printStrategy = printStrategy;
        this.validacionTickets = validacionTickets;
        this.validacionCloseTickets = validacionCloseTickets;
    }
    @JsonIgnore
    public ValidacionAddTickets getValidacionTickets() {
        return validacionTickets;
    }
    @JsonIgnore
    public PrintStrategy<T> getPrintStrategy() {
        return printStrategy;
    }

    public String getTicketDateOpen() {
        return ticketDateOpen;
    }

    public int getId() {
        return id;
    }

    public ArrayList<TicketItem<T>> getProducts() {
        return items;
    }

    public void updateState(State stateTicket) {
        this.stateTicket = stateTicket;
    }

    public State getTicketState() {
        return stateTicket;
    }
    @JsonIgnore
    public ArrayList<TicketItem<T>> getTicketItems() {
        return items;
    }


    public int getNumeroProductos() {
        int resultado = 0;
        for (TicketItem<T> item : items) {
            resultado += item.getProduct().amountTicket(item.getAmount());
        }
        return resultado;
    }

    public String getTicketDateClosed() {
        return ticketDateClosed;
    }

    public TicketItem<T> busquedaProductoPorID(ArrayList<TicketItem<T>> products, String id) {
        TicketItem<T> resultado = null;
        int indice = 0;
        while (indice < products.size() && !products.get(indice).getProduct().getId().equals(id)) {
            indice++;
        }
        if (indice < products.size()) {
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
                    if (canAdd(element)) {
                        TicketItem<T> tI = busquedaProductoPorID(items, element.getId());
                        if (tI != null) {
                            if (element.isPersonalizable()) {
                                List<String> textosA = ((ProductPers) element).getTextos();
                                List<String> textosB = ((ProductPers) tI.getProduct()).getTextos();
                                if (new HashSet<>(textosA).equals(new HashSet<>(textosB))) {
                                    tI.addAmount(cantidad);
                                    printTicket();
                                } else {
                                    items.add(new TicketItem<T>(element, cantidad));
                                    printTicket();
                                }
                            } else if (element.getMinTime().isZero()) {
                                tI.addAmount(cantidad);
                                printTicket();
                            } else {
                                System.out.println(Comments.DUPLICATE_ACTIVITY_IN_TICKET);
                            }
                        } else {
                            items.add(new TicketItem<T>(element, cantidad));
                            resultado = true;
                            printTicket();

                        }
                    } else {
                        System.out.println(Comments.TYPE_OF_PRODUCT_WRONG);
                    }
                }
            } else {
                System.out.println(Comments.CAPACITY_REACHED);
            }
        }
        return resultado;
    }

    @JsonIgnore
    public Map<Category, Integer> getCantidadProductoCategoria() {
        Map<Category, Integer> resultado = new HashMap<>();
        Product productGeneric;
        for (int i = 0; i < items.size(); i++) {
            T product = items.get(i).getProduct();
            if (!product.getId().endsWith("S")) {
                Category category = ((Product) product).getCategory();
                int amount = items.get(i).getAmount();
                resultado.put(category, resultado.getOrDefault(category, 0) + amount);
            }

        }
        return resultado;
    }

    public boolean removeProduct(String id) {
        if (this.stateTicket != State.CLOSED) {
            boolean resultado = false;
            TicketItem<T> tI = busquedaProductoPorID(items, id);
            if (tI != null) {
                items.remove(tI);
                resultado = true;
            }
            return resultado;
        } else return false;

    }

    public void printTicket() {
        if (printStrategy != null) {
            printStrategy.printTicket(this);
        } else {
            System.out.println(Comments.NO_PRINT_STRATEGY);
        }
    }

    public boolean canAdd(T element){
        if(validacionTickets.add(element)) return true;
        else return false;
    }

    public void closeTicket() {
        if (checkIfTicketCanClose()) {
            ticketDateClosed = LocalDate.now().toString();
            printStrategy.printTicket(this);
            stateTicket = State.CLOSED;
        } else {
            System.out.println(Comments.ACTIVITY_IS_EXPIRED);
        }
    }

    public boolean checkIfTicketCanClose() {
        if (validacionCloseTickets.close(this)) return true;
        else return false;

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
