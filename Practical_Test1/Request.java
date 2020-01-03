class Request {
    protected final int mileage;
    protected final int passenger;
    protected final int time;

    public Request(int mileage, int passenger, int time) {
        this.mileage = mileage;
        this.passenger = passenger;
        this.time = time;
    }

    public String toString() {
        String s1 = new String();
        s1 = this.mileage + "km for " + this.passenger + "pax @ " + 
            this.time + "hrs";
        return s1;
    }
}
