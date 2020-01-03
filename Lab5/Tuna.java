class Tuna extends Food { 
    public Tuna(String brand) {
        super(brand);
    }
    
    @Override
    public String toString() {
        String s1 = new String();
        s1 = super.foodBrand + " tuna";
        return s1;
    }
}
