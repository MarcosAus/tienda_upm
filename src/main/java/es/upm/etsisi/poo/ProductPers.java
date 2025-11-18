package es.upm.etsisi.poo;

import java.util.ArrayList;

public class ProductPers extends Product{

    private ArrayList<String> texts;

    public ProductPers(int ID, String name, double price, String type) {
        super(ID, name, price, type);
        this.texts = new ArrayList<>();
    }

    public void addPersText(String text){
        this.texts.add(text);
    }

    // Ambos metodos se pueden usar para lo mismo. Borrar el que no uséis
    public ArrayList<String> getTexts() {
        return texts;
    }
    public void showTexts(){
        texts.forEach(System.out::println);
    }



    //No es necesario en la pratica pero se podría crear un metodo que borre las personalizaciones.
}
