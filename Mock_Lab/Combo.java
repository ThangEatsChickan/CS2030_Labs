import java.util.ArrayList;

class Combo implements MenuItem {
    protected final int comboID;
    protected final String comboType;
    protected final String comboName;
    protected int totalSum;
    protected final Combo comboObj;
    protected ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Combo(int comboID, String comboType, String comboName,
                 ArrayList<MenuItem> menuItems, int price) {
        this.comboID = comboID;
        this.comboType = comboType;
        this.comboName = comboName;
        this.menuItems = menuItems;
        this.totalSum = price;
        this.comboObj = null;
    }

    public Combo(Combo comboObj) {
        this.comboID = comboObj.comboID;
        this.comboType = comboObj.comboType;
        this.comboName = comboObj.comboName;
        this.menuItems = comboObj.menuItems;
        this.totalSum = comboObj.totalSum;
        this.comboObj = comboObj;
    }

    public MenuItem getItem() {
        return this.comboObj;
    }

    public int getID() {
        return this.comboID;
    }

    public String getFoodType() {
        return this.comboType;
    }

    public String getFoodName() {
        return this.comboName;
    }

    public int getPrice() {
        return this.totalSum;
    }

    public ArrayList<MenuItem> getList() {
        return this.menuItems;
    } 
}
