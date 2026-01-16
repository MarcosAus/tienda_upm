package es.upm.etsisi.poo.Products;

import java.util.Date;

public class ServiceMarcos extends Vendible {
    private Date maximumDate;
    private ServicesTypes serviceType;

    public ServiceMarcos(String id, Date maximumDate, ServicesTypes serviceType) {
        super(formatId(id));
        this.maximumDate = maximumDate;
        this.serviceType = serviceType;
    }
    private static int formatId(String id) {
        return Integer.parseInt(id.substring(0, id.length() - 1));
    }

    @Override
    public boolean hasPrice() {
        return false;
    }
}
