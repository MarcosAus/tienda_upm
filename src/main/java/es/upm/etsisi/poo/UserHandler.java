package es.upm.etsisi.poo;

import es.upm.etsisi.poo.Users.Cashier;
import es.upm.etsisi.poo.Users.Client;
import es.upm.etsisi.poo.Users.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserHandler {
    private HashMap<String, Client> clientsRecord;
    private HashMap<String, Cashier> cashiersRecord;

    public UserHandler() {
        this.clientsRecord = new HashMap<>();
        this.cashiersRecord = new HashMap<>();
    }

    //fixme En este metodo estoy usando clientsRecord como si fuera un unico hasmap. Tambien en el metodo getUserById
    public int getUserAmonut() {
        return clientsRecord.size(); //fixme Repito. Estoy usando clientsRecords como si fuera un hasmap de usuarios generico.
    }

    public HashMap<String, Client> getClientsRecord() {
        return clientsRecord;
    }

    public HashMap<String, Cashier> getCashiersRecord() {

        return cashiersRecord;
    }

    public void registerUser(User user) {
        if (user instanceof Client) {
            clientsRecord.putIfAbsent(user.getId(), (Client) user);
            System.out.println("Client{identifier = '" + user.getId() + "', name = '" + user.getName() + "', email = '" + user.getMail() + "', cash = ' " +((Client) user).getCashier().getId() + " '}");
        } else if (user instanceof Cashier) {
            cashiersRecord.putIfAbsent(user.getId(), (Cashier) user);
            System.out.println("Cash{identifier = '" + user.getId() + "', name = '" + user.getName() + "', email = '" + user.getMail() + "'}");
        }

    }

    public User getUserById(String id) {
        if (id.contains("UW")) {
            return cashiersRecord.get(id);
        } else {
            return clientsRecord.get(id);
        }
    }
    public void listCashierRecord() {
        Map<String, Cashier> cashierRecordSorted =
                cashiersRecord.entrySet()
                        .stream()
                        .sorted(Comparator.comparing(e -> e.getValue().getName()))
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (a,b) -> a,
                                        LinkedHashMap::new
                                )
                        );
        for (Cashier cash : cashierRecordSorted.values()) {
            System.out.println("Cash{identifier = " + cash.getId() + ", name = " + cash.getName() + ", email = " + cash.getMail() + "}");
        }
    }
    public void listClientRecord() {
        Map<String, Client> clientRecordSorted =
                clientsRecord.entrySet()
                        .stream()
                        .sorted(Comparator.comparing(e -> e.getValue().getName()))
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (a,b) -> a,
                                        LinkedHashMap::new
                                )
                        );
        for (Client client : clientRecordSorted.values()) {
            System.out.println("Client{identifier = " + client.getId() + ", name = " + client.getName() + ", email = " + client.getMail() + ", cash = " + client.getCashier().getId() + "}");
        }
    }
    public void listTicketsCashier(String idCash) {
        Cashier cashier = cashiersRecord.get(idCash);
        Ticket ticketAux;
        System.out.println("Tickets:");
        for (int i = 0; i < cashier.getTickets().size(); i++) {
            ticketAux = cashier.getTickets().get(i);
            System.out.printf("%22s -> %6s",ticketAux.getId(),ticketAux.getTicketState());
        }
    }
//    public void listTickets() {
//        Map<String, Cashier> ticketsSorted =
//                cashiersRecord.entrySet()
//                        .stream()
//                        .sorted(Comparator.comparing(e -> e.getValue().extractNumericId(e.getValue().getId())))
//                        .collect(
//                                Collectors.toMap(
//                                        Map.Entry::getKey,
//                                        Map.Entry::getValue,
//                                        (a,b) -> a,
//                                        LinkedHashMap::new
//                                )
//                        );
//        for (Ticket ticket : ticketsSorted.values()) {
//
//        }
//    }
}