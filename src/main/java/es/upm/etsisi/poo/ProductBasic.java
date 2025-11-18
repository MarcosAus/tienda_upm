package es.upm.etsisi.poo;

public class ProductBasic extends Product {
    public enum Categoria {MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS;

    /**
     * Metodo que devuelve la categoria en formato Enum al pasarle un String como argumento
     * @param categoria String que comprobaremos si es o no una categoria del enum
     * @return Devuelve la Categoria en tipo Enum
     */
        public static Categoria getCategoria(String categoria) {
            switch (categoria.toUpperCase()) {
                case "MERCH":
                     return Categoria.MERCH;
                case "STATIONERY":
                    return Categoria.STATIONERY;
                case "CLOTHES":
                    return Categoria.CLOTHES;
                case "BOOK":
                    return Categoria.BOOK;
                case "ELECTRONICS":
                    return Categoria.ELECTRONICS;
                default:
                    return null;
            }
        }
    }



    public ProductBasic(int ID, String name, double price, String type) {
        super(ID, name, price, type);
    }


}
