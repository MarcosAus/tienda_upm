package es.upm.etsisi.poo.Validation;

import es.upm.etsisi.poo.Products.Vendible;

public class ValidacionAddC implements ValidacionAddTickets {
    @Override
    public boolean add(Vendible vendible) {
        return true;
    }
}
