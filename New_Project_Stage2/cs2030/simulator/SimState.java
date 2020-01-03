package cs2030.simulator;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.function.Function;
import java.util.List;
import java.util.Comparator;

/**
 * This class encapsulates all the simulation states.  There are four main
 * components: (i) the event queue, (ii) the statistics, (iii) the shop
 * (the servers) and (iv) the event logs.
 *
 * @author Song Soon Wee
 * @version CS2030 AY19/20 DES+
 */

public class SimState {

    /**
     * The Event class encapsulates information and methods pertaining to a
     * Simulator event. Stores a lambda that denotes type of event - arrival or
     * done.
     */
    private class Event implements Comparable<Event> {
        /** The time this event occurs at. */
        private double time;

        /** A function that this event will execute. */
        private Function<SimState, SimState> lambda;
    
        /**
         * Creates an event and initializes it.
         * @param time The time of occurrence.
         * @param f The function that this event will execute.
         */
        public Event(double time, Function<SimState, SimState> f) {
            this.time = time;
            this.lambda = f;
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

        /**
         * Smulates this event by applying the lambda.
         *
         * @param state The current simulation state.
         * @return A new state of simulation.
         */
        public SimState simulate(SimState state) {
            return this.lambda.apply(state);
        }
    }

    /** The priority queue of events. */
    private PriorityQueue<Event> events;

    /** The statistics maintained. */
    private final Statistics stats;

    /** The shop of servers. */
    private final Shop shop;

    /** The event logs. */
    private final String log;

    /** The customer id. */
    private final int lastCustomerId;
  
    /** The maximum number of customer in the simulation. */
    private final int maxCustomer;

    /** The random generator to generate random values. */
    private final RandomGenerator rng;
  
    /** The human server resting rate. */
    private final double restRate;
  
    /** The probability a human server goes to rest. */
    private final double restChance;
  
    /** The probability a customer is greedy. */
    private final double custChance;
  
    /**
     * A private constructor to maintain internal states.
     * @param  shop The list of servers.
     * @param  stats The statistics being kept.
     * @param  events A priority queue of events.
     * @param  log A log of what's happened so far.
     * @param  lastCustomerId The last customer ID.
     * @param  maxCustomer Maximum number of customer in this simulation.
     * @param  rng RNG that generates random values throughout the simulation.
     * @param  restChance Probability a human server goes to rest.
     * @param  custChance Probability a customer is greedy.
     */
    private SimState(Shop shop, Statistics stats, PriorityQueue<Event> events,
        String log, int lastCustomerId, int maxCustomer, RandomGenerator rng,
        double restChance, double custChance) {
        this.shop = shop;
        this.stats = stats;
        this.events = events;
        this.log = log;
        this.lastCustomerId = lastCustomerId;
        this.maxCustomer = maxCustomer;
        this.rng = rng;
        this.restRate = 0.1;
        this.restChance = restChance;
        this.custChance = custChance;
    }
  
    /** 
     * New constructor to instantiate the start of the simulation.
     * @param  seed The base seed for the RNG.
     * @param  noServer Number of human servers for this simulation.
     * @param  noCheckout Number of Self-Checkout servers for this siumulation.
     * @param  noQueue The waiting queue size limit for this simulation.
     * @param  noCustomer Number of customers in this simulation.
     * @param  arrivalRate The arrival rate of customers.
     * @param  serviceRate The service rate at which the customers are served.
     * @param  restRate The resting rate of the human servers.
     * @param  restChance The probability a human server goes to rest.
     * @param  custChance The probability a customer is greedy.
     */
    public SimState(int seed, int noServer, int noCheckout, int noQueue, int noCustomer, 
        double arrivalRate, double serviceRate, double restRate, double restChance,
        double custChance) {
        this.shop = new Shop(noServer, noCheckout, noQueue);
        this.stats = new Statistics();
        this.events = new PriorityQueue<Event>();
        this.log = "";
        this.lastCustomerId = 1;
        this.maxCustomer = noCustomer;
        this.restRate = restRate;
        this.restChance = restChance;
        this.custChance = custChance;
        this.rng = new RandomGenerator(seed, arrivalRate, serviceRate, restRate);
    }
  
    /**
     * Generate arrival events when the customers arrive.
     * @param  noCustomer Number of customers for this simulation. 
     * @return A Simstate where all arrival events are created.
     */
    public SimState createArrivalEvents(int noCustomer) {
        return Stream.iterate(0.0, t -> t + this.rng.genInterArrivalTime())
            .limit(noCustomer)
            .reduce(this,(state, time) -> state.addEvent(time, s -> s.simulateArrival(time)), 
                (x, y) -> x);
    }
  
    /**
     * Update the statistics of this simulation.
     * @param  stats The updated statistics to replace the existing one.
     * @return The new simulation state.
     */
    private SimState stats(Statistics stats) {
        return new SimState(this.shop, stats, this.events, this.log, this.lastCustomerId,
            this.maxCustomer, this.rng, this.restChance, this.custChance);
    }

    /**
     * Update a server of this simulations.
     * @param  s The updated server to replace the existing one.
     * @return The new simulation state.
     */
    private SimState server(Server s) {
        return new SimState(shop.replace(s), this.stats, this.events, this.log, 
            this.lastCustomerId, this.maxCustomer, this.rng, this.restChance, this.custChance);
    }

    /**
     * Update the event queue of this simulations.
     * @param  pq The priority queue to replace the existing one.
     * @return The new simulation state.
     */
    private SimState events(PriorityQueue<Event> pq) {
        return new SimState(this.shop, this.stats, pq, this.log, this.lastCustomerId,
            this.maxCustomer, this.rng, this.restChance, this.custChance);
    }

    /**
     * Update the event log of this simulations.
     * @param  s The log string to append to this event log.
     * @return The new simulation state.
     */
    private SimState log(String s) {
        return new SimState(this.shop, this.stats, this.events, this.log + s, this.lastCustomerId,
            this.maxCustomer, this.rng, this.restChance, this.custChance);
    }

    /**
     * Update the event log of this simulations.
     * @param  id The server id
     * @return The new simulation state.
     */
    private SimState id(int id) {
        return new SimState(this.shop, this.stats, this.events, this.log, id, this.maxCustomer,
            this.rng, this.restChance, this.custChance);
    }

    /**
     * Add an event to the simulation's event queue.
     * @param  time The time the event to be added occur.
     * @param  lambda How the state to be updated upon execution of this event.
     * @return The new simulation state.
     */
    public SimState addEvent(double time, Function<SimState, SimState> lambda) {
        return events(events.add(new Event(time, lambda)));
    }

    /**
     * Retrieve the next event with earliest time stamp from the
     * priority queue, and a new state.  If there is no more event, an
     * Optional.empty will be returned.
     * @return A pair object with an (optional) event and the new simulation
     *     state.
     */
    private Pair<Optional<Event>, SimState> nextEvent() {
        Pair<Optional<Event>, PriorityQueue<Event>> result = this.events.poll();
        return Pair.of(result.first, events(result.second));
    }

    /**
     * Called when a customer arrived in the simulation.
     * @param time The time the customer arrives.
     * @param c The customer that arrrives.
     * @return A new state of the simulation after the customer arrives.
     */
    public SimState noteArrival(double time, Customer c) {
        return log(String.format("%.3f %s" + c.strGreedy() + " arrives\n", time, c));
    }

    /**
     * Called when a customer arrived in the simulation.  This methods update
     * the logs of simulation.
     * @param time The time the customer arrives.
     * @param c The customer that arrrives.
     * @return A new state of the simulation after the customer arrives.
     */
    public SimState noteWait(double time, Server s, Customer c) {
        if (s instanceof HumanServer) {
            return log(String.format("%.3f %s" + c.strGreedy() + 
                " waits to be served by server %s\n", time, c, s));
        } else {
            return log(String.format("%.3f %s" + c.strGreedy() +
                " waits to be served by self-check %s\n", time, c, s));
        }
    }

    /**
     * Called when a customer is served in the simulation.  This methods
     * update the logs and the statistics of the simulation.
     * @param time The time the customer arrives.
     * @param s The server that serves the customer.
     * @param c The customer that is served.
     * @return A new state of the simulation after the customer is served.
     */
    public SimState noteServed(double time, Server s, Customer c) {
        if (s instanceof HumanServer) {
            return log(String.format("%.3f %s" + c.strGreedy() +
                " served by server %s\n", time, c, s))
                .stats(stats
                .serveOneCustomer()
                .recordWaitingTime(c.timeWaited(time)));
        } else {
            return log(String.format("%.3f %s" + c.strGreedy() +
                " served by self-check %s\n", time, c, s))
                .stats(stats
                .serveOneCustomer()
                .recordWaitingTime(c.timeWaited(time)));
        }
    }

    /**
     * Called when a customer is done being served in the simulation.
     * This methods update the logs of the simulation.
     * @param time The time the customer arrives.
     * @param s The server that serves the customer.
     * @param c The customer that is served.
     * @return A new state of the simulation after the customer is done being
     *     served.
     */
    public SimState noteDone(double time, Server s, Customer c) {
        if (s instanceof HumanServer) {
            return log(String.format("%.3f %s" + c.strGreedy() + 
                " done serving by server %s\n", time, c, s));
        } else {
            return log(String.format("%.3f %s" + c.strGreedy() +
                " done serving by self-check %s\n", time, c, s));
        }
    }

    /**
     * Called when a customer leaves the shops without service.
     * Update the log and statistics.
     * @param  time  The time this customer leaves.
     * @param  customer The customer who leaves.
     * @return A new state of the simulation.
     */
    public SimState noteLeave(double time, Customer customer) {
        return log(String.format("%.3f %s" + customer.strGreedy() +
            " leaves\n", time, customer))
            .stats(stats.looseOneCustomer());
    }

    /**
     * Simulates the logic of what happened when a customer arrives.
     * The customer's arrival is then processed from here.
     * @param time The time the customer arrives.
     * @return A new state of the simulation.
     */
    public SimState simulateArrival(double time) {
        double isGreedyChance = this.rng.genCustomerType();
        boolean isGreedy = false;
        if (isGreedyChance < this.custChance) {
            isGreedy = true;
        }
        Customer customer = new Customer(time, this.lastCustomerId, isGreedy);
        return noteArrival(time, customer)
            .id(this.lastCustomerId + 1)
            .processArrival(time, customer);
    }
  
    /**
     * Simulate when a human server is back from his rest to start serving any waiting
     * customer in his queue or take in any new customer. 
     * @param  time The time at which the human server resume service.
     * @param  server The human server which is resuming service from its break. 
     * @return A new state of the simulation where the human server serves a waiting
     *     customer or goes into idle state.
     */
    public SimState simulateServerBack(double time, Server server) {
        return shop.find(ser -> ser.equals(server))
            .flatMap(ser -> ser.getWaitingCustomer())
            .map(c -> serveCustomer(time, server, c))
            .orElseGet(() -> server(server.makeIdle()));
    }

    /**
     * Handle the logic of finding idle servers to serve the customer, 
     * or a server that the customer can wait for, or leave. Checks if 
     * the customer are greedy and process according to their following logic.
     * @param time The time the customer arrives.
     * @param customer The customer to be served.
     * @return A new state of the simulation.
     */
    private SimState processArrival(double time, Customer customer) {
        if (!customer.isGreedyCust()) {
            return shop.find(server -> server.isIdle())
                .map(server -> serveCustomer(time, server, customer))
                .or(() -> shop
                .find(server -> server.belowQueueLimit())
                .map(server -> noteWait(time, server, customer)
                .server(server.askToWait(customer))))
                .orElseGet(() -> noteLeave(time, customer));
        } else {
            return shop.find(server -> server.isIdle())
                .map(server -> serveCustomer(time, server, customer))
                .or(() -> shop.findAll(server -> server.belowQueueLimit())
                .stream()
                .min(Comparator.comparing(Server::getWaitingQueueSize))
                .map(server -> noteWait(time, server, customer)
                .server(server.askToWait(customer))))
                .orElseGet(() -> noteLeave(time, customer));
        }
    }

    /**
     * Simulates the logic of what happened when a customer is done being
     * served.  The server either serve the next customer or becomes idle.
     * For the human server, it can go on break if the resting chance generated is
     * less than the desired simulation rest chance.
     * @param time The time the service is done.
     * @param server The server serving the customer.
     * @param customer The customer being served.
     * @return A new state of the simulation.
     */
    public SimState simulateDone(double time, Server server, Customer customer) {
        if (server instanceof HumanServer) {
            double restingChance = this.rng.genRandomRest();
            if (restingChance < this.restChance) {
                double restDone = time + this.rng.genRestPeriod();
                return noteDone(time, server, customer)
                    .addEvent(restDone, state -> state.simulateServerBack(restDone, server));
            } else {
                return shop.find(s -> s.equals(server))
                    .flatMap(s -> s.getWaitingCustomer())
                    .map(c -> noteDone(time, server, customer).serveCustomer(time, server, c))
                    .orElseGet(() -> noteDone(time, server, customer).server(server.makeIdle()));
            }
        }
        return shop.find(s -> s.equals(server))
            .flatMap(s -> s.getWaitingCustomer())
            .map(c -> noteDone(time, server, customer).serveCustomer(time, server, c))
            .orElseGet(() -> noteDone(time, server, customer).server(server.makeIdle()));
    }
  
    /**
     * Handle the logic of server serving customer. Once the customer is served, a
     * new done event is generated and scheduled.
     * @param  time  The time this customer is served.
     * @param  server The server serving this customer.
     * @param  customer The customer being served.
     * @return A new state of the simulation.
     */
    public SimState serveCustomer(double time, Server server, Customer customer) {
        double doneTime = time + this.rng.genServiceTime();
        return shop.find(s -> s.equals(server))
            .map(s -> server(s.serve(customer)))
            .get()
            .noteServed(time, server, customer)
            .addEvent(doneTime, state -> state.simulateDone(doneTime, server, customer));
    }

    /**
     * The main simulation loop.  Repeatedly get events from the event
     * queue, simulate and update the event.  Return the final simulation
     * state.
     * @return The final state of the simulation.
     */
    public SimState run() {
        Pair<Optional<Event>,SimState> s = Stream.iterate(this.nextEvent(),
            p -> p.first.isPresent(),
            p -> p.first.get().simulate(p.second).nextEvent())
            .reduce((p, q) -> q)
            .orElseThrow();
        return s.first.get().simulate(s.second);
    } 

    /**
     * Return a string representation of the simulation state, which
     * consists of all the logs and the stats.
     * @return A string representation of the simulation.
     */
    public String toString() {
        return log + stats.toString();
    }
}
