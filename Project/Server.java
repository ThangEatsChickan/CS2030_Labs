class Server {
    protected final int serverID;
    protected final Customer servingCustomer;
    
    public Server() {
       this.serverID = 0;
       this.servingCustomer = null;
    }

    public Server(int serverID) {
        this.serverID = serverID;
        this.servingCustomer = null;
    }

    public Server(int serverID, Customer newCustomer) {
        this.serverID = serverID;
        this.servingCustomer = newCustomer;
    }

    public Server serve(Customer newCustomer) {
        double completedTime;
        if (this.servingCustomer == null) {
            return new Server(this.serverID, newCustomer);
        } else {
            if (newCustomer.getArrivalTime() >= this.servingCustomer.completedServiceTime()) {
                CustomerState cs = new CustomerState(this.servingCustomer, State.DONE, this.serverID);
                return new Server(this.serverID, newCustomer);
            } else {
                return null;
            }
        }
    }

    /*public String toString() {
        String s1 = new String();
        if(this.servingCustomer != null) {
            s1 = String.format("Customer served; next service @ %.3f", this.servingCustomer.completedServiceTime());
        } else {
            s1 = "Customer leaves";
        }
        return s1;
    }*/
}    
