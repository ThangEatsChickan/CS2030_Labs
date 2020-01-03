import java.util.Scanner;
    class Main {
        static long summer(long n) {
        Compute<Long> result = sum(n, 0);
        while (result.isRecursive()) {
        result = result.recurse();
        }
        return result.evaluate();
        }
        static Compute<Long> sum(long n, long s) {
        if (n == 0) {
            return new Base<>(() -> s);
        } else {
            return new Recursive<>(() -> fact(n - 1, n + s));
        }
        }
     static long factorial(long n) 
      { 
         if (n == 0) 
           return 1; 
          
         return n*factorial(n-1); 
      } 
    public static void main(String[] args) {
        System.out.println(factorial(new Scanner(System.in).nextLong()));
    }
}
