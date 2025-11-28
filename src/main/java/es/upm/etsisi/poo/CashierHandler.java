package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Product;

public class CashierHandler extends UserHandler {
    public CashierHandler() {
        super();
    }

    public void newTicketWithId(int ticketId, String cashId) {
        this.getCashiersRecord().get(cashId).getTickets().put(ticketId, new Ticket(ticketId));
    }
    public void newTicketNoId(String cashId) {
        Ticket newTicket = new Ticket();
        this.getCashiersRecord().get(cashId).getTickets().put(newTicket.getId(), newTicket);
    }

    public void addToTicket(int ticketId, String cashId, Product newproduct , int cantidad) {
        Ticket actTicket = this.getCashiersRecord().get(cashId).getTickets().get(ticketId);
        try {
            actTicket.addProduct(newproduct, cantidad);
            if (actTicket.getTicketState().equals(State.EMPTY)){
                actTicket.updateState(State.ACTIVE);
            }
        }
        catch (Exception noProductWithprodId){
            System.out.println("No product with id "+ ticketId +" was found");
        }
    }
    public void removeTicket(int ticketId, String cashId, int prodId) {
        Ticket actTicket = this.getCashiersRecord().get(cashId).getTickets().get(ticketId);
        try {
            actTicket.removeProduct(prodId); //Hay que cambiar las clases de Ticket para que su id sea String, no int. - Marcos
        } catch (Exception noProductWithprodId) {
            System.out.println("No product with id "+ticketId+" was found");
        }
    }
    public void printTicket(int ticketId, String cashId) {
        Ticket actTicket = this.getCashiersRecord().get(cashId).getTickets().get(ticketId);
        actTicket.printTicket();
    }
}
