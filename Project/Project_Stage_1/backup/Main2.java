import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Main2 {
    public static void main(String[] args) {
        ArrayList<Server> serverList = new ArrayList<>();
        ArrayList<Customer> holdingCustomerList = new ArrayList<>();
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<CustomerState> csList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int noServer = 0;
        int numberOfCustomer = 0;
        noServer = sc.nextInt();
        serverList = getNumberOfServers(noServer);
        holdingCustomerList = createHoldingList(serverList);
        while(sc.hasNext() == true) {
            double arrivalTime = sc.nextDouble();
            numberOfCustomer++;
            Customer c = new Customer(arrivalTime, numberOfCustomer);
            customerList = createArrivingCustomers(customerList, c);
            if(sc.hasNext() == false) {
                break;
            }
        }
        csList = createArrivalLogs(customerList, serverList, csList);
        EventHandler event = new EventHandler(customerList, holdingCustomerList,
            csList, serverList, 0, 0, 0);
        event.computeEventFlow1(customerList, holdingCustomerList, serverList,
        csList);
    }

    public static ArrayList<Server> getNumberOfServers(int noServer) {
        ArrayList<Server> serverList = new ArrayList<>();
        for (int a = 0; a < noServer; a++) {
            Server s = new Server(a);
            serverList.add(s);
        }
        return serverList;
    }
    
    public static ArrayList<Customer> createHoldingList(ArrayList<Server> s) {
        ArrayList<Customer> holdingCustomerList = new ArrayList<>();
        Customer emptyC = new Customer(0, 0);
        for (int b = 0; b < s.size(); b++) {
            holdingCustomerList.add(b, emptyC);
            holdingCustomerList.set(b, null);
        }
        return holdingCustomerList;
    }
    
    public static ArrayList<Customer> createArrivingCustomers(ArrayList<Customer> custList, Customer c) {
        custList.add(c);
        return custList;
    }

    public static ArrayList<CustomerState> createArrivalLogs(ArrayList<Customer>
        cust, ArrayList<Server> servers, ArrayList<CustomerState> csList) {
        Customer c = new Customer(0,0);
        State toggleState = State.ARRIVES;
        CustomerState cs = new CustomerState(c, toggleState, 0);
        ArrayList<CustomerState> csLogs = new ArrayList<>();
        for (int d = 0; d < cust.size(); d++) {
            cs = new CustomerState(cust.get(d), toggleState, 0);
            csList.add(cs);
        }
        return csList;
    }
    
    /*public void computeEventFlow1(ArrayList<Customer> custList, ArrayList<Server>
        serverList, ArrayList<CustomerState> csList) {
        int numberOfCustomer = custList.size();
        int noServer = serverList.size();
        int servedCustomer = 0; //see if there is any other way
        Server server = new Server(0);
        CustomerState cs = new CustomerState(c, toggleState, 0);
        for (int d = 0; d < numberOfCustomer; d++) {
            if (d <= noServer - 1) {
                server = serverList.get(d).serve(custList.get(d));
                cs = cs.toggleState(custList.get(d), toggleState.SERVES,
                    numberOfCustomer);
                serverList.set(d, server);
                csList.add(cs);
                Customer c = new Customer(custList.get(d).completedServiceTime(),
                    d);
                cs = cs.toggleState(c, toggleState.DONE, d);
                serverList.set(d, server);
                csList.add(s);
                servedCustomer++;
            } else {
                computeEventFlow2(custList, serverList, csList, d);
            }
        }
  }

    public void computeEventFlow2(ArrayList<Customer> custList, ArrayList<Server>
        serverList, ArrayList<CustomerState> csList, int customer) {
        Customer currentCust = custList.get(customer);
        double prevCompletedTime = 0;
        int leftCust = 0; //double check how to reimplement this.
        Server server = new Server(0);
        State toggleState = State.ARRIVES;
        CustomerState cs = new CustomerState(currentCust, toggleState, 0);
        for (int e = 0; e < serverList.size(); e++) {
            if (serverList.get(e).serve(currentCust) != null) {
                for (int f = 0; f < holdingCustomerList.size(); f++) {
                    if (holdingCustomerList.get(f) == null) {
                        if (serverList.get(f).serve(currentCust) != null) {
                            e = f;
                            break;
                        } else {
                            continue;
                        }
                    } else if((
                         serverList.get(f).servingCustomer.completedServiceTime()
                         + 1) < currentCust.getEventTime()) {
                        e = f;
                        break;
                    } else {
                        continue;
                    }
                }
                prevCompletedTime = serverList.get(e).servingCustomer.completedServiceTime();
                server = serverList.get(e)
                break;
            } else if (e == serverList.size() - 1) {
                for (int g = 0; g < holdingCustomerList.size(); g++) {
                    if (holdingCustomerList.get(g) == null) {
                        cs = cs.toggleState(currentCust, toggleState.WAITS, g + 1);
                        holdingCustomerList.set(g, currentCust);
                        csList.add(cs);
                        break;
                    } else if (g == holdingCustomerList.size() - 1) {
                        cs = cs.toggleState(currentCust, toggleState.LEAVES, e + 1);
                        csList.add(currentCustState);
                        leftCust++; //double check on this!
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
    } //Carry on from line 126;*/      
}
            
