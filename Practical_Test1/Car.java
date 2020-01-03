class Car {
    protected final String carPlateText;
    protected final int waitingTime;
    
    public Car() {
        this.carPlateText = "";
        this.waitingTime = 0;
    }

    public Car(String carPlateText, int mins) {
        this.carPlateText = carPlateText;
        this.waitingTime = mins;
    }
    
    public String getCarPlateText() {
        return this.carPlateText;
    }
   
    public int getWaitingTime() {
        return this.waitingTime;
    }
    
    public String tostring() { 
        String s1 = new String();
        s1 = this.getCarPlateText() + " (" + this.getWaitingTime() + 
            " mins away)" + " car";
        return s1;
    }
}
    
