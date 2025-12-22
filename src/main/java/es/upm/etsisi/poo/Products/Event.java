package es.upm.etsisi.poo.Products;

import es.upm.etsisi.poo.Utilities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Product {
    private  int maxParticipantes;
    private String dateOfEnd;
    private final int minTime;


    public Event(int id, String name, double price, String dateOfEnd, int maxParticipantes, int minTime) {
        super(id, name, price);
        this.dateOfEnd = dateOfEnd;
        this.maxParticipantes = maxParticipantes;
        this.minTime = minTime;
    }

    public String getDateOfEnd() {
        return dateOfEnd;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public Duration getMinTime() {
        return Duration.ofHours(minTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{class:Events, id:");
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
        sb.append("{class:Events, id:");
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
        return Category.CAMPUSMEALS.getDiscount();
    }

    @Override
    public int amountTicket(int amount) {
        return 1;
    }

    public Category getCategory() {
        return Category.CAMPUSMEALS;
    }

    @Override
    public boolean isPersonalizable() {
        return false;
    }

    @Override
    public LocalDateTime getStartDate() {
        return  LocalDate.parse(dateOfEnd).atTime(12,0).minusHours(getMinTime().toHours());
    }

    @Override
    public Product copyProduct() {
        return new Event(getId(),getName(),getPrecio(),getDateOfEnd(),getMaxParticipantes(),minTime);
    }

}
