package es.upm.etsisi.poo;

public class Cajero extends Usuario {

    public Cajero(String id, String nombre, String correo) {
        super(validarId(id), nombre, correo);
    }


    private static String validarId(String id) {
        if (id == null) {
            int numRandom;
            StringBuilder idAleatorio = new StringBuilder("UW");
            for (int i = 0; i < 7; i++) {
                numRandom = (int) (Math.random() * 9);
                idAleatorio.append(numRandom);
            }
            return idAleatorio.toString();
        } else {
            return id;
        }
    }
}
