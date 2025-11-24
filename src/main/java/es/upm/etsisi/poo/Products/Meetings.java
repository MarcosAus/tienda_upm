package es.upm.etsisi.poo.Products;

public class Meetings extends Product {
    private  int maxParticipantes;
    private static int MAXPEOPLEALLOWED = 100;
    private static final int minTime = 12;
    private String dateOfEnd;

    public Meetings(int id, String name, double price,int maxParticipantes,String dateOfEnd) {
        super(id, name, price);
        this.maxParticipantes = maxParticipantes;
        this.dateOfEnd = dateOfEnd;
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
        sb.append("class:Meeting, id:");
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
