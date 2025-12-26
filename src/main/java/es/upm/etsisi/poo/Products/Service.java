package es.upm.etsisi.poo.Products;

import java.time.Duration;

public class Service extends Product{

    public Service(int id, String nombre, double precio) {
        super(id, nombre, precio);
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

    @Override
    public Service isService(){
        return this;
    }
}
