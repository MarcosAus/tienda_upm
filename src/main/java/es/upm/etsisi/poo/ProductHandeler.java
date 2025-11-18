package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class ProductHandeler {
    private List<Product> productList = new ArrayList<>();

    public boolean addProduct(Product product) {
        boolean añadido = false;
        for (int i = 0; i<this.productList.size(); i++) {
            if (this.productList.get(i).getID() == product.getID()) {
                this.productList.add(product);
                añadido = true;
            }
        }
        return añadido;
    }

    public void removeProduct(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getID() == id) {
                productList.remove(i);
            }
        }
    }

    public Product getProduct(int id) {
        Product encontrado = null;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getID() == id) {
                encontrado = productList.get(i);
            }
        }
        return encontrado;
    }





    public List<Product> getProductList() {
        return productList;
    }



}
