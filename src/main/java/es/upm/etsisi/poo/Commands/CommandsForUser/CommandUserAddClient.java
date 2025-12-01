package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Users.User;

public class CommandUserAddClient extends Command {
    private final UserHandler userHandler;
    public CommandUserAddClient(String name, UserHandler productHandler) {
        super(name);
        this.userHandler = productHandler;
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
                    if (email.length() >=8 && email.endsWith("@upm.es")) {
                        try {
                            Cashier cashier = userHandler.getCashiersRecord().get(args[5]);
                            User client = new Client(id, nombre, email, cashier);
                            userHandler.registerUser(client);
                            System.out.println(Comments.CLIENT_ADD);
                        } catch (Exception e) {
                            System.out.println(Comments.CASH_NOT_FOUND);
                        }
                    }
                    else{
                        System.out.println(Comments.EMAIL_IS_PERSONAL);
                    }
                }
                else {
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
