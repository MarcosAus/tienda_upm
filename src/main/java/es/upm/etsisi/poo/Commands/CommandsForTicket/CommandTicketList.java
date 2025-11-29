package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.CashierHandler;
import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.TicketHandler;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Utilities;

import java.util.ArrayList;
import java.util.List;

public class CommandTicketList extends Command {
    private TicketHandler ticketHandler;
    private UserHandler userHandler;

    public CommandTicketList(String name, TicketHandler ticketHandler, UserHandler userHandler) {
        super(name);
        this.ticketHandler = ticketHandler;
        this.userHandler = userHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }


    //fixme No recorre los cajeros de manera ordenada. Lo suyo sería que se ordenasen en UserHandler, se le pasase una lista, y este metodo solo la recorriese.
    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            ArrayList<Cashier> arrayCashOrdenado = new ArrayList<>();
            for (int i = 0; i < arrayCashOrdenado.size(); i++) {
                if( arrayCashOrdenado.get(i).isCash()){
                    System.out.println(arrayCashOrdenado.get(i).toString());
                }
            }
        } else {
            System.out.println(Utilities.LENGTH_WRONG);
        }
    }
}
