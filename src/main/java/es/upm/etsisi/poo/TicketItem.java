package es.upm.etsisi.poo;
import es.upm.etsisi.poo.Products.*;

public class TicketItem<T extends Vendible> {
    private T product;
    private int amount;

    public TicketItem() {}
    public TicketItem(T product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public T getProduct() { return product; }
    public int getAmount() { return amount; }

    public void addAmount(int n) { this.amount += n; }

}