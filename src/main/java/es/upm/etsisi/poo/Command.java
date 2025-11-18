package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    private List<Command> commands = new ArrayList<>();



    /*protected String[] slicedCommand;

    public Command(String args) {
        this.slicedCommand = sliceCommand(args);
    }

    public String[] sliceCommand(String args) {
        return args.trim().split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

     */
}
