import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.Function;

class Lazy<T> {
    T value;
    Supplier<T> data;
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
        if (this.isCached == false) {
            this.value = this.data.get();
            this.isCached = true;
        }
        return this.value;
    }

    @Override
    public String toString() {
        String s1 = new String();
        Optional<T> opt = Optional.ofNullable(this.value);
        s1 = opt.map(x -> x.toString()).orElse("?");
        return s1;
    }
}
