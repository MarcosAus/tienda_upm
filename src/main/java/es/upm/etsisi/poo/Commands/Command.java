package es.upm.etsisi.poo.Commands;

import es.upm.etsisi.poo.Products.Inventory;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    protected String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract boolean isThisCommand(String name);

    public abstract void execute(String[] args);
}
