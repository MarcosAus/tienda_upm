package es.upm.etsisi.poo.Products;

import java.time.Duration;

public class Services extends Product{

    public Services(int id, String nombre, double precio) {
        super(id, nombre, precio);
    }

    @Override
    public boolean isPersonalizable() {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String toString(int num, int amount) {
        return "";
    }

    @Override
    public Category getCategory() {
        return null;
    }

    @Override
    public double TotalPrice() {
        return 0;
    }

    @Override
    public double getDiscount() {
        return 0;
    }

    @Override
    public int amountTicket(int amount) {
        return 0;
    }

    @Override
    public Duration getMinTime() {
        return null;
    }

    @Override
    public Product copyProduct() {
        return null;
    }
}
