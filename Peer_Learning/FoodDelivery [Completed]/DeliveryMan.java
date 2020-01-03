class DeliveryMan {
    protected final int id;
    protected final String name;
    public DeliveryMan(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DeliveryMan changeName(String name) {
         return new DeliveryMan(this.id, name);
    }

    public String toString() {
        String s1 = new String();
        s1 = "Delivery Man " + this.id + " :  " + this.name;
        return s1;
    }

}
