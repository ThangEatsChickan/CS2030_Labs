enum State {
    ARRIVES(4),
    WAITS(3),
    SERVES(2),
    DONE(1),
    LEAVES(0);
    
    private final int state;
    
    private State (int state) {
        this.state = state;
    }
    
    public int getState() {
        return this.state;
    }
}
