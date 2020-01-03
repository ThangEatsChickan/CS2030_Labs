class Canned extends Product {
    protected final int foodType;
    public Canned(String item, double price) {
        super(item,price);
        this.foodType = 2;
    }
    public int getFoodType() {
        return this.foodType;
    }
    public int compareTo(Canned prod) {
        return this.getItem().compareTo(prod.getItem());
    }
    public String toString() {
        String s1 = new String();
        s1 = "Canned: " + this.item + " " + String.format("$%.2f", this.price);
        return s1;
    }
}
