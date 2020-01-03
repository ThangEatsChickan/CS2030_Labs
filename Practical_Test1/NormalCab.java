class NormalCab extends Car {

    public NormalCab(String carPlateText, int mins) {
        super(carPlateText, mins);
    }

    @Override
    public String toString() { 
        String s1 = new String();
        s1 = super.getCarPlateText() + " (" + super.getWaitingTime() + 
            " mins away)" + " NormalCab";
        return s1;
    }
}
