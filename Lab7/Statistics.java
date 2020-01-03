/**
 * This class stores stats about the simulation.
 * In particular, the average waiting time, the number of customers
 * who left, and the number of customers who were served, are stored.
 *
 * @author Ooi Wei Tsang
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
class Statistics {
  /** Sum of time spent waiting for all customers. */
  private final double totalWaitingTime;

  /** Total number of customers who were served. */
  private final int totalNumOfServedCustomers;

  /** Total number of customers who left without being served. */
  private final int totalNumOfLostCustomers;
   
  private Statistics stats;

  /**
   * Construct an Statistics object with 0 waiting time, 0
   * served customer, and 0 lost customer.
   * @return A new Statistics object 
   */
  public Statistics() {
    this.totalWaitingTime = 0;
    this.totalNumOfServedCustomers = 0;
    this.totalNumOfLostCustomers = 0;
  }
  
  private Statistics(double a, int b, int c) {
	this.totalWaitingTime = a;
	this.totalNumOfServedCustomers = b;
	this.totalNumOfLostCustomers = c;
  }

  /**
   * Mark that a customer is served.
   * @return A new Statistics object with updated stats
   */
  public Statistics serveOneCustomer() {
    int custCount = this.totalNumOfServedCustomers + 1;
    return new Statistics(this.totalWaitingTime, custCount, this.totalNumOfLostCustomers);
  }

  /**
   * Mark that a customer is lost.
   * @return A new Statistics object with updated stats
   */
  public Statistics looseOneCustomer() {
	int custCount = this.totalNumOfLostCustomers + 1;
    return new Statistics(this.totalWaitingTime, this.totalNumOfServedCustomers, custCount);
  }

  /**
   * Accumulate the waiting time of a customer.
   * @param time The time a customer waited.
   * @return A new Statistics object with updated stats
   */
  public Statistics recordWaitingTime(double time) {
    double waitingTime = this.totalWaitingTime + time;
    return new Statistics(waitingTime, this.totalNumOfServedCustomers, this.totalNumOfLostCustomers);
  }

  /**
   * Return a string representation of the staistics collected.
   * @return A string containing three numbers: the average
   *     waiting time, followed by the number of served customer,
   *     followed by the number of lost customer.
   */
  public String toString() {
    return String.format("[%.3f %d %d]",
        totalWaitingTime / totalNumOfServedCustomers,
        totalNumOfServedCustomers, totalNumOfLostCustomers);
  }
}
