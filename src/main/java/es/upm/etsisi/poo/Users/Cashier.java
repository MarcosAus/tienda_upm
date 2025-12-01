package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket;

import java.util.HashMap;

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
        for (Ticket ticket : tickets.values()) {
            tickets.get(ticket.getId()).printTicket();
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

    public int extractNumericId(String cashierId) {
        return Integer.parseInt(cashierId.substring(2));
    }
    public void listAllTickets() {
        for (Ticket ticket : tickets.values()) {
            String listTicket = ticket.listTicket();
            System.out.println(listTicket);
        }
    }
}
