class Booking implements Comparable<Booking> {
    protected final ServiceInterface rideService;
    protected final Request request;
    protected final ServiceInterface rideServiceReq;
    protected final int waitingTime;
    protected final Car carObj;

    public Booking(Car carObj, ServiceInterface sObj, Request req) {
        this.carObj = carObj;
        this.rideService = sObj;
        this.request = req;
        this.rideServiceReq = sObj.computeFare(req);
        this.waitingTime = carObj.getWaitingTime();
    }
    
    private int getWaitingTime() {
        return this.waitingTime;
    }

    @Override
    public int compareTo(Booking secondBooking) {      
        if (this.rideServiceReq.getPrice() > 
            secondBooking.rideServiceReq.getPrice()) {
            return 1;
        } else if (this.rideServiceReq.getPrice() == 
              secondBooking.rideServiceReq.getPrice()) {
            if (this.getWaitingTime() > 
                secondBooking.getWaitingTime()) {
                return 1;
            } else if (this.getWaitingTime() < 
                secondBooking.getWaitingTime()) {
                return -1;
            } else {
                return 0;
            } 
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String s1 = new String();
        s1 = String.format("$%.2f", (this.rideServiceReq.getPrice()) / 100) + 
            " using " + this.carObj + " " + "(" +   
            this.rideService.getServiceType() + ")";
        return s1;
    }
}
