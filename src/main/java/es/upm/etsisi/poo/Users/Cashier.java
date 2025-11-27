package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.Utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Cashier extends User {
    HashMap<String, Ticket> tickets;

    public Cashier(String id, String nombre, String correo) {
        super(validarId(id), nombre, correo); /*Si el id del cajero no empieza por "UW", el cajero se crea igual.
                                               Esto lo arreglaremos en App (abierto a revisar) /M */
        this.tickets = new HashMap<>();
    }

    //Getter tickets
    public HashMap<String, Ticket> getTickets() {
        return tickets;
    }


    //Adds all the tickets from a stack
    public void addTicketsFromOtherUser(HashMap<String, Ticket> newTickets) {
        tickets.putAll(newTickets);
    }

    //Adds a section of the Stack of tickets from other user. Start incluido, end no incluido
    public void addSectionOfTicketsFromOtherUser(HashMap<String, Ticket> newTickets, int start, int end) {
        int ticketsToAdd = newTickets.size();
        Ticket actTicket = null;
        for (int i = start; i < end; i++) {
            actTicket = newTickets.get(i);
            tickets.push(actTicket);
        }
    }

    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    //Elimina el ticket en base a un String
    public void removeTicket(String id) {
        for(Map.Entry<String, Ticket> entrada : tickets.entrySet()){
            if(tickets.get(entrada.getKey()).getId().equals(id)){
                tickets.remove(entrada.getKey());
            }
        }
    }

    //Elimina un ticket en vase a un int
    public void removeTicket(int id) {
        for(Map.Entry<String, Ticket> entrada : tickets.entrySet()){
            if(Integer.parseInt(tickets.get(entrada.getKey()).getId()) == id){
                tickets.remove(entrada.getKey());
            }
        }

    }


    // Devuelve el Cashier que creo el ticket
    public boolean isCashierInCharge(String idTicket) {
        boolean inCharge = false;
        for(Map.Entry<String, Ticket> entrada : tickets.entrySet()){
            if(tickets.get(entrada.getKey()).getId().equals(idTicket)){
                inCharge = true;
            }
        }
        return inCharge;
    }

    public void printAllTickets() {
        for (Map.Entry<String, Ticket> entrada : tickets.entrySet()) {
            tickets.get(entrada.getKey()).printTicket();
        }
    }

    private static String validarId (String id){
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
}
