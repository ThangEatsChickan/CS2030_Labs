import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;

abstract class IFLImpl<T> implements IFL<T> {
    boolean isEmpty = false;
    public <R> IFL<R> map(Function<T, R> mapper) {
        return new IFLImpl<R>() {
        Optional<R> get() {
            return IFLImpl.this.get().map(mapper);
        }
   };}
    public void forEach(Consumer<T> action) {
        Optional<T> curr = get();
        while (true) {
            curr.ifPresent(action);
            curr = get();
        }
    }
    abstract Optional<T> get();
    boolean isEmptyStream() {
        return false;
    };
}
