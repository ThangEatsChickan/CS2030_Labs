import java.util.Scanner;

class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        long one = sc.nextLong();
        System.out.println(summer(one));
    }

    static long factorial(long n) 
    { 
        if (n == 0) 
          return 1;       
          return n*factorial(n-1); 
    } 
    static Compute<Long> sum(long n, long s) {
        if (n == 0) {
            return new Base<>(() -> s);
        }  else {
            return new Recursive<>(() -> sum(n - 1, n + s));
        }
    }
    static Compute<Long> fact(long n, long s) {
        if (n == 0) {
            return new Base<>(() -> s);
        }  else {
            return new Recursive<>(() -> fact(n - 1, n * s));
        }
    }
    static long summer(long n) {
        Compute<Long> result = fact(n, 1);
        while (result.isRecursive()) {
            result = result.recurse();
        }
        return result.evaluate();
    }
}

