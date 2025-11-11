package es.upm.etsisi.poo;

public class User {
    private String id;
    private String nombre;
    private String correo;

    public User(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    protected String getNombre() {
        return this.nombre;
    }

    protected String getCorreo() {
        return this.correo;
    }

    protected String getId() {
        return this.id;
    }
}
