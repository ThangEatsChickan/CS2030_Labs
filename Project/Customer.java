class Customer {
    private final int customerID;
    private final double arrivalTime;

    public Customer(double arrivalTime, int customerNo) {
        this.customerID = customerNo;
        this.arrivalTime = arrivalTime;
    }

    public Customer(Customer customer) {
        this.customerID = customer.customerID;
        this.arrivalTime = customer.arrivalTime;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public double completedServiceTime() {
        return getArrivalTime() + 1;
    }

    @Override
    public String toString() {
        String s1 = new String();
        s1 = String.format(this.customerID + " arrives at " + "%.3f", this.arrivalTime);
        return s1;
    }
}
