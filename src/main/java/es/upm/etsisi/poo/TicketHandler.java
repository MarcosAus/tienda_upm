package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Service;
import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Strategies.BusinessPrintStrategy;
import es.upm.etsisi.poo.Strategies.ClientPrintStrategy;
import es.upm.etsisi.poo.Ticket.TicketBusiness;
import es.upm.etsisi.poo.Ticket.TicketClient;
import es.upm.etsisi.poo.Ticket.TicketParam;
import es.upm.etsisi.poo.Validacion.ValidacionC;
import es.upm.etsisi.poo.Validacion.ValidacionP;
import es.upm.etsisi.poo.Validacion.ValidacionS;
import es.upm.etsisi.poo.Validacion.ValidacionTickets;

import java.util.ArrayList;

public class TicketHandler {
    private ArrayList<TicketParam<? extends Vendible>> tickets;

    public TicketHandler() {
        this.tickets = new ArrayList<>();
    }
    public ArrayList<TicketParam<? extends Vendible>> getTickets() {
        return tickets;
    }

    public int newTicketClient(){
        //Genera un ticket con id aleatorio
        ClientPrintStrategy clientPrintStrategy = new ClientPrintStrategy();
        ValidacionP validacionP = new ValidacionP();
        TicketClient actTicket = new TicketClient(clientPrintStrategy,validacionP);
        tickets.add(actTicket);
        return actTicket.getId();
    }

    public int newTicketClient(int id){
        ClientPrintStrategy clientPrintStrategy = new ClientPrintStrategy();
        ValidacionP validacionP = new ValidacionP();
        TicketClient actTicket = new TicketClient(newTicketIdFinder(id),clientPrintStrategy,validacionP);
        tickets.add(actTicket);
        return actTicket.getId();
    }

    public int newTicketBusiness(char type){
        //Genera un ticket con id aleatorio
        BusinessPrintStrategy businessPrintStrategy = new BusinessPrintStrategy();

        ValidacionTickets<Vendible> validacion;

        if (type == 's') {
            // Casting seguro: ValidacionS implementa ValidacionTickets<Service>
            // que es compatible con ValidacionTickets<? super Vendible> si ajustas la interfaz
            // o lo manejas como ValidacionTickets (raw) para simplificar este factory
            validacion = (ValidacionTickets) new ValidacionS();
        } else {
            validacion = new ValidacionC();
        }

        TicketBusiness actTicket = new TicketBusiness(businessPrintStrategy, validacion);
        tickets.add(actTicket);
        return actTicket.getId();
    }

    public int newTicketBusiness(int id,char type){
        BusinessPrintStrategy businessPrintStrategy = new BusinessPrintStrategy();
        ValidacionTickets<Vendible> validacion;

        if (type == 's') {
            // Casting seguro: ValidacionS implementa ValidacionTickets<Service>
            // que es compatible con ValidacionTickets<? super Vendible> si ajustas la interfaz
            // o lo manejas como ValidacionTickets (raw) para simplificar este factory
            validacion = (ValidacionTickets) new ValidacionS();
        } else {
            validacion = new ValidacionC();
        }

        TicketBusiness actTicket = new TicketBusiness(newTicketIdFinder(id), businessPrintStrategy, validacion);
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
    /**public void addTicket(int TId, Vendible newproduct , int cantidad){
        TicketParam<?> rawTicket = tickets.get(TId);
        try {
            // Esto es para que el IDE no nos marque advertencia al hacer el casting -M
            @SuppressWarnings("unchecked")
            TicketParam<Vendible> actTicket = (TicketParam<Vendible>) rawTicket;
            actTicket.addProduct(newproduct, cantidad);
            if (actTicket.getTicketState().equals(State.EMPTY)) {
                actTicket.updateState(State.OPEN);
            }
        }
        catch (Exception noProductWithprodId){
            System.out.println("No product with id "+TId+" was found");
        }
    }
    **/


    // Busca el ticket en el array. Si no lo encuentra devuelve null.
    public TicketParam<? extends Vendible> getTicket(int TId){
        TicketParam<?> actTicket = null;
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
    /**public boolean removeTicket(int TId){
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
    }**/
}
