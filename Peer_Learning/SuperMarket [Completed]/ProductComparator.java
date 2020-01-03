import java.util.Comparator;

class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product prod1, Product prod2) {
        if(prod1.getFoodType() > prod2.getFoodType()) {
            return 1;
        } else if(prod1.getFoodType() == prod2.getFoodType()) {
            return prod1.getItem().compareTo(prod2.getItem());
        } else {
            return -1;
        }
    }
}
