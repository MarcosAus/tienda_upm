package es.upm.etsisi.poo.Products;

public class ProductBasic extends Product {
        private Category categoria;

        public ProductBasic(Category categoria, String name, int id, double price){
                super(id,name,price);
                this.categoria = categoria;
        }
        @Override
        public String toString(){
                return "{class:Product, id:" + getId() +
                        ", name:" + getNombre() +
                        ", category:" + categoria.name() +
                        ", price:" + getPrecio() + "}";
        }
        @Override
        public double TotalPrice(){
                return getPrecio();
        }

        public Category getCategoria() {
                return categoria;
        }

        public void setCategory(Category categoria) {this.categoria = categoria;}
}
