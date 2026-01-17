package es.upm.etsisi.poo.Products;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Product extends Vendible {

    private String nombre;
    private double precio;

    public Product() {}
    public Product(String id, String nombre, double precio) {
        super(id);
        this.nombre = nombre;
        this.precio = precio;
    }

    public double getPrice(){
        return this.precio;
    }
    public void setPrice(double precio){
        this.precio = precio;
    }
    public String getName() {
        return nombre;
    }
    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public boolean equals(Product product){
        return product.getId().equals(this.getId());
    }

    public boolean isPersonalizable(){
        return false;
    }

    public  Category getCategory(){
        return null;
    }

    @Override
    public abstract String toString();
    public abstract String toString(int num , int amount);
    public abstract double TotalPrice();
    public abstract double getDiscount();
    public abstract Duration getMinTime();

    public LocalDateTime getStartDate() {
        return null;
    }
    public abstract Product copyProduct();

}