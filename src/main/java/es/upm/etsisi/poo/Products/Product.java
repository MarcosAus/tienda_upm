package es.upm.etsisi.poo.Products;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Product {

    private int id;
    private String nombre;
    private double precio;

    public Product(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public  double getPrecio(){
        return this.precio;
    }

    public  void setPrice(double precio){
        this.precio = precio;
    }
    public String getName() {
        return nombre;
    }
    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Product product){
        return product.getId()==this.getId();
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
    public abstract int amountTicket(int amount);
    public abstract Duration getMinTime();
    public LocalDateTime getStartDate() {
        return null;
    }
    public abstract Product copyProduct(); //fixme Le pregunte a chatgpt y me dijo que clonable es raro. Que es mejor esto. En esencia es lo mismo.


    //Los siguientes metodos devuelven se devuelven a si mismo si son del tipo correcto. No devuelven una copia.
    public ProductPers isProductPers(){
        return null;
    }
    public ProductBasic isProductBasic(){
        return null;
    }
    public Service isService(){
        return null;
    }
    public Event isEvent(){
        return null;
    }
}