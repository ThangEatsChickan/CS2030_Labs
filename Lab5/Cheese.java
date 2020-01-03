class Cheese extends Food { 
    public Cheese(String brand) {
        super(brand);
    }
    
    @Override
    public String toString() {
        String s1 = new String();
        s1 = super.foodBrand + " cheese";
        return s1;
    }
}
