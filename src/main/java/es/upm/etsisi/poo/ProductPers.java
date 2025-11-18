package es.upm.etsisi.poo;

import java.util.ArrayList;

public class ProductPers extends ProductBasic {
    private ArrayList<String> textos ;
    private final int maxTextos;

    public ProductPers(Categoria categoria,int id, String name,double price,int maxTextos){
        super(categoria,name,id,price);
        this.maxTextos = maxTextos;
        textos = new ArrayList<>();
    }

    public ArrayList<String> getTextos() {
        return textos;
    }
    public void addTexto(String texto) {
        if(texto == null || texto.isBlank()) return;
        textos.add(texto);
    }

    public int getMaxTextos() {
        return maxTextos;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public double precioTotal() {
        int aumento = (textos.size()/10)+1;
        return this.getPrecio() * aumento;
    }
}
