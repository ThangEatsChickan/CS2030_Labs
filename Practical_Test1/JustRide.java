class JustRide implements ServiceInterface {
    protected final double price;
    protected final String serviceType;

    public JustRide() {
        this.price = 0;
        this.serviceType = "JustRide";
    }

    public JustRide(double totalCost) { 
        this.price = totalCost;
        this.serviceType = "JustRide";
    }

    public double getPrice() {
        return this.price;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public ServiceInterface computeFare(Request req) {
        double totalCost = 0;
        totalCost = req.mileage * 22;
        if (req.time >= 600 && req.time <= 900) {
            totalCost = totalCost + 500;
        }
        return new JustRide(totalCost);
    }

    @Override
    public String toString() {
        String s1 = new String();
        if (this.price == 0) {
            s1 = this.serviceType;
        } else {
            s1 = String.format("%.0f",this.price);
        }
        return s1;
    }
}
