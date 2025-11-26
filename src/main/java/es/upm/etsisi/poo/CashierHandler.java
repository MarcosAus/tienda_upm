package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Product;

public class CashierHandler extends UserHandler {
    private TicketHandler ticketHandler;
    public CashierHandler(TicketHandler ticketHandler) {
        super();
        this.ticketHandler = ticketHandler;
    }

    public void newTicketWithId(String ticketId, String cashId) {
        this.getCashiersRecord().get(cashId).getTickets().put(ticketId, new Ticket(ticketId));
    }
    public void newTicketNoId() {

    }

    public void addTicket(String ticketId, String cashId, Product newproduct , int cantidad) {
        Ticket actTicket = this.getCashiersRecord().get(cashId).getTickets().get(ticketId);
        try {
            actTicket.addProduct(newproduct, cantidad);
            if (actTicket.getTicketState().equals(State.EMPTY)){
                actTicket.updateState(State.ACTIVE);
            }
        }
        catch (Exception noProductWithprodId){
            System.out.println("No product with id "+ticketId+" was found");
        }
    }
    public void removeTicket(String ticketId, String cashId, String prodId) {
        Ticket actTicket = this.getCashiersRecord().get(cashId).getTickets().get(ticketId);
        try {
            actTicket.removeProduct(prodId); //Hay que cambiar las clases de Ticket para que su id sea String, no int. - Marcos
        } catch (Exception noProductWithprodId) {
            System.out.println("No product with id "+ticketId+" was found");
        }
    }
    public void printTicket(String ticketId, String cashId) {
        Ticket actTicket = this.getCashiersRecord().get(cashId).getTickets().get(ticketId);
        actTicket.printTicket();
    }
}
