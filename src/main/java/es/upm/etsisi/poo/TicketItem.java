package es.upm.etsisi.poo;
import es.upm.etsisi.poo.Products.*;

public class TicketItem {
    private Product product;
    private int amount;

    public TicketItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() { return product; }
    public int getAmount() { return amount; }

    public void addAmount(int n) { this.amount += n; }
    public double subtotal() { return product.TotalPrice() * amount; }
}