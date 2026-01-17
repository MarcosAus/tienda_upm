package es.upm.etsisi.poo.Products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import es.upm.etsisi.poo.Utilities;

public class ProductPers extends ProductBasic {
    private ArrayList<String> textos ;
    private final int maxTextos;

    public ProductPers(Category categoria, int id, String name, double price, int maxTextos){
        super(categoria,name,id,price);
        if (maxTextos <= Utilities.getMaxPersProduct()) this.maxTextos = maxTextos;
        else throw new IllegalArgumentException();
        textos = new ArrayList<>();
    }

    public List<String> getTextos() {
        return Collections.unmodifiableList(textos);
    }

    public boolean addTexto(String texto) {
        if(texto != null &&  !texto.isEmpty()){
            if(textos.size() < maxTextos){
                textos.add(texto);
                return true;
            }
        }
        return false;
    }

    public int getMaxTextos() {
        return maxTextos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{class:ProductPersonalized, id:");
        sb.append(getId());
        sb.append(", name:");
        sb.append(getName());
        sb.append(", category:");
        sb.append(getCategory());
        sb.append(", price:");
        sb.append(TotalPrice());
        sb.append(", maxPersonal:");
        sb.append(maxTextos);
        sb.append("}");
        return sb.toString();
    }
    public String toString(int num , int amount ) {
        StringBuilder sb = new StringBuilder();
        sb.append("{class:ProductPersonalized, id:");
        sb.append(getId());
        sb.append(", name:");
        sb.append(getName());
        sb.append(", category:");
        sb.append(getCategory());
        sb.append(", price:");
        sb.append(TotalPrice());
        sb.append(", maxPersonal:");
        sb.append(maxTextos);
        if (!textos.isEmpty()) {
            sb.append(", personalizationList:[");
            for (int i = 0; i < textos.size(); i++) {
                sb.append(textos.get(i));
                if (!(i == textos.size() - 1)) {
                    sb.append(", ");
                }
            }
            sb.append("]");
        }
        sb.append("}");
        if (amount >= 2) {
            sb.append(" **discount -").append(this.TotalPrice() * getDiscount());
        }
        sb.append("\n");
        return sb.toString().repeat(num);
    }
    @Override
    public double TotalPrice() {
        double aumento = ((double) textos.size() /10)+1;
        return this.getPrecio() * aumento;
    }
    public boolean isFull(){
        return textos.size() == maxTextos;
    }

    @Override
    public boolean isPersonalizable() {
        return true;
    }

    @Override
    public Product  copyProduct() {
        return new ProductPers(getCategory(),getId(),getName(),getPrecio(),getMaxTextos());
    }


    @Override
    public boolean equals(Product product) {
        ProductPers p = (ProductPers) product;
        List<String> textosA= p.getTextos();
        List<String> textosB= this.getTextos();
        return new HashSet<>(textosA).equals(new HashSet<>(textosB));
    }
}
