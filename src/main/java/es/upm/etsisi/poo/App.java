package es.upm.etsisi.poo;

import java.util.Scanner;

public class App {
    private Producto[] productList;
    private Ticket[] ticketList;

    public static void main(String[] args) {
        System.out.println("Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.");
        boolean continuar = true;
        do {
            System.out.print("tUPM> ");
            Scanner sc = new Scanner(System.in);
            String entrada = sc.nextLine();
            String[] comando = entrada.split(" ");
            continuar = ejecutarComando(comando, true);
        } while (continuar);

    }

    public static boolean ejecutarComando(String[] comando, boolean continuar) {
        if (comando.length < 2) {
            if (comando[0].equals("help")) {
                mostrarComandos();
            } else if (comando[0].equals("exit")) {
                continuar = false;
            } else {
                System.out.println("Comando invalido");
            }
        } else {
                switch (comando[0]) {
                    case "prod":
                        if (comando)
                }
        }
        return continuar;
    }

    public static void mostrarComandos() {
        System.out.println("""
            Commands
                prod add <id> "<name>" <category> <price>
                prod list
                prod update <id> NAME|CATEGORY|PRICE <value>
                prod remove <id>
                ticket new
                ticket add <prodId> <quantity>
                ticket remove <prodId>
                ticket print
                echo "<texto>"
                help
                exit""");
    }

    public
}
