package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Ticket;
import es.upm.etsisi.poo.TicketHandler;
import es.upm.etsisi.poo.Utilities;

public class CommandTicketNew extends Command {
    private TicketHandler ticketHandler;

    public CommandTicketNew(String name, TicketHandler ticketHandler) {
        super(name);
        this.ticketHandler = ticketHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
        Ticket ticket;
        int id;
        if (args.length == 5) {
            try {
                id = Integer.parseInt(args[2]);
                if (id <= 1 || id >= 99999 ){
                    ticket = new Ticket(String.format("%05d", id));

                    System.out.println(Utilities.TICKET_NEW_OK);
                }
                else{
                    System.out.println(Utilities.ID_NOT_IN_BOUNDARIES);
                }

            }
            catch(Exception e){
                System.out.println(Utilities.ID_NOT_NUMBER);
            }




        } else System.out.println(Utilities.LENGTH_WRONG);
    }
}
