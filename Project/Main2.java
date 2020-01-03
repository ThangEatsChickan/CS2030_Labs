import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCustomer = 0;
        double arrivalTime = 0;
        double completedTime = 0;
        int servedCust = 0;
        int leftCust = 0;
        double avgWaitingTime = 0;
        int serverNo = 0;
        int serverID = 0;
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        ArrayList<Customer> holdingCustomerList = new ArrayList<Customer>();
        ArrayList<CustomerState> csList = new ArrayList<CustomerState>();
        ArrayList<Server> servingLogs = new ArrayList<Server>();
        ArrayList<Server> serverList = new ArrayList<Server>();
        String output = new String();
        serverNo = scanner.nextInt();
        Server s = new Server(1);
        serverList.add(s);
        if (serverNo > 1) {
            for (int a = 0; a < serverNo-1; a++) {
                s = new Server(a+1);
                serverList.add(s); //Create server accordingly.
            }
        }
        while (scanner.hasNext() == true) {
            arrivalTime = scanner.nextDouble();
            numberOfCustomer++;
            Customer c = new Customer(arrivalTime, numberOfCustomer);
            Customer completedC = new Customer(completedTime, numberOfCustomer);
            State toggleState = State.ARRIVES;
            CustomerState cs = new CustomerState(c, toggleState, 1);
            CustomerStateHandler csh = new CustomerStateHandler(cs);
            customerList.add(c);
            csList.add(cs);
            if (numberOfCustomer <= serverList.size()) {
                s = serverList.get(numberOfCustomer-1).serve(c);
                cs = cs.toggleState(c, toggleState.SERVES, numberOfCustomer);
                serverList.set(numberOfCustomer-1, s);
                //servingLogs.add(s);
                csList.add(cs); // add served log
                c = new Customer(c.completedServiceTime(), numberOfCustomer);
                cs = cs.toggleState(c, toggleState.DONE, numberOfCustomer); // add done log
                csList.add(cs);
                servedCust++;
            } else {
                for (int a = 0; a < serverList.size(); a++) {
                    if (serverList.get(a).serve(c) !=null) {
                        s = serverList.get(a).serve(c);
                        serverList.set(a, s);
                        serverID = a;
                        break;
                    }
                    else {
                        if(holdingCustomerList.size() == 0 || holdingCustomerList.get(a) == null) { 
                            cs = cs.toggleState(c, toggleState.WAITS, a);
                            holdingCustomerList.add(a, c);
                            break;
                        } else if (a == serverList.size() - 1){
                            //servingLogs.add(s.serve(c));
                            cs = cs.toggleState(c, toggleState.LEAVES, a);
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            if (cs.currentState == toggleState.WAITS) {
                csList.add(cs);
                continue;
            }
            if (cs.currentState != toggleState.LEAVES) {
            if (holdingCustomerList.size()>0 && holdingCustomerList.get(serverID) != null) {
                avgWaitingTime = avgWaitingTime + (s.servingCustomer.completedServiceTime() - holdingCustomerList.get(serverID).getArrivalTime());
                Customer holdCust = new Customer(s.servingCustomer.completedServiceTime(), holdingCustomerList.get(serverID).getCustomerID());
                s = s.serve(holdCust);
                servingLogs.add(s);
                cs = cs.toggleState(holdCust, toggleState.SERVES, serverID + 1);
                csList.add(cs);
                servedCust++;
                completedC = new Customer(holdCust.completedServiceTime(), holdCust.getCustomerID());
                cs = cs.toggleState(completedC, toggleState.DONE, serverID + 1);
                csList.add(cs);
                holdingCustomerList.remove(serverID);//remove pending customer
                if (c.getArrivalTime() < holdCust.completedServiceTime()) {
                    cs = cs.toggleState(c, toggleState.WAITS, serverID + 1);
                    holdingCustomerList.add(serverID, c);
                } else { 
                    s = s.serve(c);
                    servingLogs.add(s);
                    cs = cs.toggleState(c, toggleState.SERVES, serverID + 1);
                    csList.add(cs);
                    servedCust++;    
                    c = new Customer(c.completedServiceTime(), numberOfCustomer);
                    cs = cs.toggleState(c, toggleState.DONE, serverID + 1); // add done log
                }
            } else {
                    s = s.serve(c);
                    servingLogs.add(s);
                    cs = cs.toggleState(c, toggleState.SERVES, serverID + 1);
                    csList.add(cs);
                    servedCust++;    
                    c = new Customer(c.completedServiceTime(), numberOfCustomer);
                    cs = cs.toggleState(c, toggleState.DONE, serverID + 1); // add done log
                }
            }
            }
            csList.add(cs);
            if (scanner.hasNext() == false) { 
                if (holdingCustomerList.size() > 0 && holdingCustomerList.get(serverID) != null) {
                    c = holdingCustomerList.get(serverID);
                    avgWaitingTime = avgWaitingTime + (s.servingCustomer.completedServiceTime() - c.getArrivalTime());
                    c = new Customer(s.servingCustomer.completedServiceTime(), c.getCustomerID());
                    s = s.serve(c);
                    servingLogs.add(s);
                    cs = cs.toggleState(c, toggleState.SERVES, serverID + 1);
                    csList.add(cs);
                    servedCust++;
                    completedC = new Customer(c.completedServiceTime(), c.getCustomerID());
                    cs = cs.toggleState(completedC, toggleState.DONE, serverID + 1);
                    csList.add(cs);
                    holdingCustomerList.remove(serverID); //remove pending customer
                }
                csh.computeEvent(csList, numberOfCustomer);
                break;
            }
        }
        /*for (int a = 0; a < csList.size(); a++) {
            System.out.println(csList.get(a));
        }*/
        //for (int i = 0; i < customerList.size(); i++) {
            //System.out.println(customerList.get(i));
           // System.out.println(csList.get(2 * i));
            /*if (servingLogs.get(i) != null) {
                output = String.format("Customer served; next service @ %.3f", 
                customerList.get(i).completedServiceTime());
                System.out.println(output);
            } else {
                System.out.println("Customer leaves");
            }*/
            //System.out.println(csList.get((2 * i) + 1));
       // }
        avgWaitingTime = avgWaitingTime / servedCust;
        output = String.format("%.3f", avgWaitingTime);
        System.out.println("["+ output + " "+ servedCust + " " +  leftCust + "]");
    }
}
