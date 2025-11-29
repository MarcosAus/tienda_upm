package es.upm.etsisi.poo.Products;

public class ProductBasic extends Product {
        private Category categoria;

        public ProductBasic(Category categoria, String name, int id, double price){
                super(id,name,price);
                this.categoria = categoria;
        }
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("{class:Product, id:").append(this.getId());
            sb.append(", name:").append(this.getNombre());
            sb.append(", category:").append(this.getCategory().name());
            sb.append(", price:").append(this.TotalPrice()).append("}");
            return sb.toString();
        }

    @Override
    public Category getCategory() {
        return categoria;
    }

    public String toString(int num,int amount){
            StringBuilder auxSb = new StringBuilder();
            auxSb.append("{class:Product, id:").append(this.getId());
            auxSb.append(", name:").append(this.getNombre());
            auxSb.append(", category:").append(this.getCategory().name());
            auxSb.append(", price:").append(this.TotalPrice()).append("}");
            if(amount>=2){
                auxSb.append(" **discount -").append(this.TotalPrice()*getDiscount());
            }
            auxSb.append("\n");
            return auxSb.toString().repeat(num);
        }
        @Override
        public double TotalPrice(){
                return getPrecio();
        }

        public void setCategory(Category categoria) {this.categoria = categoria;}

    @Override
    public double getDiscount() {
        return getCategory().getDiscount();
    }

    @Override
    public int amountTicket(int amount) {
        return amount;
    }
}
