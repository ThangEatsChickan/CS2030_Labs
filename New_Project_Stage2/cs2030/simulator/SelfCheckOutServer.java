package cs2030.simulator;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * The SelfCheckOutServer class keeps track of who is the customer being served (if any)
 * and who is the customer waiting to be served (if any) from the common queue.
 *
 * @author Song Soon Wee
 * @version CS2030 AY19/20 DES+
 */
class SelfCheckOutServer implements Server {
    /** The unique ID of this Self-Checkout Server. */
    private final int id;

    /** The ustomer currently being served, if any. */
    private Optional<Customer> currentCustomer;

    /** The customer currently waiting, if any. */
    private static Optional<Customer> waitingCustomer;
  
    /** The common waiting queue which all Self-Checkout Server shares. */
    private static Queue<Customer> waitingCustList = new LinkedList<>();
  
    /** The limit of the waiting queue. */
    private final int queueSize;

    /**
     * Creates a Self-CheckOut Server and initalizes it with a unique id and queue size.
     * @param  id ID of this Self-Checkout Server.
     * @param  queueSize The waiting queue size limit for this Self-Checkout server.
     */
    public SelfCheckOutServer(int id, int queueSize) {
        this.currentCustomer = Optional.empty();
        this.id = id;
        this.queueSize = queueSize;
    }

    /**
     * Private constructor for a Self Checkout server.
     * @param  id ID of this Self-Checkout Server.
     * @param  currentCustomer The current customer it is serving, if any.
     * @param  queueSize The waiting queue size limit for this server.
     */
    private SelfCheckOutServer(int id, Optional<Customer> currentCustomer, int queueSize) {
        this.id = id;
        this.currentCustomer = currentCustomer;
        this.queueSize = queueSize;
    }
  
    /**
     * Change this Self-Checkout server's state to idle by removing its current customer.
     * @return A new Self-Checkout server with the current customer removed.
     */
    public Server makeIdle() {
        return new SelfCheckOutServer(id, Optional.empty(), this.queueSize);
    }

    /**
     * Checks if the current Self-Checkout server is idle.
     * @return true if the Self-Checkout server is idle (no current customer); false otherwise.
     */
    public boolean isIdle() {
        return !this.currentCustomer.isPresent();
    }
  
    /**
     * Checks if there is a customer waiting for given Self-Checkout server.
     * @return true if a customer is waiting for given Self-Checkout server; false otherwise.
     */
    public boolean hasWaitingCustomer() {
        return waitingCustomer.isPresent();
    } 
  
    /**
     * Checks if the current queue size is below it's limit.
     * @return true if the queue size is below the limit; false otherwise.
     */
    public boolean belowQueueLimit() {
        return this.waitingCustList.size() < this.queueSize;
    }

    /**
     * Gets the size of the waiting queue.
     * @return size of waiting queue.
     */
    public int getWaitingQueueSize() {
        return this.waitingCustList.size();
    }
  
    /**
     * Returns waiting customer for given Self-Checkout server.
     * @return customer waiting for given Self-Checkout server.
     */
    public Optional<Customer> getWaitingCustomer() {
        return Optional.ofNullable(waitingCustList.peek());
    }
  
    /**
     * Serve a customer.
     * @param customer The customer to be served.
     * @return The new Self-Checkout Server serving this customer.
     */
    public Server serve(Customer customer) {
        Optional<Customer> waitingCustomer = getWaitingCustomer();
        if (waitingCustomer.filter(c -> c.equals(customer)).isEmpty()) {
            return new SelfCheckOutServer(id, Optional.of(customer), this.queueSize);
        } else {
            waitingCustList.poll();
            return new SelfCheckOutServer(id, Optional.of(customer), this.queueSize);
        }
    }  

    /**
     * Make a customer wait for this Self-Checkout server.
     * @param customer The customer who will wait for this Self-Checkout server.
     * @return The new Self-Checkout Server with a waiting customer added to its queue.
     */
    public Server askToWait(Customer customer) {
        waitingCustList.add(customer);
        return new SelfCheckOutServer(id, currentCustomer, this.queueSize);
    }

    /**
     * Return a string representation of this Self-Checkout server.
     * @return A string S followed by the ID of the Self-Checkout server, followed by the
     *     waiting customer.
     */
    public String toString() {
        return Integer.toString(this.id);
    }

    /**
     * Checks if two Self-Checkout servers have the same id.
     * @param  obj Another objects to compared against.
     * @return  true if obj is a Self-Checkout server with the same id; false otherwise.
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof SelfCheckOutServer)) {
            return false;
        }
        return (this.id == ((SelfCheckOutServer)obj).id);
    }

    /**
     * Return the hashcode for this Self-Checkout server.
     * @return the ID of this Self-Checkout server as its hashcode.
     */
    public int hashCode() {
        return this.id;
    }
}
