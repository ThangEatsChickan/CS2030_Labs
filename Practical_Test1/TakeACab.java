class TakeACab implements ServiceInterface {
    protected final double price;
    protected final String serviceType;

    public TakeACab() {
        this.price = 0;
        this.serviceType = "TakeACab";
    }

    public TakeACab(double totalCost) { 
        this.price = totalCost;
        this.serviceType = "TakeACab";
    }

    public double getPrice() {
        return this.price;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public ServiceInterface computeFare(Request req) {
        double totalCost = 0;
        double bookingfee = 200;
        totalCost = req.mileage * 33;
        totalCost = totalCost + bookingfee;
        return new TakeACab(totalCost);
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
