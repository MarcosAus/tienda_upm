package es.upm.etsisi.poo.Products;

public class Meetings extends Product {
    private  int maxParticipantes;
    private static final int minTime = 12;
    private String fechaCaducidad;

    public Meetings(int id, String name, double price,int maxParticipantes,String fechaCaducidad) {
        super(id, name, price);
        this.maxParticipantes = maxParticipantes;
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }
    public int getMinTime() {
        return minTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{class:Meeting, id:");
        sb.append(getId());
        sb.append(", name:");
        sb.append(getNombre());
        sb.append(", price:");
        sb.append(getPrecio());
    }

    @Override
    public double TotalPrice() {
        return getPrecio();
    }
}
