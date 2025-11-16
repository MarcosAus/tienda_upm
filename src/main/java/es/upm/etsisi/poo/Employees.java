package es.upm.etsisi.poo;

import java.util.ArrayList;
import java.util.Objects;

public class Employees {
    private ArrayList<Cashier> listaEmpleados;

    public Employees() {
        this.listaEmpleados = new ArrayList<>();
    }

    public int getCapacidad(){
        return this.listaEmpleados.size();
    }

    public ArrayList<Cashier> getListaEmpleados() {
        return listaEmpleados;
    }

    public void addCashier(String id, String nombre, String correo){
        char[] idArray = id.toCharArray();
        if (Objects.equals(idArray[0], 'U') && Objects.equals(idArray[1], 'W')){
            Cashier existente = Utilities.buscarCajeroPorID(listaEmpleados,id);
            if(existente == null){
                String[] correoArray = correo.split("@");
                if(correoArray[1].equals("upm.com") || correoArray[1].equals("hotmail.com") || correoArray[1].equals("outlook.com") || correoArray[1].equals("gmail.com")){
                    Cashier cashier = new Cashier(id, nombre, correo);
                    listaEmpleados.add(cashier);
                    System.out.println(cashier + "\ncash add: ok");
                }
            }
            System.out.println("Email not valid");
        }
        System.out.println("ID format not valid");
    }
}
