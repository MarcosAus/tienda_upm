package es.upm.etsisi.poo.Validation;

import es.upm.etsisi.poo.Products.Vendible;

public class ValidacionAddS implements ValidacionAddTickets {

    @Override
    public boolean add(Vendible vendible) {
        String id = vendible.getId();
        char ultimoCaracter = id.charAt(id.length() - 1);
        return ultimoCaracter == 'S';
    }
}
