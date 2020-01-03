import java.util.PriorityQueue;
import java.lang.Comparable;
import java.util.Iterator;

class Monster implements Comparable<Monster>{
    public static PriorityQueue<Monster> monsters = new PriorityQueue<Monster>();
    public final String monsterName;
    public final int healthPoint;
    public Monster() {
        monsterName = "";
        healthPoint = 0;
    }
    private Monster(String name, int health) {
        monsterName = name;
        healthPoint = health;
    }

    public static Monster createNewMonster(String name, int health) {
        return new Monster(name, health);
    }

    public static void addNewMonster(String name, int health) {
        monsters.add(new Monster(name, health));
    }

    public static Monster getStrongestMonster() {
        PriorityQueue<Monster> copy = new PriorityQueue<Monster>(monsters);
        Iterator it = copy.iterator();
        Monster element = monsters.peek();
        while(it.hasNext()) {
            Monster nextElement = (Monster)it.next();
            if(nextElement.compareTo(element) > 0) {
                element = nextElement;
            }
        }
        return element;
    }

    @Override
    public int compareTo(Monster monst) {
        if(this.healthPoint > monst.healthPoint) {
            return 1;
        } else if (this.healthPoint == monst.healthPoint) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String s1 = new String();
        s1 = this.monsterName + " (Hit Points: " + this.healthPoint + ")";
        return s1;
    }
}
