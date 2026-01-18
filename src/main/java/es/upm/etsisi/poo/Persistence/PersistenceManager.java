package es.upm.etsisi.poo.Persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.ProductHandler;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.UserHandler;
import es.upm.etsisi.poo.TicketHandler;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PersistenceManager {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
            .setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY);

    private static final String PRODUCT_FILE = "products.json";
    private static final String USER_FILE = "users.json";
    private static final String TICKET_FILE = "tickets.json";

    public static void saveProducts(ProductHandler ph) {
        try {
            mapper.writeValue(new File(PRODUCT_FILE), ph);
        } catch (IOException e) {
            System.out.println(Comments.PROD_SAVE_ERROR);
        }
    }

    public static ProductHandler loadProducts() {
        File file = new File(PRODUCT_FILE);
        if (!file.exists()) {
            System.out.println(Comments.NEW_PROD_HANDLER);
            return new ProductHandler();
        }
        try {
            return mapper.readValue(file, ProductHandler.class);
        } catch (IOException e) {
            System.out.println(Comments.NEW_PROD_HANDLER);
            return new ProductHandler();
        }
    }


    public static void saveUsers(UserHandler uh) {
        try {
            mapper.writeValue(new File(USER_FILE), uh);
        } catch (IOException e) {
            System.out.println(Comments.USER_SAVE_ERROR);
        }
    }

    public static UserHandler loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists()) {
            System.out.println(Comments.NEW_USER_HANDLER);
            return new UserHandler();
        }
        try {
            return mapper.readValue(file, UserHandler.class);
        } catch (IOException e) {
            System.out.println(Comments.NEW_USER_HANDLER);
            return new UserHandler();
        }
    }

    public static void saveTickets(TicketHandler th) {
        try {
            mapper.writeValue(new File(TICKET_FILE), th);
        } catch (IOException e) {
            System.out.println(Comments.TICKET_SAVE_ERROR);
        }
    }

    public static TicketHandler loadTickets() {
        File file = new File(TICKET_FILE);
        if (!file.exists()) {
            System.out.println(Comments.NEW_TICKET_HANDLER);
            return new TicketHandler();
        }
        try {
            return mapper.readValue(file, TicketHandler.class);
        } catch (IOException e) {
            System.out.println(Comments.NEW_TICKET_HANDLER);
            return new TicketHandler();
        }
    }
}
