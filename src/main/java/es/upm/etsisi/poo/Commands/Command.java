package es.upm.etsisi.poo.Commands;

public abstract class Command {
    protected String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract boolean isThisCommand(String name);

    public abstract void execute(String[] args);
}
