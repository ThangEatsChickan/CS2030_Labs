import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class CustomerStateHandler extends CustomerState {

    protected ArrayList<CustomerState> csList = new ArrayList<>();

    public CustomerStateHandler(CustomerState cs) {
        super(cs, cs.currentState);
    }

    public void computeEvent(ArrayList<CustomerState> csList, int noCust) {
        List <Integer> numCust = new ArrayList<>();
        List <CustomerState> stateStorage = new ArrayList<>();
        String s1 = new String();
        s1 = "# Adding arrivals";
        System.out.println(s1);
        for (int i = 0; i < csList.size() ; i++) { //Get initial arrival state
            if (csList.get(i).currentState == State.ARRIVES) {
                stateStorage.add(csList.get(i));
                System.out.println(csList.get(i));
            }
        }
        for (int j = 0; j < csList.size(); j++) { // Get individual event.
            s1 = "# Get next event: ";
            System.out.println(s1 + csList.get(j));
            for (int k = 0; k < stateStorage.size(); k++) {
                if (stateStorage.get(k).customerID == csList.get(j).customerID) {
                    if (stateStorage.get(k).currentState == State.SERVES) {
                        stateStorage.set(k + 1, stateStorage.get(k + 2)); // Push 2nd later event into 2nd priority
                        stateStorage.remove(k + 2);
                    } else if (stateStorage.get(k).currentState == State.LEAVES) {
                        if (stateStorage.size() > 2) {
                            stateStorage.set(k + 1, stateStorage.get(k + 2));
                            stateStorage.remove(k + 2);
                        }
                        stateStorage.remove(k);
                    }
                    if (k != stateStorage.size() - 1 && j != csList.size() - 1) { // Push later event into priority
                        stateStorage.set(k, csList.get(j + 1));
                    }
                }
                if (stateStorage.get(k) != null) {
                    System.out.println(stateStorage.get(k));
                } else {
                    continue;
                }
            }
        }
        System.out.println(noCust);
    }
}




