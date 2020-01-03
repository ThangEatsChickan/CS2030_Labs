import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * This class encapsulates all the simulation states.  There are four main
 * components: (i) the event queue, (ii) the statistics, (iii) the shop
 * (the servers) and (iv) the event logs.
 *
 * @author atharvjoshi
 * @author weitsang
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
public class SimState {
	class Event implements Comparable<Event> {
		  /** The time this event occurs at. */
		  protected double time;
		  protected Function<SimState, SimState> function;

		  /**
		   * Creates an event and initializes it.
		   *
		   * @param time The time of occurrence.
		   */
		  public Event(double time,Function<SimState, SimState> func) {
		    this.time = time;
		    this.function = func;
		  }

		  /**
		   * Defines natural ordering of events by their time.
		   * Events ordered in ascending order of their timestamps.
		   *
		   * @param other Another event to compare against.
		   * @return 0 if two events occur at same time, a positive number if
		   *     this event has later than other event, a negative number otherwise.
		   */
		  public int compareTo(Event other) {
		    return (int)Math.signum(this.time - other.time);
		  }
		  
		  public SimState apply(SimState state) {
			  return this.function.apply(state);
		  }
		}
  /** The priority queue of events. */
  private final PriorityQueue<Event> events;

  /** The statistics maintained. */
  private final Statistics stats;

  /** The shop of servers. */
  private final Shop shop;
  
  private final String logs;

  private final int custID;
  /**
   * Constructor for creating the simulation state from scratch.
   * @param numOfServers The number of servers.
   */
  public SimState(int numOfServers) {
    this.shop = new Shop(numOfServers);
    this.stats = new Statistics();
    this.events = new PriorityQueue<Event>();
    this.logs = new String();
    this.custID = 0;
  }

  public SimState(Shop shop, Statistics stats, PriorityQueue<Event> event, String logs, int custID) {
	  this.shop = shop;
	  this.stats = stats;
	  this.events = event;
	  this.logs = logs;
	  this.custID = custID;
  }
  
  
  /**
   * Add an event to the simulation's event queue.
   * @param  e The event to be added to the queue.
   * @return The new simulation state.
   */
  /*public SimState addEvent(Event e) {
	SimState newSimState = new SimState(this.shop, this.stats, this.events, this.logs);
    newSimState.events.add(e);
    //return this;
    return newSimState;
  }*/
  
  public SimState addEvent(double time, Function<SimState, SimState> func) {
	  PriorityQueue<Event> newEvents = this.events.add(new Event(time, func));
      return new SimState(this.shop, this.stats, newEvents, this.logs, this.custID);
  }
  
  private SimState editEvents(PriorityQueue<Event> events) {
      return new SimState(this.shop, this.stats, events, this.logs, this.custID);
  }

  private SimState editShop(Shop shop) {
      return new SimState(shop, this.stats, this.events, this.logs, this.custID);
  }
  /**
   * Retrieve the next event with earliest time stamp from the
   * priority queue, and a new state.  If there is no more event, an
   * Optional.empty will be returned.
   * @return A pair object with an (optional) event and the new simulation
   *     state.
   */
  private Pair<Optional<Event>, SimState> nextEvent() {//Previously was Pair<Event,SimState>
	  Pair<Optional<Event>, PriorityQueue<Event>> result = this.events.poll();
	  SimState newSimState = this.editEvents(result.second);
	  return Pair.of(result.first, newSimState);
  }
  /**
   * Log a customer's arrival in the simulation.
   * @param time The time the customer arrives.
   * @param c The customer that arrives.
   * @return A new state of the simulation after the customer arrives.
   */
  public SimState noteArrival(double time, Customer c) {
	String newLogs = this.logs;
    newLogs = newLogs + String.format("%.3f %s arrives\n", time, c);
    return new SimState(this.shop, this.stats, this.events, newLogs, this.custID);
  }

  /**
   * Log when a customer waits in the simulation.  
   * @param time The time the customer starts waiting.
   * @param s The server the customer is waiting for.
   * @param c The customer who waits.
   * @return A new state of the simulation after the customer waits.
   */
  public SimState noteWait(double time, Server s, Customer c) { //Server changed to Optional
	String newLogs = this.logs;
    newLogs = newLogs + String.format("%.3f %s waits to be served by %s\n", time, c, s);
    return new SimState(this.shop, this.stats, this.events, newLogs, this.custID);
  }

  /**
   * Log when a customer is served in the simulation.  
   * @param time The time the customer arrives.
   * @param s The server that serves the customer.
   * @param c The customer that is served.
   * @return A new state of the simulation after the customer is served.
   */
  public SimState noteServed(double time, Server s, Customer c) {
	String newLogs = this.logs;
    newLogs = newLogs + String.format("%.3f %s served by %s\n", time, c, s);
    Statistics newStats = this.stats;
    newStats = newStats.serveOneCustomer().recordWaitingTime(c.timeWaited(time));
    return new SimState(this.shop, newStats, this.events, newLogs, this.custID);
  }

  /**
   * Log when a customer is done being served in the simulation.
   * @param time The time the customer arrives.
   * @param s The server that serves the customer.
   * @param c The customer that is served.
   * @return A new state of the simulation after the customer is done being
   *     served.
   */
  public SimState noteDone(double time, Server s, Customer c) {
	String newLogs = this.logs;
    newLogs = newLogs + String.format("%.3f %s done serving by %s\n", time, c, s);
    return new SimState(this.shop, this.stats, this.events, newLogs, this.custID);
  }

  /**
   * Log when a customer leaves the shops without service.
   * @param  time  The time this customer leaves.
   * @param  customer The customer who leaves.
   * @return A new state of the simulation.
   */
  public SimState noteLeave(double time, Customer customer) {
	String newLogs = this.logs;
    newLogs = newLogs + String.format("%.3f %s leaves\n", time, customer);
    Statistics newStats = this.stats.looseOneCustomer();
    return new SimState(this.shop, newStats, this.events, newLogs, this.custID);
  }

  /**
   * Simulates the logic of what happened when a customer arrives.
   * The customer is either served, waiting to be served, or leaves.
   * @param time The time the customer arrives.
   * @return A new state of the simulation.
   */
  public SimState simulateArrival(double time) {
	int newCustID = this.custID + 1;
    Customer customer = new Customer(time, newCustID);
    SimState newSimState = new SimState(this.shop, this.stats, this.events, this.logs, newCustID);
    newSimState = newSimState.noteArrival(time, customer);
    newSimState = newSimState.processArrival(time, customer);
    return newSimState;
  }

  /**
   * Handle the logic of finding idle servers to serve the customer, 
   * or a server that the customer can wait for, or leave.  Called
   * from simulateArrival.
   * @param time The time the customer arrives.
   * @param customer The customer to be served.
   * @return A new state of the simulation.
   */
  private SimState processArrival(double time, Customer customer) {
	SimState newSimState = new SimState(this.shop, this.stats, this.events, this.logs, this.custID);
    Optional<Server> s = newSimState.shop.find(server -> server.isIdle());
	Optional<Customer> c = Optional.of(customer); //not defined originally
    if (s.isPresent()) {
      newSimState = newSimState.serveCustomer(time, s, c);
      return newSimState; 
    }
    s = newSimState.shop.find(server -> !server.hasWaitingCustomer());
    if (s.isPresent()) { 
      newSimState = newSimState.noteWait(time, s.get(), customer); // this.noteWait
      newSimState = newSimState.editShop(this.shop.replace(s.get().askToWait(customer)));
      return newSimState;
    }
    newSimState = newSimState.noteLeave(time, customer); //this.noteLeave
    return newSimState;
  }

  /**
   * Simulate the logic of what happened when a customer is done being
   * served.  The server either serve the next customer or becomes idle.
   * @param time The time the service is done.
   * @param server The server serving the customer.
   * @param customer The customer being served.
   * @return A new state of the simulation.
   */
  public SimState simulateDone(double time, Server server, Customer customer) {
	SimState newSimState = new SimState(this.shop, this.stats, this.events, this.logs, this.custID);
    newSimState = newSimState.noteDone(time, server, customer);
    Server targetServer = newSimState.shop.find(s -> s.equals(server)).get();
    Optional<Server> s = Optional.of(targetServer);
    Optional <Customer> c = targetServer.getWaitingCustomer();
    if (c.isPresent()) { //(c!=null)
      newSimState = newSimState.serveCustomer(time, s, c);
      return newSimState;
    }
    newSimState = newSimState.editShop(this.shop.replace(targetServer.makeIdle()));
     return newSimState;
  }

  /**
   * Handle the logic of server serving customer.  A new done event
   * is generated and scheduled.
   * @param  time  The time this customer is served.
   * @param  server The server serving this customer.
   * @param  customer The customer being served.
   * @return A new state of the simulation.
   */
  private SimState serveCustomer(double time, Optional<Server> server, Optional<Customer> customer) {
    double doneTime = time + Simulation.SERVICE_TIME;
    SimState newSimState = new SimState(this.shop, this.stats, this.events, this.logs, this.custID);
    newSimState = newSimState.editShop(this.shop.replace(server.get().serve(customer.get())));
    newSimState = newSimState.noteServed(time, server.get(), customer.get());
    newSimState = newSimState.addEvent(doneTime, s -> s.simulateDone(doneTime, server.get(), customer.get()));
    return new SimState(newSimState.shop, newSimState.stats, newSimState.events, newSimState.logs, newSimState.custID);
  }

  /**
   * The main simulation loop.  Repeatedly get events from the event
   * queue, simulate and update the event.  Return the final simulation
   * state.
   * @return The final state of the simulation.
   */
  public SimState run() {
    Pair<Optional<Event>, SimState> pair = nextEvent();
    return Stream.iterate(pair,p -> p.first.get().apply(p.second).nextEvent())
            .dropWhile(p -> p.first.isPresent())
            .findFirst().get().second;
  }

  /**
   * Return a string representation of the simulation state, which
   * consists of all the logs and the stats.
   * @return A string representation of the simulation.
   */
  public String toString() {
	String s1 = this.logs + this.stats.toString();
	return s1;
  }
}
