package es.upm.etsisi.poo.Products;

import java.time.Duration;

public class Service extends Vendible {
    private String dateOfEnd;
    private ServicesTypes servicesType;

    public Service(String dateOfEnd,String id,ServicesTypes servicesType) {
        super(id);
        this.dateOfEnd = dateOfEnd;
        this.servicesType = servicesType;
    }

    public String getDateOfEnd() {
        return dateOfEnd;
    }

    public ServicesTypes getServicesType(){
        return servicesType;
    }

    public int amountTicket(int amount) {
        return 1;
    }
}
