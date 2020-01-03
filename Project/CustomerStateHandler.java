import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

class CustomerStateHandler extends CustomerState {

    protected ArrayList<CustomerState> csList = new ArrayList<>();

    public CustomerStateHandler(CustomerState cs) {
        super(cs, cs.currentState, cs.serverID);
    }

    public void computeEvent(ArrayList<CustomerState> csList, int noCust) {
        List <Integer> numCust = new ArrayList<>();
        List <CustomerState> stateStorage = new ArrayList<>();
        PriorityQueue<CustomerState> cspq = new PriorityQueue<>();
        for (int i = 0; i < csList.size(); i++) {
            cspq.add(csList.get(i));
        } 
        Iterator<CustomerState> it = cspq.iterator();
        while(it.hasNext()) {
            System.out.println(cspq.poll().toString());
        }
    }
}




