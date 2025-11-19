package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductBasic;
import es.upm.etsisi.poo.Products.ProductPers;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler {
    private List<Product> productList = new ArrayList<>();





    // Añade un producto a la lista de productos. No lo añade si el id se repite.
    public boolean addProduct(Product product) {
        boolean añadido = false;
        for (int i = 0; i<this.productList.size(); i++) {
            if (this.productList.get(i).getId() == product.getId()) {
                this.productList.add(product);
                añadido = true;
            }
        }
        return añadido;
    }


    // Elimina un producto SOLO del array no, se va a tickets
    public void removeProduct(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                productList.remove(i);
            }
        }
    }

    //Devuelve el producto en base a un id. Null si no se encuentra.
    public Product getProduct(int id) {
        Product encontrado = null;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                encontrado = productList.get(i);
            }
        }
        return encontrado;
    }

    //Da la lista de produtos.
    public List<Product> getProductList() {
        return productList;
    }

    // Actualiza el producto. El nombre, categoria o precio respectivamente.
    public void updataProductName(int id ,String newName) {
        getProduct(id).setNombre(newName);
    }
    public boolean updataProductCategory(int id, State newState) {
        boolean resultado = false;
        if (productList.get(id).getClass().equals(ProductBasic.class) || productList.get(id).getClass().equals(ProductPers.class){
            productList.get(id);
            resultado = true;
        }
        return resultado;
    }
    public void updataProductPrice(int id, double newPrice) {
        getProduct(id).setPrecio(newPrice);
    }

}
