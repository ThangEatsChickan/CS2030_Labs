class Card {
    private final double value;
    private final int cardID;
    public Card() {
        this.cardID = 0;
        this.value = 0;
    } 
    public Card(int cardID, double value) {
        this.cardID = cardID;
        this.value = value;
    }
    
    public int getCardID () { 
        return this.cardID;
    }

    public double getValue() {
        return this.value;
    }

    public String toString() {
        String s1 = new String();
        s1 =  String.format("\nCard Balance: %.2f", this.value);
        return s1;
    }
}
