package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.Category;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductBasic;
import es.upm.etsisi.poo.Products.ProductPers;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler {
    private List<Product> productList = new ArrayList<>();
    private static final int capacity = 200;


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
        int indice = 0;
        while(indice<productList.size() && encontrado==null) {
            if( productList.get(indice).getId() == id) {
                encontrado = productList.get(indice);
            }
            indice++;
        }
        return encontrado;
    }


    //Da la lista de produtos.
    public List<Product> getProductList() {
        return productList;
    }

    //Devuelve la cantidad de productos
    public int getHandlerSize(){ return productList.size();}


    // Actualiza el producto. El nombre, categoria o precio respectivamente.
    public void updateProduct(int id, String field, String newValue) {
        Product product = null;
        switch (field) {
            case "NAME":
                 product = updateProductName(id,newValue);
                break;
            case "CATEGORY":
                Category newCategory = Category.valueOf(newValue);
                product = updateProductCategory(id,newCategory);
                break;
            case "PRICE":
                double price = Double.parseDouble(newValue);
                product = updateProductPrice(id,price);
        }
        if(product !=null) {
            System.out.println(product);
        }
    }

    public Product updateProductName(int id ,String newName) {
        Product result =  getProduct(id);
        result.setName(newName);
        return result;
    }

    public Product updateProductCategory(int id, Category newCategory) {
        Product p = getProduct(id);
        if (p.getMinTime().isZero() && p.isPersonalizable()){
            ((ProductPers)p).setCategory(newCategory);
        }
        else if (p.getMinTime().isZero() && !p.isPersonalizable()){
            ((ProductBasic)p).setCategory(newCategory);
        }
        return p;
    }

    public Product updateProductPrice(int id, double newPrice) {
        Product p = getProduct(id);
        p.setPrice(newPrice);
        return p;
    }


    public void listProducts() {
        System.out.print("Catalog:\n");
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    // Metodo que devuelve cuantos productos se pueden crear nuevos hasta quedarse sin ids posibles.
    public int capacityLeft() {
        return capacity - productList.size();

    }

    public int getCapacity() {
        return capacity;
    }
}
