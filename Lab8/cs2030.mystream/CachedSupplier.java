package cs2030.mystream;

import java.util.function.Supplier;

public class CachedSupplier<T> {
    private Supplier<? extends T> source;
    private T value;
    private boolean isCached;

    protected CachedSupplier(Supplier<? extends T> source) {
        this.source = source;
        this.isCached = false;
    }

    protected T get() {
        if(this.isCached == false) {
            this.value = this.source.get();
            this.isCached = true;
        }
        return this.value;
    }
}
