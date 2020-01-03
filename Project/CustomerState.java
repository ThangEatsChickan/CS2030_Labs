class CustomerState extends Customer implements Comparable <CustomerState> {

    protected final State currentState;
    protected final int customerID;
    protected final double arrivalTime;
    protected final double completedServiceTime;
    protected final int serverID;

    public CustomerState(Customer customer, State state, int serverID) {
        super(customer);
        this.customerID = super.getCustomerID();
        this.arrivalTime = super.getArrivalTime();
        this.completedServiceTime = super.completedServiceTime();
        this.currentState = state;
        this.serverID = serverID;
    }

    public CustomerState toggleState(Customer customer, State state, int id) {
        return new CustomerState(customer, state, id);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerState cs = (CustomerState) o;
        return this.customerID == cs.customerID;
    }

    @Override
    public int compareTo (CustomerState cs) {
        if (this.arrivalTime < cs.arrivalTime) {
            return -1;
        } else if (this.arrivalTime > cs.arrivalTime) {
            return 1;
        } else {
            if (this.customerID < cs.customerID) {
                return -1;
            } else if (this.customerID == cs.customerID) {
                if (this.currentState.getState() > cs.currentState.getState()) {
                    return -1;
                }
                else {
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
        s1 = String.format("%.3f", super.getArrivalTime())
            + " " + this.customerID;
        if (this.currentState == State.ARRIVES) {
            s1 = s1 + " " + "arrives";
        } else if (this.currentState == State.WAITS) {
            s1 = s1 + " " + "waits";
        } else if (this.currentState == State.SERVES) {
            s1 = s1 + " " + "served by " + this.serverID;
        } else if (this.currentState == State.DONE) {
            s1 = s1 + " " + "done";
        } else {
            s1 = s1 + " " + "leaves";
        }
        return s1;
    }
}
