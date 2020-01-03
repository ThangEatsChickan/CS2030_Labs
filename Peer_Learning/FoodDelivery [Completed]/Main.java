import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noLocation = sc.nextInt();
        String location = new String();
        int x = 0;
        int y = 0;
        List <FoodDelivery> fdList = new ArrayList<>();
        while(x < noLocation) {
            List<DeliveryMan> newList = new ArrayList<>();
            location = sc.next();
            int noRiders = sc.nextInt();
            while(y < noRiders) {
                String first = sc.next();
                int id = sc.nextInt();
                String name = sc.next();
                if(first.equals("D")) {
                    DeliveryMan dm = new DeliveryMan(id, name);
                    newList.add(dm);
                }
                if(first.equals("R")) {
                    String date = sc.next();
                    Rider rm = new Rider(id,name,date);
                    newList.add(rm);
                }
                if(y == noRiders - 1) {
                    fdList.add(new FoodDelivery(newList, location));
                    break;
                }
                y++;
            }
            y = 0;
            if(x == noLocation - 1) {
                break;
            }
            x++;
        }
        for(int i = 0; i < fdList.size(); i++) {
            System.out.println(fdList.get(i));
        }
    }
}
