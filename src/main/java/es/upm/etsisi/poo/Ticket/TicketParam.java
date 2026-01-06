package es.upm.etsisi.poo.Ticket;

import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.State;
import es.upm.etsisi.poo.TicketItem;
import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Utilities;
import jdk.jshell.execution.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class TicketParam <T extends Client> {
    private ArrayList<TicketItem> items;
    private int id;
    private State stateTicket;
    private static final int MAXSIZE = 100;
    private String ticketDateOpen;
    private String ticketDateClosed;
    private T client;

    public TicketParam(int id, T client) {
        this.id = id;
        this.client = client;
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
    }

    public TicketParam(T client) {
        this.id = Utilities.numGenerator(5);
        this.client = client;
        this.items = new ArrayList<>();
        this.stateTicket = State.EMPTY;
        this.ticketDateOpen = LocalDate.now().toString();
    }
    public T getClient() {
        return client;
    }
    public void setClient(T client) {
        if (this.stateTicket != State.CLOSED){
           this.client = client;
        }
    }
    public String getTicketDateOpen() {
        return ticketDateOpen;
    }
    public int getId() {return id;}
    public ArrayList<TicketItem> getProducts() {
        return items;
    }


    // Método addProduct actualizado con validación de cliente
    public boolean addProduct(Product product, int cantidad) {
        if (client == null) {
            System.out.println("No se puede añadir producto sin cliente asignado");
            return false;
        }

        // Validaciones específicas por tipo de cliente
        if (!client.isBusiness() && product.getCategory().equals(Category.EMPRESARIAL)) {
            System.out.println("Producto empresarial no disponible para clientes personales");
            return false;
        }

        // Resto de la lógica original...
        boolean resultado = false;
        if (this.stateTicket != State.CLOSED) {
            stateTicket = State.OPEN;
            if (cantidad + this.getNumeroProductos() < MAXSIZE) {
                if (product != null) {
                    TicketItem tI = busquedaProductoPorID(items, product.getId());
                    if (tI != null) {
                        if (product.isPersonalizable()) {
                            List<String> textosA = ((ProductPers) product).getTextos();
                            List<String> textosB = ((ProductPers) tI.getProduct()).getTextos();
                            if (new HashSet<>(textosA).equals(new HashSet<>(textosB))) {
                                tI.addAmount(cantidad);
                                printTicket();
                            } else {
                                items.add(new TicketItem(product, cantidad));
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


    public void printTicket() {
        int cantidadCategoria;
        double precioTotal = 0;
        Product product;
        double descuentoTotal = 0;
        Map<Category, Integer> cantidadProductoCategoria = getCantidadProductoCategoria();

        StringBuilder sb = new StringBuilder("=== TICKET ").append(id).append(" ===\n");

        // Información del cliente
        if (client != null) {
            sb.append("Cliente: ").append(client.getNombre());
            sb.append(" (").append(getClienteTipo()).append(")\n");
            sb.append("ID: ").append(getClienteId()).append("\n");
        }

        if (ticketDateClosed != null) {
            sb.append("Fecha cierre: ").append(ticketDateClosed).append("\n");
        }
        sb.append("Fecha apertura: ").append(ticketDateOpen).append("\n");
        sb.append("Estado: ").append(stateTicket).append("\n\n");
        sb.append("=== PRODUCTOS ===\n");

        // Aplicar descuentos específicos por tipo de cliente
        double descuentoCliente = calcularDescuentoTipoCliente();

        for (TicketItem tI : items) {
            cantidadCategoria = cantidadProductoCategoria.getOrDefault(tI.getProduct().getCategory(), 0);
            product = tI.getProduct();
            sb.append(product.toString(tI.getAmount(), cantidadCategoria));

            double precioItem = product.TotalPrice() * tI.getAmount();
            precioTotal += precioItem;

            // Descuento por categoría
            if (cantidadCategoria >= 2) {
                descuentoTotal += precioItem * product.getDiscount();
            }
        }

        // Aplicar descuento del cliente
        descuentoTotal += precioTotal * descuentoCliente;

        System.out.print(sb);
        System.out.println("Total precio: " + precioTotal);
        System.out.println("Descuento productos: " + (precioTotal * descuentoTotal));
        System.out.println("Descuento cliente (" + getClienteTipo() + "): " + (precioTotal * descuentoCliente));
        System.out.println("Descuento total: " + descuentoTotal);
        System.out.println("Precio final: " + (precioTotal - descuentoTotal));
    }
}
