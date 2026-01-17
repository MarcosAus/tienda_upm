package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Products.*;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler {
    private List<Vendible> productList = new ArrayList<>();
    private static final int capacity = 200;

    public ProductHandler() {}

    // Añade un producto a la lista de productos. No lo añade si el id se repite.
    public boolean addProduct(Vendible product) {
        boolean add = true;
        for (int i = 0; i < this.productList.size(); i++) {
            if (this.productList.get(i).getId().equals(product.getId())) {
                add = false;
            }
        }
        if ( add && productList.size() < capacity) {
            this.productList.add(product);
        }
        return add;
    }

    // Elimina un producto SOLO del array no, se va a tickets
    public void removeProduct(String id) {
        boolean remove = false;
        int i = 0;
        while (!remove && i < this.productList.size()) {
            if (productList.get(i).getId().equals(id)) {
                productList.remove(i);
                remove = true;
            }
            i++;
        }
    }


    //Devuelve el producto en base a un id. Null si no se encuentra.
    public Vendible getProduct(String id) {
        Vendible encontrado = null;
        int indice = 0;
        while(indice<productList.size() && encontrado==null) {
            if( productList.get(indice).getId().equals(id)) {
                encontrado = productList.get(indice);
            }
            indice++;
        }
        return encontrado;
    }
    public Service getService(String id) {
        Service found = null;
        int indice = 0;
        while (indice < productList.size() && found == null) {
            if (productList.get(indice).getId().equals(id)) {
                found = (Service) productList.get(indice);
            }
        }
        return found;
    }


    //Da la lista de produtos.
    public List<Vendible> getProductList() {
        return productList;
    }

    //Devuelve la cantidad de productos
    public int getHandlerSize(){ return productList.size();}


    // Actualiza el producto. El nombre, categoria o precio respectivamente.
    public void updateProduct(String id, String field, String newValue) {
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

    public Product updateProductName(String id ,String newName) {
        Product result = (Product) getProduct(id);
        result.setName(newName);
        return result;
    }

    public Product updateProductCategory(String id, Category newCategory) {
        Product p = (Product) getProduct(id);
        if (p.getMinTime().isZero() && p.isPersonalizable()){
            ((ProductPers)p).setCategory(newCategory);
        }
        else if (p.getMinTime().isZero() && !p.isPersonalizable()){
            ((ProductBasic)p).setCategory(newCategory);
        }
        return p;
    }

    public Product updateProductPrice(String id, double newPrice) {
        Product p = (Product) getProduct(id);
        p.setPrice(newPrice);
        return p;
    }


    public void listProducts() {
        System.out.print("Catalog:\n");
        for (Vendible product : productList) {
            System.out.println(product.toString());
        }
    }


}
