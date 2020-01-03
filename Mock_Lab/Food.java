import java.util.ArrayList;

class Food implements MenuItem {
    protected final int foodID;
    protected final String foodType;
    protected final String foodName;
    protected final int price;
    protected final Food foodObj;

    public Food(int foodID, String foodType, String foodName, int price) {
        this.foodID = foodID;
        this.foodType = foodType;
        this.foodName = foodName;
        this.price = price;
        this.foodObj = null;
    }

    public Food(Food foodObj) {
        this.foodID = foodObj.foodID;
        this.foodType = foodObj.foodType;
        this.foodName = foodObj.foodName;
        this.price = foodObj.price;
        this.foodObj = foodObj;
    }

    public MenuItem getItem() {
        return this.foodObj;
    }
    
    public int getID() {
        return this.foodID;
    }
    
    public String getFoodType() {
        return this.foodType;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public int getPrice() {
        return this.price;
    }

    public ArrayList<MenuItem> getList() {
        return null; 
    }
}
