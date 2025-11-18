package es.upm.etsisi.poo.Products;

public class Meetings extends Product {
    private static final int maxParticipantes = 100;
    private static final  int minTime = 12;

    public Meetings(int id, String name, double price) {
        super(id, name, price);
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
    public double precioTotal() {
        return getPrecio();
    }
}
