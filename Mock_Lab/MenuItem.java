import java.util.ArrayList;

interface MenuItem{
    public MenuItem getItem();
    public int getID();
    public String getFoodType();
    public String getFoodName();
    public int getPrice();
    public ArrayList<MenuItem> getList();
}
