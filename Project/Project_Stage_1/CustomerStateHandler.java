import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
/**
 * This class will define the customer state handler which will trigger the 
 * event to arrange the list of customer state data taken in to their
 * arrangement order.
 *
 * @author  Song Soon Wee
 * @version 1.0
 * @since   2019-10-05
 */
 
class CustomerStateHandler extends CustomerState {

    protected ArrayList<CustomerState> csList = new ArrayList<>();
    /**
     * This initializes a new CustomerStateHandler object by taking in a
     * customer state object.
     *
     * @param  cs takes in a CustomerState object.
     */

    public CustomerStateHandler(CustomerState cs) {
        super(cs, cs.currentState, cs.serverID);
    }
    /**
     * This method helps to trigger the event to sort the CustomerState
     * arraylist and print out the new sorted output.
     *
     * @param csList takes in a CustomerState ArrayList.
     */

    public void sortEvent(ArrayList<CustomerState> csList) {
        PriorityQueue<CustomerState> cspq = new PriorityQueue<>();
        for (int i = 0; i < csList.size(); i++) {
            cspq.add(csList.get(i));
        } 
        Iterator<CustomerState> it = cspq.iterator();
        while (it.hasNext()) {
            System.out.println(cspq.poll().toString());
        }
    }
}
