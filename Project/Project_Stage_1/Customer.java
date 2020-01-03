/**
 * This class defines the base for Customer class when a customer arrives
 * and creates them.
 * 
 * @author   Song Soon Wee
 * @version  1.0
 * @since    2019-10-05 
 */ 
public class Customer {
    protected final int customerID;
    protected final double eventTime;
    /** 
     * This initializes a new Customer with a time when an event has occurred
     * such as their arrival time, their service start time and customer number
     * by taking in a time and customer number as an input parameter.
     *
     * @param  eventTime  the time a customer encountered an event.
     *                    
     * @param  customerNo identity number to identify the customer. 
     */
   
    public Customer(double eventTime, int customerNo) {
        this.customerID = customerNo;
        this.eventTime = eventTime;
    }
    /**
     * This is similiar to the previous customer constructor which 
     * instead takes in a customer object to produce out a similar output. 
     *
     * @param  customer takes in a customer object.
     */

    public Customer(Customer customer) {
        this.customerID = customer.getCustomerID();
        this.eventTime = customer.getEventTime();
    }

    public double getEventTime() {
        return this.eventTime;
    }

    public int getCustomerID() {
        return this.customerID;
    }
    /**
     * Get the current customer's estimated completed time. 
     *
     * @return the current completed service time upon this 
     *         instance customer being served. 
     */

    public double completedServiceTime() {
        return getEventTime() + 1;
    }
    
    @Override
    public String toString() {
        String s1 = new String();
        s1 = String.format(this.getCustomerID() + " arrives at " + "%.3f", 
            this.getEventTime());
        return s1;
    }
}
