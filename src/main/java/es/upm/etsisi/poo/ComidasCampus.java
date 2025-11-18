package es.upm.etsisi.poo;

public class ComidasCampus extends ProductBasic{

    private int maxParticipantes = 100;
    private int mintime = 72;


    public ComidasCampus(int ID, String name, double price, String type) {
        super(ID, name, price, type);

    }
}
