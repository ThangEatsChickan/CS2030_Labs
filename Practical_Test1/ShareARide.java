class ShareARide implements ServiceInterface {
    protected final double price;
    protected final String serviceType;

    public ShareARide() {
        this.price = 0;
        this.serviceType = "ShareARide";
    }

    public ShareARide(double totalCost) { 
        this.price = totalCost;
        this.serviceType = "ShareARide";
    }

    public double getPrice() {
        return this.price;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public ServiceInterface computeFare(Request req) {
        double totalCost = 0;
        int roundDown = 0;
        totalCost = req.mileage * 50;
        if (req.time >= 600 && req.time <= 900) {
            totalCost = totalCost + 500;
        }
        totalCost = totalCost / req.passenger;
        roundDown = (int) totalCost;
        totalCost = roundDown;
        return new ShareARide(totalCost);
    }

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
