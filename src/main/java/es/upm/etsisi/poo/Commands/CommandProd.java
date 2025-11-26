package es.upm.etsisi.poo.Commands;

import java.util.ArrayList;

import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Products.Inventory;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Utilities;
public class CommandProd {
    private ArrayList<Command> commands;

    public CommandProd(ArrayList<Command> commands) {
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
            System.out.println(Utilities.UNKNOWN_COMMAND);
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
    }
}
