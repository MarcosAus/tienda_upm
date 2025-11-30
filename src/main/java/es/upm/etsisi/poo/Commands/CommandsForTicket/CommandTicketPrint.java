package es.upm.etsisi.poo.Commands.CommandsForTicket;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.TicketHandler;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Utilities;

public class CommandTicketPrint extends Command {
    private TicketHandler ticketHandler;
    private UserHandler userHandler;
    public CommandTicketPrint(String name, TicketHandler ticketHandler, UserHandler userHandler) {
        super(name);
        this.ticketHandler = ticketHandler;
        this.userHandler = userHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 4) {
            try {// Se comprueba que los datos del usuario están bien.
                int idTicket = Integer.parseInt(args[2]);
                Cashier cashier = userHandler.getUserById(args[3]).getThisCash();

                if (cashier != null) { // Si el cashies es un cajero se puede poner el ticket a close ya que este como tal no se elimina.
                    cashier.printTicket(Integer.parseInt(args[2]));
                    ticketHandler.removeTicket(Integer.parseInt(args[2]));
                }
                else {
                    System.out.println(Comments.ID_NOT_OF_A_CASIER);
                }
            } catch (NullPointerException e) {
                System.out.println(Comments.CASHIER_ID_NOT_EXISTS);
            }
        } else {
            System.out.println(Comments.LENGTH_WRONG);
        }
    }
}
