package cs2030.mystream;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class EmptyList<T> extends InfiniteListImpl<T>{
    EmptyList(){
        super(()->Optional.empty(),()->new EmptyList<T>());
    }

    public <R> InfiniteListImpl<R> map(Function<? super T,? extends R> mapper) {
        return new EmptyList<R>();
    }

    public EmptyList<T> filter(Predicate<? super T> predicate) {
        return new EmptyList<T>();
    }
    @Override
    public boolean isEmptyList() {
        return true;
    }
}
