import java.util.function.Supplier; 

class Base<T> implements Compute<T> {
   private Supplier<T> supplier;
   
   public Base(Supplier<T> supplier) {
       this.supplier = supplier;
   }
   public boolean isRecursive() {
        return false;
   } 
   public Compute<T> recurse() {
        throw new IllegalStateException("Invalid recursive call in base case");
   }
   public T evaluate() {
        return supplier.get();
   }
}       
