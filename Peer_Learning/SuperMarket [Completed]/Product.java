abstract class Product {
    protected final String item;
    protected final double price;
    protected final int foodType;
    public Product(String item, double price) {
        this.item = item;
        this.price = price;
        this.foodType = 0;
    }
    abstract int getFoodType();
    
    public double getPrice() {
        return this.price;
    }

    public String getItem() {
        return this.item;
    }
}
