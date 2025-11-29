package es.upm.etsisi.poo.Products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPers extends ProductBasic {
    private ArrayList<String> textos ;
    private final int maxTextos;

    public ProductPers(Category categoria, int id, String name, double price, int maxTextos){
        super(categoria,name,id,price);
        this.maxTextos = maxTextos;
        textos = new ArrayList<>();
    }

    //fixme leer el LEERPORFA
    public ProductPers(Category categoria, int id, String name, double price){
        super(categoria,name,id,price);
        this.maxTextos = 10;
        textos = new ArrayList<>();
    }

    //Devuelve una vista del arraylist de textos de esta manera se evita que se pueda modificar el array original.
    //el metodo unmodifiableList te devuelve una lista no modificable (es decir , no se permite añadir , eliminar,etc)
    //pero que si permite lectura y lo implementa mediante un wrapper en donde devuelve un objeto unmodifiableList
    //por ultimo Collection es una clase de Utilidades que permite hacer operaciones sobre colecciones en nuestro caso
    //sobre un ArrayList.
    //Sacado de chat gpt
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
        sb.append("class:ProductPersonalized, id:");
        sb.append(getId());
        sb.append(", name:");
        sb.append(getNombre());
        sb.append(", category:");
        sb.append(getCategoria());
        sb.append(", price:");
        sb.append(TotalPrice());
        sb.append(", maxPersonal:");
        sb.append(maxTextos);
        if (!textos.isEmpty()) {
            sb.append(", personalizationList:[");
            for(int i =0 ; i<textos.size();i++){
                sb.append(textos.get(i));
                sb.append(",");
            }
            sb.append("]");
        }


        return sb.toString();
    }

    @Override
    public double TotalPrice() {
        int aumento = (textos.size()/10)+1;
        return this.getPrecio() * aumento;
    }

    @Override
    public ProductPers getProductPers(){return this;}
}
