class Produce extends Product {
    protected final int foodType;
    public Produce(String item, double price) {
        super(item,price);
        this.foodType = 5;
    }
    public int getFoodType() {
        return this.foodType;
    }
    public int compare(Produce prod) {
        return this.getItem().compareTo(prod.getItem());
    }
    public String toString() {
        String s1 = new String();
        s1 = "Produce: " + this.item + " " + String.format("$%.2f", this.price);
        return s1;
    }
}
