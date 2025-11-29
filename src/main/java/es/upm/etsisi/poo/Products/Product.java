package es.upm.etsisi.poo.Products;

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
    public String getNombre() {
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

    @Override
    public abstract String toString();

    public ProductBasic getProductBasic(){ return null;}
    public Product getProductPers(){ return null;}
    public Meetings getMeetings(){ return null;}
    public CampusMeals getCampusMeals(){ return null;}

    public abstract double TotalPrice();
}