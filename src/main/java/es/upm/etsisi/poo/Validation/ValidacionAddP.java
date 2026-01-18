package es.upm.etsisi.poo.Validation;

import es.upm.etsisi.poo.Products.Vendible;

public class ValidacionAddP implements ValidacionAddTickets {

    @Override
    public boolean add(Vendible vendible) {
        char ultimaLetra = vendible.getId().charAt(vendible.getId().length()-1);
        return Character.isDigit(ultimaLetra);
    }
}
