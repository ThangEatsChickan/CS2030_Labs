/** 
 * This class will define the customer state in which the customer will
 * go into upon meeting a server.
 *
 * @author  Song Soon Wee
 * @version 1.0
 * @since   2019-10-05
 */
class CustomerState extends Customer implements Comparable<CustomerState> {

    protected final State currentState;
    protected final int serverID; 
    /**
     * This initializes a new Customer State object by taking in a customer
     * object, state object and serverID.
     * 
     * @param  customer takes in a customer object.
     * @param  state    takes in the current state for the customer.
     * @param  serverID takes in a serverID to identify the server.
     */

    public CustomerState(Customer customer, State state, int serverID) {
        super(customer);
        this.currentState = state;
        this.serverID = serverID;
    }
    /**
     * This method helps to toggle the change of state for the customer.
     *
     * @param  customer takes in a customer object.
     * @param  state    takes in the next desired state change.
     * @param  sid      takes in the serverID that is involved with serving
     *                  this customer.
     * @return          a new CustomerState object with its updated state
     *                  for the respective customer being served by the server.
     */
 
    public CustomerState toggleState(Customer customer, State state, int sid) {
        return new CustomerState(customer, state, sid);
    }
    /**
     * This method has been overwritten such that it compares whether the
     * customer state object is referring to the same customer.
     *
     * @param  o takes in an object to compare with.
     * @return   true if they are referring to the same customer.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerState cs = (CustomerState) o;
        return this.getCustomerID() == cs.getCustomerID();
    }
    /**
     * This method has been overwritten such that the list of customer state 
     * data can be sorted in a way by arrival time in ascending order and if
     * there is a tie, they will then be sorted according to the customerID
     * in ascending order.
     *
     * @param  cs takes in another customer state object.
     * @return    an int that determines their sorting order.
     */
 
    @Override
    public int compareTo(CustomerState cs) {
        if (super.getEventTime() < cs.getEventTime()) {
            return -1;
        } else if (super.getEventTime() > cs.getEventTime()) {
            return 1;
        } else {
            if (super.getCustomerID() < cs.getCustomerID()) {
                return -1;
            } else if (super.getCustomerID() == cs.getCustomerID()) {
                if (this.currentState.getState() > cs.currentState.getState()) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }

    @Override
    public String toString() {
        String s1 = new String();
        s1 = String.format("%.3f",super.getEventTime())
            + " " + super.getCustomerID();
        if (this.currentState == State.ARRIVES) {
            s1 = s1 + " " + "arrives";
        } else if (this.currentState == State.WAITS) {
            s1 = s1 + " " + "waits to be served by " + this.serverID;
        } else if (this.currentState == State.SERVES) {
            s1 = s1 + " " + "served by " + this.serverID;
        } else if (this.currentState == State.DONE) {
            s1 = s1 + " " + "done serving by " + this.serverID;
        } else {
            s1 = s1 + " " + "leaves";
        }
        return s1;
    }
}
