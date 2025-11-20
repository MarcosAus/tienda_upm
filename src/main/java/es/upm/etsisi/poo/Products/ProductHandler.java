package es.upm.etsisi.poo.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler {
    private List<Product> productList = new ArrayList<>();


    // Añade un producto a la lista de productos. No lo añade si el id se repite.
    public boolean addProduct(Product product) {
        boolean add = true;
        for (int i = 0; i<this.productList.size(); i++) {
            if (this.productList.get(i).getId() == product.getId()) {
                add = false;
            }
        }
        if (add) {
            this.productList.add(product);
        }
        return add;
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
    public void updateProduct(int id, String field, String newFact) {
        switch (field) {
            case "NAME":
                updateProductName(id,newFact);
                break;
            case "CATEGORY":
                Category newCategory = Category.valueOf(newFact);
                updateProductCategory(id,newCategory);
                break;
            case "PRICE":
                double price = Double.parseDouble(newFact);
                updateProductPrice(id,price);
        }

    }

    public void updateProductName(int id ,String newName) {
        getProduct(id).setNombre(newName);
    }

    public void updateProductCategory(int id, Category newCategory) {
        Product p = productList.get(id);
        if (p instanceof ProductBasic productBasic){
            productBasic.setCategory(newCategory);
        }
        if(p instanceof ProductPers productPers){
            productPers.setCategory(newCategory);
        }
    }

    public void updateProductPrice(int id, double newPrice) {
        getProduct(id).setPrecio(newPrice);
    }


    public void listProducts() {
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }
}
