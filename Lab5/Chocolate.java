class Chocolate extends Food { 
    public String chocolateType;

    public Chocolate(String brand, String type) {
        super(brand);
        this.chocolateType = type;
    }
    
    @Override
    public String toString() {
        String s1 = new String();
        s1 = super.foodBrand + " " + this.chocolateType + " chocolate";
        return s1;
    }
}
