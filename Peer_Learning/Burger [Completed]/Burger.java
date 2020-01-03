import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class Burger {
    private ArrayList <String> dataList = new ArrayList<>();
    private Burger(ArrayList<String> list) {
        this.dataList = list;
    }

    public static Burger of(String food) {
        ArrayList<String> objectList = new ArrayList<>();
        objectList.add("bun");
        objectList.add(food);
        return new Burger(objectList);
    }

    public Burger and(String food) {
        ArrayList<String> objectList = new ArrayList<>(this.dataList);
        if(!objectList.get(this.dataList.size() - 1).equals("bun")) {
            objectList.add(food);
        }
        return new Burger(objectList);
    }

    public Burger remove(String food) {
        ArrayList<String> objectList = new ArrayList<>(this.dataList);
        long occurence = objectList.stream().filter(e -> e.equals("bun")).count();
        if(occurence < 2) { 
            for(int i = 0; i < objectList.size(); i++) {
                if(objectList.get(i).equals(food)) {
                    objectList.remove(i);
                    break;
                }
            }
        }
        return new Burger(objectList);
    }

    public Burger done() {
        ArrayList<String> objectList = new ArrayList<>(this.dataList);
        long occurence = objectList.stream().filter(e -> e.equals("bun")).count();
        if(occurence < 2) {
            objectList.add("bun");
        }
        return new Burger(objectList);
    }

    @Override
    public String toString() {
        String s1 = new String();
        s1 = s1 + "\n";
        for(int i = this.dataList.size() - 1 ; i >= 0; --i) {
            s1 = s1 + this.dataList.get(i);
            if(i!=0) {
                s1 = s1 + "\n";
            }
        }
        return s1;
    }
}
