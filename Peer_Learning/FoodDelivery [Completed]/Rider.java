class Rider extends DeliveryMan {
    protected final String date;
    public Rider(int id, String name, String date) {
        super(id, name);
        this.date = date;
    }
    public Rider changeName(String newName){
        return new Rider(this.id, newName, this.date);
    }
    public Rider changeDate(String newDate) {
        return new Rider(this.id, this.name, newDate);
    }
    public String toString() {
        String s1 = new String();
        s1 = "Rider " + super.id + " : " + super.name + " (" + this.date + ")";
        return s1;
    }
}
