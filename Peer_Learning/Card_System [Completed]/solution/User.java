class User implements BasicRights{
    protected final String userName;
    protected final int uID;
    protected final Card card;
    public User(String userName, int uID, Card card) {
        this.userName = userName;
        this.uID = uID;
        this.card = card;
    }
    
    public int getID() { 
        return this.uID;
    }

    public Card checkBalance() {
        return this.card;
    }
    
    public User doTopUp(double topUp) {
        double newValue = this.card.getValue() + topUp;
        return new User(this.userName, this.uID, new Card(this.card.getCardID(), newValue));
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;  
        } else if (obj instanceof User) {
            User use = (User)obj;
            if(this.getID() == use.getID()) {
                return true;
            } else {
                return false; 
            }
        } else {
             return false;
        }
    }
    @Override
    public String toString() {
        String s1 = new String();
        s1 = "Username: " + this.userName + " (" + this.uID + ")\n" +
             this.card;
        return s1;
    }
}


