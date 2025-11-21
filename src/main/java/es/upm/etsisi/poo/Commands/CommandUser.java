package es.upm.etsisi.poo.Commands;

import java.util.ArrayList;

public class CommandUser {
    private ArrayList<Command> commands;

    public CommandUser(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public void checkCommand(String[] commanddiv, String actCommand) {
        int counter = 0;
        boolean flag = false;
        while (counter < commands.size() && !flag) {
            flag = commands.get(counter).isThisCommand(actCommand);
            counter++;
        }
        if (flag) {
            commands.get(counter).execute(commanddiv);
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
    }
}
