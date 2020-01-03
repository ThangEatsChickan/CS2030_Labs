import java.util.function.Supplier; 

class Recursive<T> implements Compute<T> {
   private Supplier<Compute<T>> supplier;
   
   public Recursive(Supplier<Compute<T>> supplier) {
       this.supplier = supplier;
   }
   public boolean isRecursive() {
        return true;
   } 
   public Compute<T> recurse() {
        return supplier.get();
   }
   public T evaluate() {
        throw new IllegalStateException("Invalid evaluation in recursive case");
   }
}       
