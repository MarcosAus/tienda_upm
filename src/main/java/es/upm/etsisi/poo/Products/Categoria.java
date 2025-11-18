package es.upm.etsisi.poo.Products;

public enum Categoria {
    MERCH(0.07), STATIONERY(0.05), CLOTHES(0.07), BOOK(0.1), ELECTRONICS(0.3);

    private final double discount;


    Categoria(double discount){
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}