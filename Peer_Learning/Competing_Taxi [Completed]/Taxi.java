class Taxi {
    public String taxiType;
    public double totalPrice;
    public Taxi (String taxi) {
        this.taxiType = taxi;
        this.totalPrice = 0;
    }

    public Taxi (String taxi, double totalPrice) {
        this.taxiType = taxi;
        this.totalPrice = totalPrice;
    }

    public Taxi travel (double distance) {
        double bookingFare = 0;
        double distanceRate = 0;
        double totalPrice = 0;
        if (this.taxiType.contains("Grab")){
            bookingFare = 3.2;
            distanceRate = 1.5;
        } else {
            bookingFare = 5;
            distanceRate = 0.9;
        }
        totalPrice = bookingFare + (distanceRate * distance);
        return new Taxi(this.taxiType, totalPrice);
    }        

    public String toString() {
        String s1 = new String();
        s1 = taxiType + " with total fare $" + String.format("%.2f", this.totalPrice);
        return s1;
    } 
}
