class Meat extends Product {
    protected final int foodType;
    public Meat(String item, double price) {
        super(item,price);
        this.foodType = 4;
    }
    public int getFoodType() {
        return this.foodType;
    }
    public int compare(Meat prod) {
        return this.getItem().compareTo(prod.getItem());
    }
    public String toString() {
        String s1 = new String();
        s1 = "Meat: " + this.item + " " + String.format("$%.2f", this.price);
        return s1;
    }
}
