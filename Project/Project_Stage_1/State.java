/** 
 * This enumeration defines the various state in which the customer 
 * will go into.
 *
 * @author  Song Soon Wee
 * @version 1.0
 * @since   2019-10-05
 */
enum State {
    ARRIVES(4),
    WAITS(3),
    SERVES(2),
    DONE(1),
    LEAVES(0);
    
    private final int state;
    /**
     * Sets the state of this particular instance to a value.
     *
     * @param state value to set the current instance state.
     */
    private State(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }
}
