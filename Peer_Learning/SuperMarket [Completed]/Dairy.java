class Dairy extends Product {
    protected final int foodType;
    public Dairy(String item, double price) {
        super(item,price);
        this.foodType = 3;
    }
    public int getFoodType() {
        return this.foodType;
    }
    public int compare(Dairy prod) {
        return this.getItem().compareTo(prod.getItem());
    }
    public String toString() {
        String s1 = new String();
        s1 = "Dairy: " + this.item + " " + String.format("$%.2f", this.price);
        return s1;
    }
}
