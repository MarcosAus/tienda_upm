package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.Utilities;

import java.util.HashMap;
import java.util.Stack;

public class Cashier extends User {
    HashMap<Integer,Ticket> tickets; //fixme tickets a cambiado de un Stack a un HasMap

    public Cashier(String id, String nombre, String correo) {
        super(validarId(id), nombre, correo); /*Si el id del cajero no empieza por "UW", el cajero se crea igual.
                                               Esto lo arreglaremos en App (abierto a revisar) /M */
        this.tickets = new HashMap<>();
    }

    //Getter tickets
    public HashMap<Integer,Ticket> getTickets() {
        return tickets;
    }

//
//    //Adds all the tickets from a stack
//    public void addTicketsFromOtherUser(HashMap<String,Ticket> newTickets){
//        int ticketsToAdd = newTickets.size();
//        for (int i = 0; i<ticketsToAdd; i++) {
//            tickets.push(newTickets.pop());
//        }
//    }
//
//    //Adds a section of the Stack of tickets from other user. Start incluido, end no incluido
//    public void addSectionOfTicketsFromOtherUser(Stack<Ticket> newTickets, int start, int end){
//        int ticketsToAdd = newTickets.size();
//        Ticket actTicket = null;
//        for (int i = start; i<end; i++){
//            actTicket = newTickets.get(i);
//            tickets.push(actTicket);
//        }
//    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getId(),ticket);
    }

    //Elimina el ticket en base a un String
    public void removeTicket(int ticketId) {
        Ticket ticketActual = tickets.get(ticketId);
        try {
            tickets.remove(ticketActual.getId());
        } catch (Exception noProductWithProdId) {
            System.out.println("No product with id " + ticketId + " was found");
        }
    }



//    // Devuelve el Cashier que creo el ticket
//    public boolean isCashierInCharge(int idTicket) {
//        boolean inCharge = false;
//        int busqueda=0;
//        while(busqueda<tickets.size()){
//            if(Integer.parseInt(tickets.get(busqueda).getId())==idTicket){
//                tickets.get(busqueda).printTicket();
//                busqueda =  tickets.size();
//                inCharge = true;
//            }
//            busqueda++;
//        }
//        return inCharge;
//    }

    public void printTicket(int ticketId) {
        this.tickets.get(ticketId).printTicket();
    }

    public void printAllTickets() {
        for (int i = 0; i<tickets.size(); i++) {
            tickets.get(i).printTicket();
        }
    }


    private static String validarId(String id) {
        if (id == null) {
            int numRandom;
            StringBuilder idAleatorio = new StringBuilder("UW");
            for (int i = 0; i < 7; i++) {
                numRandom = (int) (Math.random() * 9);
                idAleatorio.append(numRandom);
            }
            return idAleatorio.toString();
        } else {
            return id;
        }
    }

    @Override
    public Cashier getThisCash(){
        return this;
    }

    @Override
    public boolean isCash() {
        return true;
    }
}
