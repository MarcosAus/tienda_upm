package es.upm.etsisi.poo.Products;

public enum Category {
    MERCH(0.07), STATIONERY(0.05), CLOTHES(0.07), BOOK(0.1), ELECTRONICS(0.3);

    private final double discount;


    Category(double discount){
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public static String CategoryName(Category category){
        String name = null;
        switch (category){
            case MERCH:
                name = "MERCH";
                break;
            case STATIONERY:
                name = "STATIONERY";
                break;
            case CLOTHES:
                name = "CLOTHES";
                break;
            case BOOK:
                name = "BOOK";
                break;
            case ELECTRONICS:
                name = "ELECTRONICS";
                break;
        }
        return name;
    }
    public static Category getCategory(String categoryName){
        Category category = null;
        switch (categoryName){
            case "MERCH":
                category = Category.MERCH;
                break;
            case "STATIONERY":
                category = Category.STATIONERY;
                break;
            case "CLOTHES":
                category = Category.CLOTHES;
                break;
            case "BOOK":
                category = Category.BOOK;
                break;
            case "ELECTRONICS":
                category = Category.ELECTRONICS;
                break;
        }
        return category;
    }
}