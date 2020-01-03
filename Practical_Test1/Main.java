import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Main {
    
    public static void main(String [] args) {
        getUserInput();
    }
    
    public static void getUserInput() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Booking> bookingList = new ArrayList<>();
        int mileage = 0;
        int passenger = 0;
        int time = 0;
        String serviceType = new String();
        String plateNo = new String();
        int waitingTime = 0;
        mileage = sc.nextInt();
        passenger = sc.nextInt();
        time = sc.nextInt();
        Request req = new Request(mileage, passenger, time);
        while (sc.hasNext() == true) {
            serviceType = sc.next();
            plateNo = sc.next();
            waitingTime = sc.nextInt();
            Car carObj = new Car();
            if (serviceType.equals("NormalCab")) {
                carObj = new NormalCab(plateNo, waitingTime);
                Booking b1 = new Booking(carObj, new TakeACab(), req);
                bookingList.add(b1);
            } else if (serviceType.equals("PrivateCar")) {
                carObj = new PrivateCar(plateNo, waitingTime);
                Booking b1 = new Booking(carObj, new ShareARide(), req);
                bookingList.add(b1);
            } else {
                break;
            }
            Booking b2 = new Booking(carObj, new JustRide(), req);
            bookingList.add(b2);
        }
        computeList(bookingList);
    }
   
    public static void computeList(ArrayList<Booking> bookingList) {
        Collections.sort(bookingList);
        for (int i = 0; i < bookingList.size(); i++) {
            System.out.println(bookingList.get(i));
        }
    }
}
