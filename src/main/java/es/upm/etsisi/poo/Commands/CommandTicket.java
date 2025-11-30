package es.upm.etsisi.poo.Commands;

import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.Utilities;

import java.util.ArrayList;

public class CommandTicket {
    private ArrayList<Command> commands;

    public CommandTicket(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public void checkCommand(String[] commanddiv, String actCommand) {
        int counter = 0;
        boolean flag = false;
        try {
            while (counter < commands.size() && !flag) {
                flag = commands.get(counter).isThisCommand(actCommand);
                if (!flag) {
                    counter++;
                }
            }
            if (flag) {
                commands.get(counter).execute(commanddiv);
            }
        }catch (Exception e){
            System.out.println(Comments.UNKNOWN_COMMAND);
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
    }


    /*public void ticketNew() {
        if (slicedCommand.length == 2) {
            ticket = new Ticket();
            System.out.println(Utilities.TICKET_NEW_OK);
        } else System.out.println(Utilities.LENGTH_WRONG);
    }
    public void ticketAdd(){
        int id;
        try {
            if (slicedCommand.length == 4 && productList.getCapacidad() <= Utilities.MAX_IN_TICKET) {
                id = Integer.parseInt(slicedCommand[2]);
                int cantidad = Integer.parseInt(slicedCommand[3]);
                if (cantidad<=Utilities.MAX_IN_TICKET-ticket.getNumeroProductos()){
                    ticket.addProduct(Utilities.busquedaProductoPorID(productList.getLista(), id), cantidad);
                    ticket.printTicket();
                }
                else System.out.println(Utilities.MORE_THAN_TICKET_CAPACITY);

            }
            else {
                if (slicedCommand.length != 4) {
                    System.out.println(Utilities.LENGTH_WRONG);
                }
                if (ticket.getNumeroProductos() == Utilities.MAX_IN_TICKET) {
                    System.out.println(Utilities.TICKET_FULL);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(Utilities.ID_AMMOUNT_NOT_NUMBER);
        }
    }
    public void ticketRemove() {
        int id;
        if (slicedCommand.length == 3) {
            try {
                id  = Integer.parseInt(slicedCommand[2]);
                boolean eliminado = ticket.removeProduct(id);
                if (eliminado) System.out.println(Utilities.TICKET_REMOVE_OK);
                else System.out.println(Utilities.NO_PRODUCTS_WITH_THAT_ID_IN_TICKET);
            } catch (NumberFormatException e) {
                System.out.println(Utilities.ID_NOT_NUMBER);
            }
        } else System.out.println(Utilities.LENGTH_WRONG);
    }
    public void ticketPrint() {
        if (slicedCommand.length == 2) {
            ticket.printTicket();
            System.out.println(Utilities.TICKET_PRINT_OK);
        } else System.out.println(Utilities.LENGTH_WRONG);
    }*/
}
