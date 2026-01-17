package es.upm.etsisi.poo.Persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;

import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.TicketHandler;

import java.io.File;
import java.io.IOException;

public class PersistenceManager {
    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT) // JSON legible
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final String PRODUCT_FILE = "products.json";
    private static final String USER_FILE = "users.json";
    private static final String TICKET_FILE = "tickets.json";

    public static void saveProducts(ProductHandler ph) {
        try {
            mapper.writeValue(new File(PRODUCT_FILE), ph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ProductHandler loadProducts() {
        try {
            File file = new File(PRODUCT_FILE);
            if (!file.exists()) return new ProductHandler(); // archivo no existe → nuevo handler
            return mapper.readValue(file, ProductHandler.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ProductHandler();
        }
    }

    public static void saveUsers(UserHandler uh) {
        try {
            mapper.writeValue(new File(USER_FILE), uh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserHandler loadUsers() {
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) return new UserHandler();
            return mapper.readValue(file, UserHandler.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new UserHandler();
        }
    }

    public static void saveTickets(TicketHandler th) {
        try {
            mapper.writeValue(new File(TICKET_FILE), th);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TicketHandler loadTickets() {
        try {
            File file = new File(TICKET_FILE);
            if (!file.exists()) return new TicketHandler();
            return mapper.readValue(file, TicketHandler.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new TicketHandler();
        }
    }
}
