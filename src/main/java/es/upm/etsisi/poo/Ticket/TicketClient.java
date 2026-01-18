package es.upm.etsisi.poo.Ticket;

import es.upm.etsisi.poo.Products.Product;
import es.upm.etsisi.poo.Strategies.ClientPrintStrategy;
import es.upm.etsisi.poo.Strategies.PrintStrategy;
import es.upm.etsisi.poo.Validacion.ValidacionP;
import es.upm.etsisi.poo.Validacion.ValidacionS;
import es.upm.etsisi.poo.Validacion.ValidacionTickets;

import java.lang.reflect.Array;

public class TicketClient extends TicketParam<Product> {

    public TicketClient() { super(); }

    public TicketClient(int id, PrintStrategy<Product> printStrategy, ValidacionP validacion) {
        super(id, printStrategy,validacion);
    }

    public TicketClient(PrintStrategy<Product> printStrategy,ValidacionP validacion) {
        super(printStrategy,validacion);
    }

//    @Override
//    public boolean addProduct(Product product, int cantidad) {
//        boolean resultado = false;
//        if (this.getTicketState() != State.CLOSED) {
//            updateState(State.OPEN);
//            if (TicketWillBeFull(cantidad) <= 0) {
//                if (product != null) {
//                    TicketItem tI = busquedaProductoPorID(getItems(),product.getId());
//                    if (tI != null) {
//                        if ( product.isPersonalizable()) {
//                            List<String> textosA= ((ProductPers)product).getTextos();
//                            List<String> textosB= ((ProductPers)tI.getProduct()).getTextos();
//                            if(new HashSet<>(textosA).equals(new HashSet<>(textosB))){
//                                tI.addAmount(cantidad);
//                                printTicket();
//                            }else{
//                                getItems().add(new TicketItem(product,cantidad));
//                                printTicket();
//                            }
//                        } else if (product.getMinTime().isZero()) {
//                            tI.addAmount(cantidad);
//                            printTicket();
//                        } else {
//                            System.out.println(Comments.DUPLICATE_ACTIVITY_IN_TICKET);
//                        }
//                    } else {
//                        getItems().add(new TicketItem(product, cantidad));
//                        resultado = true;
//                        printTicket();
//
//                    }
//                }
//            } else {
//                System.out.println(Comments.CAPACITY_REACHED);
//            }
//        }
//        return resultado;
//    }

}
