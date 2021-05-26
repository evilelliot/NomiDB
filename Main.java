import java.util.Arrays;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.models.TicketModel;
import com.controllers.TicketsController;
import com.DB;

class Main{
    public static void main(String[] args) {
        
        String[] ticketModel  = TicketModel.TicketModel;
        String[] data         = {"300", "Venta de cartuchos"};
        String[] data2        = {"3003", "Venta de cartuchos"};
        String[] data3        = {"3020", "Venta de cartuchos"};

        TicketsController tController = new TicketsController();
        tController.create();
        // Aquí se guardarán los datos
        NavigableMap<Integer, String[]> ticketTable = new TreeMap<>();
        add(ticketTable, data);
        add(ticketTable, data2);
        add(ticketTable, data3);
        add(ticketTable, data);
        System.out.println("Tickets");
        read(ticketTable, ticketModel);

        DB database_a = new DB("./database/db.dat", true);
        database_a.newTable("./database/table_tickets.dat");
        NavigableMap<Integer, String[]> personsTable = new TreeMap<>();
        String[] personsModel = {"id", "nombre", "apellido", "edad"};
        String[] monica       = {"Monica Montserrat", "Loo Torres", "20"};
        add(personsTable, monica);
        System.out.println("Personas");
        
        
        
    }
    // DONT TOUCH
    static private void add(NavigableMap table, String[] data){
        int index = 0;
        if(table.size() == 0){
            index = 1;
        }else{
            Entry<Integer, String[]> last_entry = table.lastEntry();
            int last_entry_id       = last_entry.getKey();
            

            index = (Integer) last_entry_id + 1;
        }
        table.put(index, data);
    }
    static private void read(NavigableMap table, String[] model){
        int id   =  Arrays.asList(model).indexOf("id");
        int date =  Arrays.asList(model).indexOf("concepto_venta");
        Set keys = table.keySet();
        for(Iterator i = keys.iterator(); i.hasNext();){
            Integer key = (Integer) i.next();
            String[] data = (String[]) table.get(key);
            
            System.out.println(data[id] +  "Date: " + data[date]);
            
        }
    }
}
