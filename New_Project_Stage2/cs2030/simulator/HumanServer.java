package cs2030.simulator;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * The Human Server class keeps track of who is the customer being served (if any)
 * and who is the customer waiting to be served (if any).
 *
 * @author Song Soon Wee
 * @version CS2030 AY19/20 DES+
 */

class HumanServer implements Server {
    /** The unique ID of this server. */
    private final int id;

    /** The customer currently being served, if any. */
    private Optional<Customer> currentCustomer;

    /** The customer currently waiting, if any. */
    private Optional<Customer> waitingCustomer;
  
    /** The customer currently waiting in the queue for the respective server. */
    private Queue<Customer> waitingCustList;
  
    /** The waiting queue size limit.*/
    private final int queueSize;
  
    /**
     * Creates a human server and initalizes it with a unique id and queue size.
     * @param  id ID of this human server.
     * @param  noQueue The waiting queue size limit for this server.
     */
    public HumanServer(int id, int noQueue) {
        this.currentCustomer = Optional.empty();
        this.waitingCustomer = Optional.empty();
        this.id = id;
        this.waitingCustList = new LinkedList<>();
        this.queueSize = noQueue;
    }

    /**
     * Private constructor for a Human server.
     * @param  id ID of this human server.
     * @param  currentCustomer The current customer it is serving, if any.
     * @param  waitingCustomer The customer currently waiting, if any.
     * @param  list The waiting queue list for this server.
     * @param  queueSize The waiting queue size limit for this server.
     */
    private HumanServer(int id, Optional<Customer> currentCustomer,
        Optional<Customer> waitingCustomer, Queue<Customer> list, int queueSize) {
        this.id = id;
        this.currentCustomer = currentCustomer;
        this.waitingCustomer = waitingCustomer;
        this.waitingCustList = list;
        this.queueSize = queueSize;
    }
  
    /**
     * Change this human server's state to idle by removing its current customer.
     * @return A new server with the current customer removed.
     */
    public Server makeIdle() {
        return new HumanServer(id, Optional.empty(), waitingCustomer, this.waitingCustList, 
            this.queueSize);
    }

    /**
     * Checks if the current human server is idle.
     * @return true if the human server is idle (no current customer); false otherwise.
     */
    public boolean isIdle() {
        return !this.currentCustomer.isPresent();
    }

    /**
     * Checks if there is a customer waiting for given human server.
     * @return true if a customer is waiting for given human server; false otherwise.
     */
    public boolean hasWaitingCustomer() {
        return waitingCustomer.isPresent();
    }
  
    /**
     * Gets the size of the waiting queue.
     * @return size of waiting queue.
     */
    public int getWaitingQueueSize() {
        return this.waitingCustList.size();
    }
  
    /**
     * Checks if the current queue size is below it's limit.
     * @return true if the queue size is below the limit; false otherwise.
     */
    public boolean belowQueueLimit() {
        return this.waitingCustList.size() < this.queueSize;
    }

    /**
     * Returns waiting customer for given human server.
     * @return customer waiting for given human server.
     */
    public Optional<Customer> getWaitingCustomer() {
        return Optional.ofNullable(waitingCustList.peek());
    }  
  
    /**
     * Serve a customer.
     * @param customer The customer to be served.
     * @return The new human server serving this customer.
     */
    public Server serve(Customer customer) {
        Optional<Customer> waitingCustomer = getWaitingCustomer();
        if (waitingCustomer.filter(c -> c.equals(customer)).isEmpty()) {
            return new HumanServer(id, Optional.of(customer), waitingCustomer, 
                waitingCustList, queueSize);
        } else {
            Queue<Customer> copy = new LinkedList<>(this.waitingCustList);
            copy.poll();
            return new HumanServer(id, Optional.of(customer), Optional.empty(), copy, queueSize);
        }
    }

    /**
     * Make a customer wait for this human server.
     * @param customer The customer who will wait for this human server.
     * @return The new human server with a waiting customer added to its queue.
     */
    public Server askToWait(Customer customer) {
        Queue<Customer> copy = new LinkedList<>(this.waitingCustList);
        copy.add(customer);
        return new HumanServer(id, currentCustomer, this.waitingCustomer, copy, queueSize);
    }

    /**
     * Return a string representation of this human server.
     * @return A string S followed by the ID of the human server, followed by the
     *     waiting customer.
     */
    public String toString() {
        return Integer.toString(this.id);
    }

    /**
     * Checks if two human servers have the same id.
     * @param  obj Another objects to compared against.
     * @return  true if obj is a human server with the same id; false otherwise.
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof HumanServer)) {
            return false;
        }
        return (this.id == ((HumanServer)obj).id);
    }

    /**
     * Return the hashcode for this human server.
     * @return the ID of this human server as its hashcode.
     */
    public int hashCode() {
        return this.id;
    }
}
