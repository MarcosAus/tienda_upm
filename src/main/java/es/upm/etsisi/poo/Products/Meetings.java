package es.upm.etsisi.poo.Products;

import java.time.Duration;
import java.time.LocalDateTime;

public class Meetings extends Product {
    private  int maxParticipantes;
    private static int MAXPEOPLEALLOWED = 100;
    private static final int minTime = 12;
    private String dateOfEnd;

    public Meetings(int id, String name, double price, String dateOfEnd, int maxParticipantes) {
        super(id, name, price);
        this.dateOfEnd = dateOfEnd;
        this.maxParticipantes = maxParticipantes;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }
    public Duration getMinTime() {
        return Duration.ofHours(minTime);
    }
    public String getDateOfEnd() {
        return dateOfEnd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{class:Meeting, id:");
        sb.append(getId());
        sb.append(", name:");
        sb.append(getName());
        sb.append(", price:");
        sb.append(getPrecio());
        sb.append(", date of Event:");
        sb.append(dateOfEnd);
        sb.append(", max people allowed:");
        sb.append(maxParticipantes);
        sb.append("}");

        return sb.toString();
    }

    public String toString(int num,int amount){
        StringBuilder sb = new StringBuilder();
        sb.append("{class:Meeting, id:");
        sb.append(getId());
        sb.append(", name:");
        sb.append(getName());
        sb.append(", price:");
        sb.append(getPrecio()*num);
        sb.append(", date of Event:");
        sb.append(dateOfEnd);
        sb.append(", max people allowed:");
        sb.append(maxParticipantes);
        sb.append(", actual people in event:");
        sb.append(num);
        sb.append("}");

        return  sb.toString();
    }

    @Override
    public double TotalPrice() {
        return getPrecio();
    }
    public double getDiscount() {
        return Category.MEETINGS.getDiscount();
    }

    @Override
    public int amountTicket(int amount) {
        return 1;
    }

    public Category getCategory() {
        return Category.MEETINGS;
    }
    @Override
    public boolean isPersonalizable() {
        return false;
    }

    @Override
    public LocalDateTime getStartDate() {
        return LocalDateTime.parse(dateOfEnd);
    }
}
