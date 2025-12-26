package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            System.out.println("No ticket with id " + ticketId + " was found");
        }
    }
    public boolean ticketExists(int ticketId) {
        return tickets.containsKey(ticketId);
    }


    public void printTicket(int ticketId) {
        this.tickets.get(ticketId).closeTicket();
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

    public int extractNumericId(String cashierId) {
        return Integer.parseInt(cashierId.substring(2));
    }
    public void listAllTickets() {
        List<Map.Entry<Integer, Ticket>> lista = new ArrayList<>(tickets.entrySet());
        lista.sort(Map.Entry.comparingByKey());
        for (Map.Entry<Integer, Ticket> entry : lista) {
            Ticket ticket = entry.getValue();
            System.out.println(ticket.listTicket());
        }
    }
}
