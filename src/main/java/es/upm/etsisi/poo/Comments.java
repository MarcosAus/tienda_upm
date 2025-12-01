package es.upm.etsisi.poo;

public class Comments {
    //Mensajes de salida:


    //GENERAL ERR RESPONSES
    public static final String LENGTH_WRONG = "Command length is wrong";
    public static final String ID_NOT_NUMBER = "ID is not a number";
    public static final String INT_NOT_NUMBER = "The command has invalid number.";
    public static final String ID_NOT_IN_BOUNDARIES = "ID needs to be between 1 and 99999";
    public static final String ID_PRICE_AMOUNT_NOT_NUMBER = "ID, price or amount is not a number";
    public static final String CAPACITY_REACHED = "Capacity has been reached";
    public static final String UNKNOWN_COMMAND = "Unknown command";


    //CASH COMMANDS ERR RESPONSES
    public static final String CASH_NOT_FOUND = "CashId does not match any cashiers";
    public static final String ID_NOT_OF_A_CASHIER = "ID is not a CASHIER";
    public static final String NAME_HAS_WRONG_FORMAT = "Please write the name as \"name\"";
    public static final String EMAIL_IS_PERSONAL = "Please enter your email address from de UPM (x.@upm.es)";


    //CLIENT COMMANDS ERR RESPONSES
    public static final String CLIENT_ID_NOT_EXISTS = "ClientId does not match any clients";
    public static final String USER_NOT_FOUND = "User not found";


    //TICKET COMMANDS ERR RESPONSES
    public static final String TICKET_IS_CLOSED = "Can not modify the ticket when is closed";
    public static final String TICKET_ID_NOT_FOUND = "Ticket ID does not exist.";
    public static final String MORE_THAN_TICKET_CAPACITY = "You cannot add more product than the ticket capacity allows";
    public static final String TICKET_FULL = "Ticket is full, cannot add any more products";
    public static final String TICKET_REMOVED_SUCCESSFULLY = "Ticket removed successfully";
    public static final String DATE_NOT_VALID = "Date is not valid";


    //PROD COMMANDS ERR RESPONSES
    public static final String CATEGORY_WRONG = "Category is wrong";
    public static final String ID_PRICE_NOT_NUMBER = "ID or price is not a number";
    public static final String PRODUCT_LIST_FULL = "List of Products is full, cannot add any more products";
    public static final String PRODUCT_NOT_FOUND = "Given product id does not mach.";
    public static final String NO_PRODUCTS_WITH_THAT_ID_IN_TICKET = "No products with that ID were found in ticket";


    //CASHIER COMMANDS OK RESPONSES
    public static final String CASHIER_REMOVED = "Cash removed: ok";
    public static final String CASHIER_ADD = "Cash add: ok";
    public static final String CASH_LIST = "Cash list: ok";
    public static final String CASH_TICKETS = "Cash tickets: ok";


    //CLIENT COMMANDS OK RESPONSES
    public static final String CLIENT_REMOVED = "Client removed: ok";
    public static final String CLIENT_ADD= "Client add: ok";
    public static final String CLIENT_LIST = "Client list: ok";


    //TICKET COMMANDS OK RESPONSES
    public static final String TICKET_NEW = "ticket new: ok";
    public static final String TICKET_PRINT = "ticket print: ok";
    public static final String TICKET_REMOVE = "ticket remove: ok";
    public static final String TICKET_ADD = "ticket add: ok" ;
    public static final String TICKET_LIST = "ticket list: ok";


    //PROD COMMANDS OK RESPONSES
    public static final String PROD_ADD = "prod add: ok";
    public static final String PROD_REMOVE = "prod remove: ok";
    public static final String PROD_LIST = "prod list: ok";
    public static final String PROD_UPDATE = "prod update: ok";
    public static final String PROD_ADDFOOD = "prod addfood: ok";
    public static final String PROD_ADDMEETINGS = "prod addmeetings: ok";



}
