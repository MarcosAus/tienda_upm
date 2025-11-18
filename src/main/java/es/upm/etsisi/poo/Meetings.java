package es.upm.etsisi.poo;

public class Meetings {



    public Product(int ID, String nombre, double precioPersona, int numAsistentes, String fechaEvento) {
        if (numAsistentes > MAX_NUM_PARTICIPANTES) {
            this.ID = ID;
            this.nombre = nombre;
            this.precio = precioPersona;
            this.fechaEvento = fechaEvento;
        }
    }
}
