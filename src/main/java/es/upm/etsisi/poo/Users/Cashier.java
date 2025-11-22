package es.upm.etsisi.poo.Users;

import es.upm.etsisi.poo.Ticket;

import java.util.Stack;

public class Cashier extends User {
    Stack<Ticket> tickets;

    public Cashier(String id, String nombre, String correo) {
        super(validarId(id), nombre, correo); /*Si el id del cajero no empieza por "UW", el cajero se crea igual.
                                               Esto lo arreglaremos en App (abierto a revisar) /M */
        this.tickets = new Stack<>();
    }

    // Devuelve el Cashier que creo el ticket
    public boolean isCashierInCharge(int idTicket) {
        boolean inCharge = false;
        int busqueda=0;
        while(busqueda<tickets.size()){
            if(Integer.parseInt(tickets.get(busqueda).getId())==idTicket){
                tickets.get(busqueda).printTicket();
                busqueda =  tickets.size();
                inCharge = true;
            }
            busqueda++;
        }
        return inCharge;
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


}
