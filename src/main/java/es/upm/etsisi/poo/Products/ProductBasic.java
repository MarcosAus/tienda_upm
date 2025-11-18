package es.upm.etsisi.poo.Products;

public class ProductBasic extends Product {
        private final Categoria categoria;

        public ProductBasic(Categoria categoria,String name,int id, double price){
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
        public double precioTotal(){
                return getPrecio();
        }

        public Categoria getCategoria() {
                return categoria;
        }
}
