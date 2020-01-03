package cs2030.simulator;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * A server interface which provides the general method signature that all 
 * server types need to have.  
 *
 * @author Song Soon Wee
 * @version CS2030 AY19/20 DES+
 */
interface Server {
    public Server makeIdle();
  
    public boolean isIdle();

    public boolean hasWaitingCustomer();
   
    public int getWaitingQueueSize();
  
    public boolean belowQueueLimit();

    public Optional<Customer> getWaitingCustomer();
  
    public Server serve(Customer customer);

    public Server askToWait(Customer customer);

    public String toString();

    public boolean equals(Object obj);

    public int hashCode();
}
