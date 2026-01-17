package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.*;
import es.upm.etsisi.poo.Utilities;

public class CommandUserAddClient implements Command {
    private final UserHandler userHandler;
    String name;
    public CommandUserAddClient(String name, UserHandler productHandler) {
        this.name = name;
        this.userHandler = productHandler;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 6) {
                String nombre = args[2];
                String id = args[3];
                String email = args[4];
                if (nombre.length() >=3 && nombre.startsWith("\"") && nombre.endsWith("\"")) { //Se verifica si el mail y el formato del nombre es correcto.
                    nombre = nombre.substring(1, nombre.length()-1);
                    try {
                        Cashier cashier = userHandler.getCashiersRecord().get(args[5]);
                        Client client;
                        if (Utilities.isBusiness(id)) {
                            client = new ClientBusiness(id, nombre, email, cashier);
                        } else {
                            client = new ClientUser(id, nombre, email, cashier);
                        }
                        userHandler.registerUser(client);
                        System.out.println(Comments.CLIENT_ADD);
                    } catch (Exception e) {
                        System.out.println(Comments.CASH_NOT_FOUND);
                    }
                } else {
                    System.out.println(Comments.NAME_HAS_WRONG_FORMAT);
                }


            } else {
                System.out.println(Comments.LENGTH_WRONG);
            }
        } catch (Exception e) {
            System.out.println(Comments.ID_NOT_NUMBER);
        }
    }
}
