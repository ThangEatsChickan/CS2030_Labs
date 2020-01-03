package cs2030.simulator;
/**
 * The Customer class encapsulates information and methods pertaining to a
 * Customer in a simulation. The customer class is simplified to maintaining
 * only three variables -- timeArrived, id and isGreedy.
 *
 * @author Song Soon Wee
 * @version CS2030 AY19/20 DES+
 */

class Customer {
    /** The unique ID of this customer. */
    private final int id;

    /** The time this customer arrives. */
    private double timeArrived;
  
    /** The type of Customer. */
    private final boolean isGreedy;

    /**
     * Create and initalize a new customer.
     * The {@code id} of the customer is set.
     * @param  timeArrived The time this customer arrived in the simulation.
     * @param  id The current customer ID.
     * @param  isGreedy The type of customer generated in the simulation.
     */
    public Customer(double timeArrived, int id, boolean isGreedy) {
        this.timeArrived = timeArrived;
        this.id = id;
        this.isGreedy = isGreedy;
    }

    /**
     * Return the waiting time of this customer.
     * @return The waiting time of this customer.
     */
    public double timeWaited(double t) {
        return t - timeArrived;
    }
  
    /**
     * Return whether is this a greedy customer.
     * @return The type of customer.
     */
    public boolean isGreedyCust() {
        return this.isGreedy;
    }
  
    /**
     * Return a string for greedy customer log.
     * @return The string for greedy customer log.
     */
    public String strGreedy() {
        if (this.isGreedy) {
            return "(greedy)";
        } else {
            return "";
        }
    }

    /**
     * Return a string representation of this customer.
     * @return The id of the customer prefixed with "C".
     */
    public String toString() {
        return Integer.toString(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }   
        if (obj instanceof Customer) {
            Customer c = (Customer) obj;
            return c.id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
