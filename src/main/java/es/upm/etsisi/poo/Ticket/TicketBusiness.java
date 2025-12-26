package es.upm.etsisi.poo.Ticket;

import es.upm.etsisi.poo.Comments;
import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Products.ProductPers;
import es.upm.etsisi.poo.State;
import es.upm.etsisi.poo.TicketItem;

import java.util.HashSet;
import java.util.List;

public class TicketBusiness extends Ticket{

    private char ticketType;

    public TicketBusiness(int id){
        super(id);
        ticketType = 'p';
    }

    public TicketBusiness(){
        super();
        ticketType = 'p';
    }

    public void setTicketType(char ticketType){
        this.ticketType = ticketType;
    }


    @Override
    public boolean isBusinessType() {
        return true;
    }

    @Override
    public boolean addProduct(Product product, int cantidad) {
        boolean resultado = false;
        if (this.getTicketState() != State.CLOSED) {
            updateState(State.OPEN);
            if (TicketWillBeFull(cantidad) <= 0) {
                if (product != null) {
                    if (ProductMatchesType(product)) {
                        TicketItem tI = busquedaProductoPorID(getItems(),product.getId());
                        if (tI != null) {
                            if ( product.isPersonalizable()) {
                                List<String> textosA= ((ProductPers)product).getTextos();
                                List<String> textosB= ((ProductPers)tI.getProduct()).getTextos();
                                if(new HashSet<>(textosA).equals(new HashSet<>(textosB))){
                                    tI.addAmount(cantidad);
                                    printTicket();
                                }else{
                                    getItems().add(new TicketItem(product,cantidad));
                                    printTicket();
                                }
                            } else if (product.getMinTime().isZero()) {
                                tI.addAmount(cantidad);
                                printTicket();
                            } else {
                                System.out.println(Comments.DUPLICATE_ACTIVITY_IN_TICKET);
                            }
                        } else {
                            getItems().add(new TicketItem(product, cantidad));
                            resultado = true;
                            printTicket();

                        }
                    }
                    else{
                        System.out.println(Comments.INVALID_PRODUCT_TIPE_FOR_TICKET);
                    }

                }
            } else {
                System.out.println(Comments.CAPACITY_REACHED);
            }
        }
        return resultado;
    }

    private boolean ProductMatchesType(Product product) {
        boolean result =  false;
        switch (ticketType) {
            case 'c':
                result = true;
                break;
            case 'p':
                if (product.isService() == null) {
                    result = true;
                }
                break;
            case 's':
                if (product.isService() != null) {
                    result = true;
                }

        }
        return result;
    }
}
