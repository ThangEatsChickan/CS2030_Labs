/**
 * This class defines the Server class and its behavior to serve customer.
 *
 * @author  Song Soon Wee
 * @version 1.0
 * @since   2019-10-05
 */
public class Server {
    protected final int serverID;
    protected final Customer servingCustomer;
    /**
     * This initializes a new Server object giving it a 
     * default ID value as 0 and setting it as null for
     * the servingCustomer for this instance.
     */
  
    public Server() {
        this.serverID = 0;
        this.servingCustomer = null;
    }
    /** 
     * This also initializes a new Server object except
     * that it additionally takes in a ID of the server as
     * an input parameter.
     *
     * @param serverID identity number of the server.
     */

    public Server(int serverID) {
        this.serverID = serverID;
        this.servingCustomer = null;
    }
    /**
     * This also initializes a new Server object except
     * that it additionally takes in a ID of the server and
     * the customer as an input parameter.
     *
     * @param serverID identity number of the server. 
     * @param newCustomer a customer that is being served by this server.
     */

    public Server(int serverID, Customer newCustomer) {
        this.serverID = serverID;
        this.servingCustomer = newCustomer;
    }

    public int getServerID() {
        return this.serverID;
    }
    
    public Customer getServingCustomer() {
        return this.servingCustomer;
    }
    /**
     * Returns a new Server object if the current server is able to serve the
     * new customer taken in as an input.
     *  
     * <p>If the server is not serving any customer, then the new customer will be
     * served. If he is serving a customer, then the completed service time of
     * the current customer will be checked against the time of arrival with
     * the new customer. If the new customer arrival time is more than the 
     * completed service time of the current customer who is being served, then
     * the server is able to serve the new customer. Otherwise, he cannot serve
     * the new customer.
     * 
     * @param newCustomer details of the new customer
     * @return            a new Server object with the new serving
     *                    customer, otherwise return null.
     */

    public Server serve(Customer newCustomer) {
        double completedTime;
        if (this.servingCustomer == null) {
            return new Server(this.serverID, newCustomer);
        } else {
            if (newCustomer.getEventTime() >= this.servingCustomer.completedServiceTime()) {
                CustomerState cs = new CustomerState(
                    this.servingCustomer, State.DONE, this.serverID);
                return new Server(this.serverID, newCustomer);
            } else {
                return null;
            }
        }
    }
}   
