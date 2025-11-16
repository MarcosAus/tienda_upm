package es.upm.etsisi.poo;

public abstract class Command {
    protected String[] slicedCommand;

    public Command(String args) {
        this.slicedCommand = sliceCommand(args);
    }

    public String[] sliceCommand(String args) {
        return args.trim().split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }
}
