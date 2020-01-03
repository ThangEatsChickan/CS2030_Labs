import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.function.Function;
import java.util.function.Predicate;
/**
 * A shop object maintains the list of servers and support queries
 * for server.
 *
 * @author weitsang
 * @author atharvjoshi
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
class Shop {
  /** List of servers. */
  private final List<Server> servers;

  /**
   * Create a new shop with a given number of servers.
   * @param numOfServers The number of servers.
   */
  Shop(int numOfServers) {
    this.servers = IntStream.rangeClosed(1, numOfServers)
    		.mapToObj(n -> new Server(n))
    		.collect(Collectors.toList());
    /*for (int i = 0; i < numOfServers; i++) {
      this.servers.add(new Server());
    }*/
  }
  
  Shop(List<Server> serverList){
     this.servers = serverList;
  }
  
  public Optional<Server> find(Predicate<Server> f) {
	  List<Server> serverList = new ArrayList<Server>(this.servers);
	  Optional<Server> server = serverList.stream().filter(f).findFirst();
	  return server;
  } //Predicate used filter while function used map.
  
  public Shop replace(Server s){
      List<Server> serverList = new ArrayList<Server>(this.servers);
      serverList.set(s.getID() - 1, s);
      return new Shop(serverList);
  }
  
  /**
   * Return the first idle server in the list.
   *
   * @return An idle server, or {@code null} if every server is busy.
   */
  public Optional<Server> findIdleServer() {
	Optional<Server> idleServer = this.servers.stream()
			.filter(server -> server.isIdle()).findFirst();
	if(idleServer.isPresent()) {
		return idleServer;
	} else {
    return Optional.empty();
	}
  }

  /**
   * Return the first server with no waiting customer.
   * @return A server with no waiting customer, or {@code null} is every
   *     server already has a waiting customer.
   */
  public Optional<Server> findServerWithNoWaitingCustomer() {
	Optional<Server> noWaiting = this.servers.stream().
			filter(server -> !server.hasWaitingCustomer()).findFirst();
	if(noWaiting.isPresent()) {
		return noWaiting;
	} else {
    return Optional.empty();
	}
  }

  /**
   * Return a string representation of this shop.
   * @return A string reprensetation of this shop.
   */
  public String toString() {
    return servers.toString();
  }
}
