package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Ticket.Ticket;
import es.upm.etsisi.poo.Ticket.TicketBusiness;
import es.upm.etsisi.poo.Ticket.TicketClient;

import java.util.ArrayList;

public class TicketHandler {
    private ArrayList<Ticket> tickets;

    public TicketHandler() {
        this.tickets = new ArrayList<>();
    }
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public int newTicketClient(){
        //Genera un ticket con id aleatorio
        TicketClient actTicket = new TicketClient();
        tickets.add(actTicket);
        return actTicket.getId();
    }
    public int newTicketClient(int id){
        TicketClient actTicket = new TicketClient(newTicketIdFinder(id));
        tickets.add(actTicket);
        return actTicket.getId();
    }
    public int newTicketBusiness(){
        //Genera un ticket con id aleatorio
        TicketBusiness actTicket = new TicketBusiness();
        tickets.add(actTicket);
        return actTicket.getId();
    }
    public int newTicketBusiness(int id){
        TicketBusiness actTicket = new TicketBusiness(newTicketIdFinder(id));
        tickets.add(actTicket);
        return actTicket.getId();
    }

    //Busca si el id es correcto.
    private int newTicketIdFinder(int idT){
        int idChosen = -1;

        // Si se encuentra un ticket con el mismo
        for (int i = 0; i<this.tickets.size();i++) {
            if (this.tickets.get(i).getId() == idT) {

                idChosen=-2;
            }
        }

        if(idChosen==-1){
            idChosen=idT;
        }
        else {
            idChosen=auxFindIdForTicket(idT+1);
        }

        return idChosen;
    }

    // Metodo auxiliar para buscar el siguiente id libre.
    private int auxFindIdForTicket(int TId){
        int result=TId , busqueda=0;
        while (busqueda < tickets.size()) {
            if (tickets.get(busqueda).getId() == TId){
                result = auxFindIdForTicket(result + 1);
            }
            busqueda++;
        }
        return result;
    }

    // Añade un producto al ticket
    public void addTicket(int TId, Product newproduct ,int cantidad){
        Ticket actTicket = tickets.get(TId);
        try {
            actTicket.addProduct(newproduct, cantidad);
            if (actTicket.getTicketState().equals(State.EMPTY)){
                actTicket.updateState(State.OPEN);
            }
        }
        catch (Exception noProductWithprodId){
            System.out.println("No product with id "+TId+" was found");
        }
    }

    // Busca el ticket en el array. Si no lo encuentra devuelve null.
    public Ticket getTicket(int TId){
        Ticket actTicket = null;
        int busqueda=0;
        while(busqueda<tickets.size()){
            if (tickets.get(busqueda).getId() == TId){

                actTicket = tickets.get(busqueda);
            }
            busqueda++;
        }
        return actTicket;
    }

    // Elimina el ticket. Si no encuentra un ricket con TId da false.
    public boolean removeTicket(int TId){
        boolean result = false;
        int i = 0;
        while (i < this.tickets.size() && !result) {
            if (this.tickets.get(i).getId() == TId) {
                tickets.remove(i);
                result = true;
            }
            i++;
        }
        return result;
    }
}
