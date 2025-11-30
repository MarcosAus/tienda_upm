package es.upm.etsisi.poo.Products;

public enum Category {
    MERCH(0.07), STATIONERY(0.05), CLOTHES(0.07), BOOK(0.1), ELECTRONICS(0.3),MEETINGS(0),CAMPUSMEALS(0);

    private final double discount;


    Category(double discount){
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

}