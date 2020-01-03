import java.util.List;
import java.util.ArrayList;

class EventHandler {
    public ArrayList<Customer> customerList = new ArrayList<>();
    public ArrayList<Customer> holdingCustomerList = new ArrayList<>();
    public ArrayList<CustomerState> csList = new ArrayList<>();
    public ArrayList<Server> serverList = new ArrayList<>();
    public double avgWaitingTime;
    public int servedCustNo;
    public int leftCustNo;

    public EventHandler(ArrayList<Customer> custList, ArrayList<Customer>
        holdingCustList, ArrayList<CustomerState> csList, ArrayList<Server>
        serverList, int waitingTime, int servedCust, int leftCust) {
        this.customerList = custList;
        this.holdingCustomerList = holdingCustList;
        this.csList = csList;
        this.serverList = serverList;
        this.avgWaitingTime = waitingTime;
        this.servedCustNo = servedCust;
        this.leftCustNo = leftCust;
   }
   
    public ArrayList<Customer> getCustomerList() {
        return this.customerList;
    }
   
    public ArrayList<Customer> getHoldingList() {
        return this.holdingCustomerList;
    }
   
    public ArrayList<CustomerState> getStateList() {
        return this.csList;
    }
   
    public ArrayList<Server> getServerList() {
         return this.serverList;
    } 
   
    public double getAvgWaitTime() {
        return this.avgWaitingTime;
    }
    
    public int getServedCustNo() {
        return this.servedCustNo;
    }
    
    public int getLeftCustNo() {
        return this.leftCustNo;
    }

    public ArrayList<Customer> setCustomerList(ArrayList<Customer> custList) {
        return this.customerList = custList;
    }
    
    public ArrayList<Customer> setHoldingList(ArrayList<Customer> holdList) {
        return this.holdingCustomerList = holdList;
    }
   
    public ArrayList<CustomerState> setCustStateList(ArrayList<CustomerState> 
        stateList) {
        return this.csList = stateList;
    }
    
    public ArrayList<Server> setServerList(ArrayList<Server> serverList) {
        return this.serverList = serverList;
    }

    public double setAvgWaitTime(double avgWaitTime) {
        return this.avgWaitingTime = avgWaitTime;
    }
    
    public int setServedCustNo(int servedCustNo) {
        return this.servedCustNo = servedCustNo;
    }
    
    public int setLeftCustNo(int leftCustNo) {
        return this.leftCustNo = leftCustNo;
    }
    public void computeEventFlow1(ArrayList<Customer> custList, ArrayList<Customer> 
        holdingCustList, ArrayList<Server> serverList, ArrayList<CustomerState> csList) {
        int numberOfCustomer = custList.size();
        int noServer = serverList.size();
        State toggleState = State.ARRIVES;
        Server server = new Server(0);
        Customer c = new Customer(0, 0);
        CustomerState cs = new CustomerState(c, toggleState, 0);
        for (int d = 0; d < numberOfCustomer; d++) {
            if (d <= noServer - 1) {
                System.out.println(custList.get(d));
                server = serverList.get(d).serve(custList.get(d));
                cs = cs.toggleState(custList.get(d), toggleState.SERVES,
                    numberOfCustomer);
                serverList.set(d, server);
                csList.add(cs);
                c = new Customer(custList.get(d).completedServiceTime(),
                    d);
                cs = cs.toggleState(c, toggleState.DONE, d);
                serverList.set(d, server);
                csList.add(cs);
                int servedCustNo = getServedCustNo();
                setServedCustNo(servedCustNo + 1);
                setCustStateList(csList);
                setServerList(serverList);
            } else {
                computeEventFlow2(custList, holdingCustList, serverList, csList, d);
            }
        }
    }

    public void computeEventFlow2(ArrayList<Customer> custList, ArrayList<Customer> 
        holdingCustList, ArrayList<Server> serverList, ArrayList<CustomerState> csList,
        int customer) {
        Customer currentCust = custList.get(customer);
        double prevCompletedTime = 0;
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
                server = serverList.get(e); 
                break;
            } else if (e == serverList.size() - 1) {
                for (int g = 0; g < holdingCustList.size(); g++) {
                    if (holdingCustList.get(g) == null) {
                        cs = cs.toggleState(currentCust, toggleState.WAITS, g + 1);
                        holdingCustList.set(g, currentCust);
                        setHoldingList(holdingCustList);
                        csList.add(cs);
                        setCustStateList(csList);
                        break;
                    } else if (g == holdingCustList.size() - 1) {
                        cs = cs.toggleState(currentCust, toggleState.LEAVES, e + 1);
                        csList.add(cs);
                        setCustStateList(csList);
                        int leftCustomer = getLeftCustNo();
                        setLeftCustNo(leftCustomer + 1);
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }
    computeEventFlow3(server.getServerID(), currentCust.getCustomerID(),
        prevCompletedTime);
    }
    
    public void computeEventFlow3(int serverID, int customerID, double 
        prevCompletedTime) {
        State toggleState = State.ARRIVES;
        ArrayList<Customer> holdingCustList = getHoldingList();
        ArrayList<Server> serverList = getServerList();
        ArrayList<CustomerState> csList = getStateList();
        ArrayList<Customer> custList = getCustomerList();
        Customer c = holdingCustList.get(customerID - 1);
        Server s = serverList.get(serverID);
        CustomerState cs = new CustomerState(c, toggleState, serverID); 
        if (cs.currentState == toggleState.ARRIVES) {
            if (holdingCustList.get(s.serverID) != null) {
                double avgWaitingTime = getAvgWaitTime();
                avgWaitingTime = avgWaitingTime + (prevCompletedTime - 
                holdingCustList.get(s.serverID).getEventTime());
                setAvgWaitTime(avgWaitingTime);
                Customer holdCust = new Customer(
                prevCompletedTime, holdingCustList.get(s.serverID).getCustomerID());
                s = s.serve(holdCust);
                serverList.set(s.serverID, s);
                cs = cs.toggleState(holdCust, toggleState.SERVES, s.serverID + 1);
                csList.add(cs);
                int servedCustNo = getServedCustNo();
                setServedCustNo(servedCustNo + 1);
                Customer completedC = new Customer(
                    holdingCustList.get(s.serverID).completedServiceTime(), 
                    holdingCustList.get(s.serverID).getCustomerID());
                    cs = cs.toggleState(completedC, toggleState.DONE, s.serverID + 1);
                    csList.add(cs);
                    holdingCustList.set(s.serverID, null); //remove pending customer
                    if (c.getEventTime() <= holdCust.completedServiceTime()) {
                        cs = cs.toggleState(c, toggleState.WAITS, s.serverID + 1);
                        holdingCustList.set(s.serverID,c);
                        csList.add(cs);
                    } else { 
                        s = s.serve(c);
                        serverList.set(s.serverID, s);
                        cs = cs.toggleState(c, toggleState.SERVES, s.serverID + 1);
                        csList.add(cs);
                        int servedCustNum = getServedCustNo();
                        setServedCustNo(servedCustNum + 1);    
                        c = new Customer(c.completedServiceTime(), c.getCustomerID());
                        cs = cs.toggleState(c, toggleState.DONE, s.serverID + 1); // add done log
                        csList.add(cs);
                    }
                } else {
                    s = s.serve(c);
                    serverList.set(s.serverID, s);
                    cs = cs.toggleState(c, toggleState.SERVES, s.serverID + 1);
                    csList.add(cs);
                    int servedCustNo = getServedCustNo();
                    setServedCustNo(servedCustNo + 1);
                    c = new Customer(c.completedServiceTime(), c.getCustomerID());
                    cs = cs.toggleState(c, toggleState.DONE, s.serverID + 1); // add done log
                    csList.add(cs);
                }
            }
        setHoldingList(holdingCustList);
        setCustStateList(csList);
        setServerList(serverList);
        computeEventFlow4();
    }

    public void computeEventFlow4() { 
        ArrayList<Customer> holdingCustList = getHoldingList();
        ArrayList<Server> serverList = getServerList();
        ArrayList<CustomerState> csList = getStateList();
        ArrayList<Customer> custList = getCustomerList();
        Customer c = new Customer(0, 0);
        State toggleState = State.ARRIVES;
        CustomerState cs = new CustomerState(c, toggleState, 0); 
        CustomerStateHandler csh = new CustomerStateHandler(cs); // check if this is needed.
        double avgWaitingTime = getAvgWaitTime();
        String output = new String();
            if (holdingCustomerList.size() >= 1) {
                for (int i = 0; i < holdingCustomerList.size(); i++) {
                    if (holdingCustomerList.get(i) != null) {
                        c = holdingCustomerList.get(i);
                        Server s = serverList.get(i);
                        avgWaitingTime = avgWaitingTime + (
                            s.servingCustomer.completedServiceTime() - c.getEventTime());
                        setAvgWaitTime(avgWaitingTime);
                        c = new Customer(
                            s.servingCustomer.completedServiceTime(), c.getCustomerID());
                        s = s.serve(c);
                        serverList.set(s.serverID, s);
                        cs = cs.toggleState(c, toggleState.SERVES, s.serverID + 1);
                        csList.add(cs);
                        int servedCustNo = getServedCustNo();
                        setServedCustNo(servedCustNo + 1);
                        Customer completedC = new Customer(c.completedServiceTime(),
                            c.getCustomerID());
                        cs = cs.toggleState(completedC, toggleState.DONE, s.serverID + 1);
                        csList.add(cs);
                        holdingCustomerList.set(i, null); //remove pending customer
                        } else {
                            continue;
                        }
                    }
                }
        csh.sortEvent(csList);
        avgWaitingTime = avgWaitingTime / getServedCustNo();
        output = String.format("%.3f", avgWaitingTime);
        System.out.println("[" + output + " " + getServedCustNo() + " " + getLeftCustNo() +
            "]");
    }
}
