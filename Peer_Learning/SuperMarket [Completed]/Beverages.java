class Beverages extends Product {
    protected final int foodType;
    public Beverages(String item, double price) {
        super(item,price);
        this.foodType = 1;
    }
    public int getFoodType() {
        return this.foodType;
    }
    public int compareTo(Beverages prod) {
        return this.getItem().compareTo(prod.getItem());
    }
    public String toString() {
        String s1 = new String();
        s1 = "Beverages: " + this.item + " " + String.format("$%.2f", this.price);
        return s1;
    }
}
