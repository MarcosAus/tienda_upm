package es.upm.etsisi.poo.Products;

import java.time.Duration;
import java.time.LocalDate;

public class Service extends Vendible {
    private static int contador = 1;
    private LocalDate maximumDate;
    private ServicesTypes serviceType;

    public Service() {}
    public Service(String maximumDate, ServicesTypes serviceType) {
        super(contador+"S");
        contador = contador + 1;
        this.maximumDate = LocalDate.parse(maximumDate);
        this.serviceType = serviceType;
    }

    public boolean validDate(LocalDate fecha){
        if (maximumDate.isAfter(fecha)){
            return true;
        }
        else return false;
    }
    public String getServiceCategory() {
        return serviceType.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{class:ProductService, id:").append(this.getId().substring(0, this.getId().length() - 1));
        sb.append(", category:").append(this.getServiceCategory());
        sb.append(", expiration").append(maximumDate.toString());
        return sb.toString();
    }

    @Override
    public int amountTicket(int amount) {
        return 1;
    }
    @Override
    public Product copyProduct() {
        return null;
    }
    @Override
    public Duration getMinTime() {
        return null;
    };
}
