package es.upm.etsisi.poo.Products;

public abstract class Vendible {
    private int id;

    public Vendible(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public abstract boolean hasPrice();

}
