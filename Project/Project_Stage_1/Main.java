import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
/**
 * The Main class defines an application that will display an event flow
 * based on the number of servers available and the number of customers 
 * arriving at various time. 
 *  
 * @author  Song Soon Wee
 * @version 1.0
 * @since   2019-10-05
 */

class Main {
    /**
     * The main method defines a list of steps which the program will
     * take to achieve the final output. It takes in the number of server first
     * which will then create the number of servers first and its respective
     * customer holding list. After creating, it will then take in the arrival
     * time of the various customers and try to see if they can be allocated to
     * any of the servers based on the various conditions provided:
     *
     * <p><ul>
     * <li>Serving no customer at that time
     * <li>Serving a customer but has no one in the waiting list at that time
     * </ul>
     *
     * <p>If the conditions are not met, the customers will then have to leave.
     * At any point of time when there is a change in state, it will be
     * added into a list that will keep track of the event. At the end when
     * there is no more input, this list will then be sorted according to their
     * arrival time, followed by the customerID. 
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCustomer = 0;
        double arrivalTime = 0;
        double completedTime = 0;
        int servedCust = 0;
        int leftCust = 0;
        double avgWaitingTime = 0;
        double prevCompletedTime = 0;
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        ArrayList<Customer> holdingCustomerList = new ArrayList<Customer>();
        ArrayList<CustomerState> csList = new ArrayList<CustomerState>();
        ArrayList<Server> serverList = new ArrayList<Server>();
        Server s = new Server(1);
        Customer blankc = new Customer(arrivalTime,numberOfCustomer);
        String output = new String();
        int noServer = 0; 
        int serverID = 0;
        noServer = scanner.nextInt();
        for (int i = 0; i < noServer; i++) {
            s = new Server(i);
            serverList.add(s);
            holdingCustomerList.add(i, blankc);
            holdingCustomerList.set(i, null);
        }
        while (scanner.hasNext() == true) {
            arrivalTime = scanner.nextDouble();
            numberOfCustomer++;
            Customer c = new Customer(arrivalTime, numberOfCustomer);
            Customer completedC = new Customer(completedTime, numberOfCustomer);
            State toggleState = State.ARRIVES;
            CustomerState cs = new CustomerState(c, toggleState, serverID);
            CustomerStateHandler csh = new CustomerStateHandler(cs);
            customerList.add(c);
            csList.add(cs);
            if (numberOfCustomer <= noServer) {
                s = serverList.get(numberOfCustomer - 1).serve(c);
                cs = cs.toggleState(c, toggleState.SERVES, numberOfCustomer);
                serverList.set(numberOfCustomer - 1, s);
                csList.add(cs);
                c = new Customer(c.completedServiceTime(), numberOfCustomer);
                cs = cs.toggleState(c, toggleState.DONE, numberOfCustomer);
                serverList.set(numberOfCustomer - 1, s);
                csList.add(cs);
                servedCust++;
            } else {
                for (int i = 0; i < serverList.size(); i++) {
                    if (serverList.get(i).serve(c) != null) {
                        for (int j = 0; j < holdingCustomerList.size(); j++) {
                            if (holdingCustomerList.get(j) == null) {
                                if (serverList.get(j).serve(c) != null) {
                                    i = j;
                                    break;
                                } else {
                                    continue;
                                }
                            } else if ((
                                 serverList.get(j).servingCustomer.completedServiceTime() 
                                 + 1) < c.getEventTime()) {
                                i = j; 
                                break;
                            } else {
                                continue;
                            }
                        }
                        prevCompletedTime = 
                        serverList.get(i).servingCustomer.completedServiceTime();
                        s = serverList.get(i);
                        break;
                    } else if (i == serverList.size() - 1) {
                        for (int a = 0; a < holdingCustomerList.size(); a++) {
                            if (holdingCustomerList.get(a) == null) {
                                cs = cs.toggleState(c, toggleState.WAITS, a + 1);
                                holdingCustomerList.set(a, c);
                                csList.add(cs);
                                break;
                            } else if (a == holdingCustomerList.size() - 1) {
                                cs = cs.toggleState(c, toggleState.LEAVES, i + 1);
                                leftCust++;
                                csList.add(cs);
                                break;
                            } else {
                                continue;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            if (cs.currentState == toggleState.ARRIVES) {
                if (holdingCustomerList.get(s.serverID) != null) {
                    avgWaitingTime = avgWaitingTime + (prevCompletedTime - 
                    holdingCustomerList.get(s.serverID).getEventTime());
                    Customer holdCust = new Customer(
                        prevCompletedTime, holdingCustomerList.get(s.serverID).getCustomerID());
                    s = s.serve(holdCust);
                    serverList.set(s.serverID, s);
                    cs = cs.toggleState(holdCust, toggleState.SERVES, s.serverID + 1);
                    csList.add(cs);
                    servedCust++;
                    completedC = new Customer(
                        holdCust.completedServiceTime(), holdCust.getCustomerID());
                    cs = cs.toggleState(completedC, toggleState.DONE, s.serverID + 1);
                    csList.add(cs);
                    holdingCustomerList.set(s.serverID, null);
                    if (c.getEventTime() <= holdCust.completedServiceTime()) {
                        cs = cs.toggleState(c, toggleState.WAITS, s.serverID + 1);
                        holdingCustomerList.set(s.serverID,c);
                        csList.add(cs);
                    } else { 
                        s = s.serve(c);
                        serverList.set(s.serverID, s);
                        cs = cs.toggleState(c, toggleState.SERVES, s.serverID + 1);
                        csList.add(cs);
                        servedCust++;    
                        c = new Customer(c.completedServiceTime(), numberOfCustomer);
                        cs = cs.toggleState(c, toggleState.DONE, s.serverID + 1);
                        csList.add(cs);
                    }
                } else {
                    s = s.serve(c);
                    serverList.set(s.serverID, s);
                    cs = cs.toggleState(c, toggleState.SERVES, s.serverID + 1);
                    csList.add(cs);
                    servedCust++;    
                    c = new Customer(c.completedServiceTime(), numberOfCustomer);
                    cs = cs.toggleState(c, toggleState.DONE, s.serverID + 1);
                    csList.add(cs);
                }
            }
            if (scanner.hasNext() == false) { 
                if (holdingCustomerList.size() >= 1) {
                    for (int i = 0; i < holdingCustomerList.size(); i++) {
                        if (holdingCustomerList.get(i) != null) {
                            c = holdingCustomerList.get(i);
                            s = serverList.get(i);
                            avgWaitingTime = avgWaitingTime + (
                                s.servingCustomer.completedServiceTime() - c.getEventTime());
                            c = new Customer(
                                s.servingCustomer.completedServiceTime(), c.getCustomerID());
                            s = s.serve(c);
                            serverList.set(s.serverID, s);
                            cs = cs.toggleState(c, toggleState.SERVES, s.serverID + 1);
                            csList.add(cs);
                            servedCust++;
                            completedC = new Customer(c.completedServiceTime(), c.getCustomerID());
                            cs = cs.toggleState(completedC, toggleState.DONE, s.serverID + 1);
                            csList.add(cs);
                            holdingCustomerList.set(i, null);
                        } else {
                            continue;
                        }
                    }
                }
                csh.sortEvent(csList);
                break;
            }
        }
        avgWaitingTime = avgWaitingTime / servedCust;
        output = String.format("%.3f", avgWaitingTime);
        System.out.println("[" + output + " " + servedCust + " " +  leftCust + "]");
    }
}
