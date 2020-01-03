import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.function.Predicate;

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

    public <U, R> Lazy<R> combine(Lazy<U> obj, BiFunction <T,U,R> biFunction) {
        return new Lazy<R>(() -> biFunction.apply(this.value, obj.value));
    }

    public Lazy<Boolean> test(Predicate<? super T> predicate) {
        return new Lazy<Boolean>(() -> predicate.test(this.value));
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if (obj instanceof Lazy) {
            Lazy newLazy = (Lazy) obj;
            if (this.get() == newLazy.get()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String s1 = new String();
        Optional<T> opt = Optional.ofNullable(this.value);
        s1 = opt.map(x -> x.toString()).orElse("?");
        return s1;
    }
}
