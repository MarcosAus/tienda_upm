package es.upm.etsisi.poo.Commands.CommandsForProd;

import es.upm.etsisi.poo.Commands.Command;
import es.upm.etsisi.poo.ProductHandler;

public class CommandProdAddMeeting extends Command {
    private ProductHandler productHandler;
    public CommandProdAddMeeting(String name, ProductHandler productHandler) {
        super(name);
        this.productHandler = productHandler;
    }

    @Override
    public boolean isThisCommand(String name) {
        return  this.name.equals(name.toLowerCase().substring(0,name.length()));
    }

    @Override
    public void execute(String[] args) {
        System.out.print("Does nothing and wins");
    }
}
