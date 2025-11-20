package es.upm.etsisi.poo.Products;

public class CampusMeals extends Product {
    private  int maxParticipantes;
    private static final int minTime = 72;
    private String dateOfEnd;

    public CampusMeals(int id, String name, double price, String dateOfEnd, int maxParticipantes) {
        super(id, name, price);
        this.dateOfEnd = dateOfEnd;
        this.maxParticipantes = maxParticipantes;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }
    public int getMinTime() {
        return minTime;
    }

    @Override
    public String toString() {
        return "{class:Product, id:" + getId() +
                ", name:" + getNombre() +
                ", price per person:" + getPrecio() +
                ", Type of product:  "+ this.getClass().getSimpleName() +
                " }";
    }

    @Override
    public double TotalPrice() {
        return getPrecio();
    }
}
