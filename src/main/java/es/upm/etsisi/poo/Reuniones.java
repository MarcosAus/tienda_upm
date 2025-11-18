package es.upm.etsisi.poo;

public class Reuniones extends Product {

    private  int maxParticipantes = 100;
    private static int minTime = 12;


    public Reuniones(int ID, String name, double price, int maxParticipantes, String type) {
        super(ID, name, price, type);
        this.maxParticipantes = maxParticipantes;
    }



    public int getMaxParticipantes() {
        return maxParticipantes;
    }
    public int getMinTime() {
        return maxParticipantes;
    }
}
