import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class Shop {
    static List<Product> productList = new ArrayList<>();
    public Shop() {
    }
    public static void addItem(Product prod) {
        productList.add(prod);
    }
    public String toString() {
        String s1 = new String();
        if(productList.size() > 0) {
            Collections.sort(productList, new ProductComparator());
            s1 =  s1 + "\n-List of Items in Supermarket-";
            for(int i = 0; i < productList.size(); i++) {
                s1 = s1 + "\n" + productList.get(i);
            }
            s1 = s1 + "\n";
        } 
        return s1;
    }
} 
