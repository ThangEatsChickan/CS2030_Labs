package cs2030.simulator;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.ArrayList;

/**
 * A shop object maintains the list of servers and support queries
 * for server.
 *
 * @author Song Soon Wee
 * @version CS2030 AY19/20 DES+
 */
class Shop {
    /** List of servers. */
    private final List<Server> servers;

    /**
     * Create a new shop with a given number of human servers and Self-Checkout servers.
     * @param  numOfServers The number of human servers.
     * @param  noCheckOut The number of Self-CheckOut servers.
     * @param  noQueue The waiting queue size limit.
     */
    Shop(int numOfServers, int noCheckOut, int noQueue) {
        Stream<HumanServer> humanServer = Stream.iterate(1, i -> i + 1)
            .map(i -> new HumanServer(i, noQueue))
            .limit(numOfServers);
        int checkOutIndex = numOfServers + 1;
        Stream<SelfCheckOutServer> checkOutServer = Stream.iterate(checkOutIndex, i -> i + 1)
            .map(i -> new SelfCheckOutServer(i, noQueue))
            .limit(noCheckOut);
        this.servers = Stream.concat(humanServer, checkOutServer).collect(Collectors.toList());
    } 

    /**
     * Constructor for updated shop.
     * @param servers Take in an updated list of servers for the shop.
     */
    Shop(List<Server> servers) {
        this.servers = servers;
    }

    /**
     * Find a server matching the predicate.
     *
     * @param  predicate A predicate to match the server with.
     * @return Optional.empty if no server matches the predicate, or the
     *     optional of the server if a matching server is found.
     */
    public Optional<Server> find(Predicate<Server> predicate) {
        return this.servers.stream()
            .filter(predicate)
            .findFirst();
    }

    /**
     * Returns a new shop when one of the server changes its state.
     * @param  server An updated server state.
     * @return A new shop with updated server.
     */
    public Shop replace(Server server) {
        return new Shop(
            servers.stream()
            .map(s -> (s.equals(server) ? server : s))
            .collect(Collectors.toList())
            );
    }
  
    /**
     * Find all servers that match the given predicate.
     * @param  predicate A predicate to match the server(s) with.
     * @return A list of servers if they match the given predicate.
     */
    public List<Server> findAll(Predicate<Server> predicate) {
        return this.servers.stream()
            .filter(predicate)
            .collect(Collectors.toList());
    }

    /**
     * Return a string representation of this shop.
     * @return A string reprensetation of this shop.
     */
    public String toString() {
        return servers.toString();
    }
}
