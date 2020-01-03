class Admin extends User implements SuperRights, BasicRights{
    public Admin(String userName, int uID, Card card) {
        super(userName, uID, card);
    }
    public Customer rewardEventBonus(Customer cust, double prize) {
        double newValue = cust.card.getValue() + prize;
        return new Customer(cust.userName, cust.getID(), new Card(cust.card.getCardID(), newValue));
    }
}
