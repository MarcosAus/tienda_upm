package es.upm.etsisi.poo.Commands.CommandsForUser;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.User;

public class CommandUserAddCashier extends Command {
    private final UserHandler userHandler;
    public CommandUserAddCashier(String name, UserHandler productHandler) {
        super(name);
        this.userHandler = productHandler;
    }


    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 5) {
                String id = args[2];
                if (args[2].length()==9 && Integer.parseInt(args[2].substring(3))>0){
                    String nombre = args[3];
                    String email = args[4];
                    //Se verifica si el mail y el formato del nombre es correcto.
                    if (nombre.length() >=3 && nombre.startsWith("\"") && nombre.endsWith("\"")) {
                        nombre = nombre.substring(1, nombre.length()-1);
                        if (email.length() >=8 && email.endsWith("@upm.es")) {
                            User cashier = new Cashier(id, nombre, email);
                            userHandler.registerUser(cashier);
                            System.out.println(Comments.CASHIER_ADD);
                        } else{
                            System.out.println(Comments.EMAIL_IS_PERSONAL);
                        }
                    } else {
                        System.out.println(Comments.NAME_HAS_WRONG_FORMAT);
                    }
                } else{
                    System.out.println(Comments.INVALID_CASH_ID);
                }
            } else if (args.length == 4) {
                String nombre = args[2];
                String email = args[3];
                //Se verifica si el mail y el formato del nombre es correcto.
                if (nombre.length() >=3 && nombre.startsWith("\"") && nombre.endsWith("\"")) {
                    User cashier = new Cashier(null, nombre, email);
                    userHandler.registerUser(cashier);
                    System.out.println(Comments.CASHIER_ADD);
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
