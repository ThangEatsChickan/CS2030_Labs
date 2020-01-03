import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.stream.Stream;

class Lazy<T> {

    T value;
    Supplier <T> data;
    boolean isCached = false;

    public Lazy(T v) {
        this.value = v;
        this.isCached = true;
    }

    public Lazy(Supplier<T> s) {
        this.data = s;
        this.isCached = false;
    }

    public static <T> Lazy<T> of (T v) {
        return new Lazy<T>(v);
    }

    public static <T> Lazy<T> of (Supplier<T> s) {
        return new Lazy<T>(s);
    }

    public T get() {
        if(this.isCached == false) {
            this.value = this.data.get();
            this.isCached = true;
        }
        return this.value;
    }

    public <R> Lazy<R> map (Function <? super T, ? extends R> mapper) {
        return new Lazy<R>(() -> mapper.apply(this.get()));
    }

    public <R> Lazy<R> flatMap(Function <? super T, Lazy<R>> mapper) {
        return new Lazy<R>(() -> mapper.apply(this.get()).get());
    }

    @Override
    public String toString() {
        String s1 = new String();
        Optional<T> opt = Optional.ofNullable(this.value);
        s1 = opt.map(x -> x.toString()).orElse("?");
        return s1;
    }
}
