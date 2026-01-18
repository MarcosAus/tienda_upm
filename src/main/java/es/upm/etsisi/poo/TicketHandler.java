package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.Service;
import es.upm.etsisi.poo.Products.Vendible;
import es.upm.etsisi.poo.Strategies.BusinessPrintStrategyCombined;
import es.upm.etsisi.poo.Strategies.BusinessPrintStrategyService;
import es.upm.etsisi.poo.Strategies.ClientPrintStrategy;
import es.upm.etsisi.poo.Strategies.PrintStrategy;
import es.upm.etsisi.poo.Ticket.TicketBusiness;
import es.upm.etsisi.poo.Ticket.TicketClient;
import es.upm.etsisi.poo.Ticket.TicketParam;
import es.upm.etsisi.poo.Validation.*;

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

        PrintStrategy<Product> clientPrintStrategy = new ClientPrintStrategy();
        ValidacionAddTickets validacionP = new ValidacionAddP();
        ValidacionCloseTickets<Product> validacionCloseP = new ValidacionCloseP();

        TicketClient actTicket = new TicketClient(clientPrintStrategy,validacionP,validacionCloseP);
        tickets.add(actTicket);

        return actTicket.getId();
    }

    public int newTicketClient(int id){

        PrintStrategy<Product> clientPrintStrategy = new ClientPrintStrategy();
        ValidacionAddTickets validacionP = new ValidacionAddP();
        ValidacionCloseTickets<Product> validacionCloseP = new ValidacionCloseP();

        TicketClient actTicket = new TicketClient(newTicketIdFinder(id),clientPrintStrategy,validacionP,validacionCloseP);
        tickets.add(actTicket);
        return actTicket.getId();
    }

    public int newTicketBusinessService(){

        PrintStrategy<Service> businessPrintStrategy = new BusinessPrintStrategyService();
        ValidacionCloseTickets<Service> validacionCloseTickets = new ValidacionCloseS();
        ValidacionAddTickets validacion = new ValidacionAddS();


        TicketBusiness<Service> actTicket = new TicketBusiness<>(businessPrintStrategy, validacion,validacionCloseTickets);
        tickets.add(actTicket);
        return actTicket.getId();
    }
    public int newTicketBusinessService(int id){

        PrintStrategy<Service> businessPrintStrategy = new BusinessPrintStrategyService();
        ValidacionCloseTickets<Service> validacionCloseTickets = new ValidacionCloseS();
        ValidacionAddTickets validacion = new ValidacionAddS();


        TicketBusiness<Service> actTicket = new TicketBusiness<>(newTicketIdFinder(id),businessPrintStrategy, validacion,validacionCloseTickets);
        tickets.add(actTicket);
        return actTicket.getId();
    }

    public int newTicketBusinessCombined(){

        PrintStrategy<Vendible> businessPrintStrategy = new BusinessPrintStrategyCombined();
        ValidacionCloseTickets<Vendible> validacionCloseTickets  = new ValidacionCloseC();
        ValidacionAddTickets validacion = new ValidacionAddC();


        TicketBusiness<Vendible> actTicket = new TicketBusiness<>(businessPrintStrategy, validacion,validacionCloseTickets);
        tickets.add(actTicket);
        return actTicket.getId();
    }

    public int newTicketBusinessCombined(int id){

        PrintStrategy<Vendible> businessPrintStrategy = new BusinessPrintStrategyCombined();
        ValidacionCloseTickets<Vendible> validacionCloseTickets  = new ValidacionCloseC();
        ValidacionAddTickets validacion = new ValidacionAddC();


        TicketBusiness<Vendible> actTicket = new TicketBusiness<>(newTicketIdFinder(id), businessPrintStrategy, validacion,validacionCloseTickets);
        tickets.add(actTicket);
        return actTicket.getId();
    }

    //Busca si el id es correcto.
    private int newTicketIdFinder(int idT){
        int idChosen = -1;

        // Si se encuentra un ticket con el mismo
        for (int i = 0; i<this.tickets.size();i++) {
            if (this.tickets.get(i).getId() == idT) {

                idChosen = -2;
            }
        }

        if (idChosen == -1){
            idChosen = idT;
        }
        else {
            idChosen = auxFindIdForTicket(idT+1);
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
}
