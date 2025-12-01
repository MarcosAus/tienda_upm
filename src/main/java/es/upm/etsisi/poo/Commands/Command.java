package es.upm.etsisi.poo.Commands;

public abstract class Command {
    protected String name;

    public Command(String name) {
        this.name = name;
    }

    public boolean isThisCommand(String name) {
        return name != null && name.equals(this.name);
    }


    public abstract void execute(String[] args);

}
