import java.util.ArrayList;

class Order extends Menu {
    protected ArrayList<MenuItem> orderList = new ArrayList<MenuItem>();
    protected int foodCount = 0;

    public Order(Menu menuObj) {
        super(menuObj.items, menuObj.foodCount);
    }

    public Order(ArrayList<MenuItem> orderList, int foodCount) {
        this.orderList = orderList;
        this.foodCount = foodCount;
    }

    public Order add(int [] orderID) {
        for (int i = 0; i < orderID.length; i++) {
            this.orderList.add(super.items.get(orderID[i]));
        } 
        return new Order(this.orderList, orderID.length);
    }

    @Override
    public String toString() {
        String s1 = new String();
        String s2 = new String();
        int totalSum = 0;
        for (int j = 0; j < this.orderList.size(); j++) {
            totalSum = totalSum + this.orderList.get(j).getPrice();
            if (this.orderList.get(j) instanceof Combo) {
                s1 = s1 + "\n" + "#" + this.orderList.get(j).getID() + " " + 
                this.orderList.get(j).getFoodType() + ": " + 
                this.orderList.get(j).getFoodName() + 
                    " (" + this.orderList.get(j).getPrice() + ")";
                for (int z = 0; z < this.orderList.get(j).getList().size(); z++) {
                    s1 = s1 + String.format("\n   #" + 
                    this.orderList.get(j).getList().get(z).getID()
                    + " " + this.orderList.get(j).getList().get(z).getFoodType() + ": " + 
                    this.orderList.get(j).getList().get(z).getFoodName() + " (" +
                    this.orderList.get(j).getList().get(z).getPrice() + ")");
                    if (this.orderList.get(j).getList().get(z).getFoodType().equals("Combo")) {
                        for (int a = 0; a < this.orderList.get(j).getList().get(z).getList().size(); a++) {
                            s1 = s1 + String.format("\n   #" +
                            this.orderList.get(j).getList().get(z).getList().get(a).getID()
                            + " " + this.orderList.get(j).getList().get(z).getList().get(a).getFoodType() + ": " + 
                            this.orderList.get(j).getList().get(z).getList().get(a).getFoodName() + " (" +
                            this.orderList.get(j).getList().get(z).getList().get(a).getPrice() + ")");
                            if (this.orderList.get(j).getList().get(z).getList().get(a).getFoodType().equals("Combo")) {
                                for(int b = 0; b < this.orderList.get(j).getList().get(z).getList().get(a).getList().size(); b++) { 
                                s1 = s1 + String.format("\n   #" +
                                this.orderList.get(j).getList().get(z).getList().get(a).getList().get(b).getID()
                                + " " + this.orderList.get(j).getList().get(z).getList().get(a).getList().get(b).getFoodType() + ": " + 
                                this.orderList.get(j).getList().get(z).getList().get(a).getList().get(b).getFoodName() + " (" +
                                this.orderList.get(j).getList().get(z).getList().get(a).getList().get(b).getPrice() + ")");
                                }
                            }
                        }
                    }
                }
            
            } else {
                s1 = s1 + "\n" + "#" + this.orderList.get(j).getID() + " " + 
                this.orderList.get(j).getFoodType() + ": " + 
                this.orderList.get(j).getFoodName() + 
                    " (" + this.orderList.get(j).getPrice() + ")";
            }
            if (j == this.orderList.size() - 1) {
                s2 = s2 + "\n" + "Total: " + totalSum;
            }
        }
        return s1 + s2;
    }
}    
