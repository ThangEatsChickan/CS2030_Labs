import java.util.Comparator;

class StateComparator implements Comparator<State> {

    @Override
    public int compare(State s1, State s2) {
        return s1.getState() - s2.getState();
    }
}
