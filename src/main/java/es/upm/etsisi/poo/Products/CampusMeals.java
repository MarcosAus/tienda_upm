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
        StringBuilder sb = new StringBuilder();
        sb.append("{class:CampusMeals, id:");
        sb.append(getId());
        sb.append(", name:");
        sb.append(getNombre());
        sb.append(", price:");
        sb.append(getPrecio());
        sb.append(", date of Event:");
        sb.append(dateOfEnd);
        sb.append(", max people allowed:");
        sb.append(maxParticipantes);

        return sb.toString();
    }

    @Override
    public double TotalPrice() {
        return getPrecio();
    }
}
