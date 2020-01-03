import java.util.Scanner;

class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String taxiType = sc.next();
        double distance = sc.nextDouble();
        Taxi newTaxi = new Taxi(taxiType);
        newTaxi = newTaxi.travel(distance);
        System.out.println(newTaxi);
    }
}
