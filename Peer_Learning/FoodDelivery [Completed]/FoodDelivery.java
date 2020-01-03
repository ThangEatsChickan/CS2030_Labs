import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

class FoodDelivery {
    protected final List<? extends DeliveryMan> listDelivery;
    protected final String location;
    public FoodDelivery(List<? extends DeliveryMan> listDelivery, String location) { 
        this.listDelivery = listDelivery;
        this.location = location;
    }
    public String toString() {
        String s1 = new String();
        s1 = "Location : " + this.location + "\n";
        for(int i = 0; i < this.listDelivery.size(); i++) {
            s1 = s1 + this.listDelivery.get(i);
            if(i < this.listDelivery.size() - 1) {
                s1 = s1 + "\n";
            }

        }
        //this.listDelivery.stream().map(x-> x.toString()).forEach(System.out::println);
        return s1;
    }
}
        
