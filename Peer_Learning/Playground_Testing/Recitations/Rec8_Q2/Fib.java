import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.Collectors;
class Fib {
    static BigInteger findFibTerm(int n) {
        List<BigInteger> fibList = new ArrayList<>();
        fibList.add(BigInteger.ONE);
        fibList.add(BigInteger.ONE);
        while (fibList.size() < n) {
            generateFib(fibList);
        }
        return fibList.get(n-1);
     }

   static List<BigInteger> generateFib(List<BigInteger> al) {
       List <BigInteger> newList = new ArrayList<BigInteger>();
       newList = al.stream().map(x -> x + (x-1)).limit(al.size()).collect(Collectors.toList());
       return newList;
   }
   
}
