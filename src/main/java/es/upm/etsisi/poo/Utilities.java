package es.upm.etsisi.poo;

public class Utilities {
    //Constantes:

    public static final int MAX_LIST = 200;         // Número máximo de productos en la Tienda
    public static final int MAX_IN_TICKET = 100;    //Número máximo de productos en el Ticket
    public static final int MAX_PPOPLE_EVENT = 100; //Maximo de personas en un evento.
    public static final int MIN_TIME_MEETING = 12;  //Tiempo minimo para un meeting
    public static final int MIN_TIME_MEELS = 72;    //Timepo minimo para un meel
    public static final int MAX_PERS_PRODUCT = 10;  //Maximo de personalizaciones del productPers


    public static int numGenerator(int cantidad) {
        int resultado=0;
        int multiplicador=1;
        for (int i = 0; i < cantidad; i++) {
            double generate = Math.random() * (10);
            resultado += (int) generate * multiplicador;
            multiplicador *= 10;
        }
        return resultado;
    }


    public static int idAleatorio(ProductHandler productHandler) {
        int idRandom = -1;
        while(idRandom == -1 || productHandler.getProduct(Integer.toString(idRandom)) != null) {
            double generate ;
            generate = Math.random() * (200);
            idRandom = (int) generate;
        }
        return idRandom;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBusiness(String id){
        if(id.startsWith("B")){
            try {
                int num = Integer.parseInt(id.substring(1));
                return true;
            } catch (NumberFormatException e) {
                System.out.println(Comments.ID_BUSINESS_NOT_VALID);
                return false;
                
            }
        }
        return false;
    }
    public static boolean isDNICorrect(String id){
        char ultimaLetra= id.charAt(id.length()-1);
        if (Character.isLetter(ultimaLetra)){
            try {
                int num = Integer.parseInt(id.substring(0,id.length()-1));
                return true;
            } catch (NumberFormatException e) {
                System.out.println(Comments.ID_CLIENT_NOT_VALID);
                return false;
            }
        }
        return false;
    }

    public static int getMinTimeMeetings(){
        return MIN_TIME_MEETING;
    }
    public static int getMinTimeMels(){
        return MIN_TIME_MEELS;
    }

    public static int getMaxPersProduct(){
        return MAX_PERS_PRODUCT;
    }

    public static int getMaxCustomizationsProducts(){
        return MAX_IN_TICKET;
    }

    public static int getMaxPeopleAllowed(){
        return MAX_PPOPLE_EVENT;
    }
}
