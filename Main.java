import java.util.NavigableMap;
import java.util.Map.Entry;

import com.models.TicketModel;
import com.controllers.TicketsController;
import com.DB;

class Main{
    public static void main(String[] args) {
        DB database_a = new DB("./database/db.dat", true);
        

        String[] ticketModel  = TicketModel.TicketModel;
        String[] data         = {"300", "Venta de cartuchos"};
        String[] data2        = {"3003", "Venta de cartuchos"};
        String[] data3        = {"3020", "Venta de cartuchos"};

        TicketsController tController = new TicketsController();
        tController.create("./database/table_tickets.dat", data3);
        tController.read("./database/table_tickets.dat");
        
    }
    
}
