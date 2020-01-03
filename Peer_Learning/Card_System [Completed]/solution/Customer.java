class Customer extends User implements BasicRights{
    public Customer(String userName, int uID, Card card) {
        super(userName, uID, card);
    }

    @Override
    public Customer doTopUp(double topUp) {
        double newValue = this.card.getValue() + topUp;
        return new Customer(this.userName, this.uID, new Card(this.card.getCardID(), newValue));
    }
}
