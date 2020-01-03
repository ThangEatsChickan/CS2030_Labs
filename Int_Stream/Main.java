import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> intData = new ArrayList<>();
        //int input = sc.nextInt();
        if (sc.hasNextInt()) {
        	intData = sc.tokens().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        }
        int[] intArray = intData.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(countRepeats(intArray));
    }
    
    static boolean isPerfect(int n) { 
       if(n==1){
            return false;
        } else {
        return LongStream.rangeClosed(2, (long) Math.sqrt(n))
            .reduce(1, (sum, test) -> n % test == 0 ? sum + test + (n / test) : sum) == n;
        }
    }

    static boolean isSquare(int n) {
        IntStream q = IntStream.rangeClosed(1,n).map(((int x) -> {return x*x;}));
        return q.anyMatch(x -> (x==n)); //Check if match
    }
    
    static long countRepeats(int[] array) {
        List <Integer> listItem = new ArrayList<>();
        listItem = Arrays.stream(array).boxed().collect(Collectors.toList());
        long counter = 0;
        Optional<Integer> intList = listItem.stream().reduce((a,b) -> a==b ? Optional.of(b) : Optional.empty());
        return counter;
    	/*IntStream intStream1 = Arrays.stream(array);
    	Stream.of(array).reduce((a, b) -> {
    		if(a==b) {
    			return b;
    		}
    	});*/
    }
}

