package es.upm.etsisi.poo.Commands;

public interface Command {

    boolean isThisCommand(String name);


    void execute(String[] args);

}
