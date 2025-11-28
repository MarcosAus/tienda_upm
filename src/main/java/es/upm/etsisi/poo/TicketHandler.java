package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.Client;

import java.util.ArrayList;

public class TicketHandler {
    private ArrayList<Ticket> tickets;

    public TicketHandler(){
        this.tickets = new ArrayList<>();
    }

    //Crea un nuevo ticket. Si encuentra que existe un ticket con el id marcado buscará el siguien id libre más cercano.
    //Luego lo añade al array. Devulvuelve el id usado.
    public int newTicket(int idT){
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
        Ticket actTicket = new Ticket(idT);
        tickets.add(actTicket);
        return idChosen;
    }

    public void newTicket(){
        //Genera un ticket con id aleatorio
        tickets.add(new Ticket());
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
                actTicket.updateState(State.ACTIVE);
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

    // Imprime el ticket y lo cierra
    public void printTicketsClose(int TId){
        int busqueda=0;
        while(busqueda<tickets.size()){
            if (tickets.get(busqueda).getId() == TId){
                tickets.get(busqueda).printTicket();
                tickets.get(busqueda).updateState(State.CLOSED);
                busqueda = tickets.size();
            }
            busqueda++;
        }
    }

    //Imprime el ticket pero se queda habierto al contrario qe printTicketClose
    public void printTicketsShow(int TId) {
        int busqueda=0;
        while(busqueda<tickets.size()){
            if (tickets.get(busqueda).getId() == TId){
                tickets.get(busqueda).printTicket();
                busqueda =  tickets.size();
            }
            busqueda++;
        }
    }
}
