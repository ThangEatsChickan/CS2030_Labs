import java.util.concurrent.CompletableFuture;
import java.lang.InterruptedException;

class A {
    private final int x;
    A() {
        this(0);
    }
    private A(int x) {
        this.x = x;
    }
    void sleep() {
        System.out.println(Thread.currentThread().getName() + " " + x);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
    A incr() {
        sleep();
        return new A(this.x + 1);
    }
    A decr() {
        sleep();
        if (x < 0) {
            throw new IllegalStateException();
        }
        return new A(this.x - 1);
    }
   
    static CompletableFuture<A> foo(A a) {
        CompletableFuture<A> future = CompletableFuture.supplyAsync(() -> 
            a.incr()).thenApply(b -> b.decr());
        return future;
    }

    static CompletableFuture<A> bar(A a) {
       CompletableFuture<A> future = CompletableFuture.supplyAsync(() -> a.incr()); 
       return future;
    }
        
    public String toString() {
        return "" + x;
    }
}
