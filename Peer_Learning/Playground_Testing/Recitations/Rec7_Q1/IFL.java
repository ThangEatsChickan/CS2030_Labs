import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
public interface IFL<T> {
    public static <T> IFL<T> iterate(T seed, Predicate<T> pred, Function<T, T> next) {
        return new IFLImpl<T>() {
            T element = seed;
            boolean empty;
            Function<T, T> func = x -> {
            func = next;
            return element;
        };
        Optional<T> get() {
            element = func.apply(element);
            if(pred.test(element)) {
                empty = false;
                return Optional.of(element);
            } else {
                empty = true;
                return Optional.empty();
            }
            //return Optional.of(element);
        }
      
        boolean isEmpty() {
            return this.empty;
        }
        };
    }
    public <R> IFL<R> map(Function<T, R> mapper);
    public void forEach(Consumer<T> action);

    public static <T> IFL<T> concat(IFL<T> list1, IFL<T> list2) {
        return new IFLImpl<T>() {
            boolean emptyStream = false;
            Optional<T> get() {
                IFLImpl<T> l1 = (IFLImpl<T>)list1;
                IFLImpl<T> l2 = (IFLImpl<T>)list2;
                if (!l1.isEmptyStream()) {
                    return l1.get();
                } 
                if (!l2.isEmptyStream()) {
                    return l2.get();
                }
                emptyStream = true;
                return Optional.empty();
            }
            boolean isEmptyStream() {
                return emptyStream;
            }
        };
    }    
}

