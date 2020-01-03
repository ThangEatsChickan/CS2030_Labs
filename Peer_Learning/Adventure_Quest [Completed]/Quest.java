import java.util.PriorityQueue;
import java.lang.Comparable;
import java.util.Iterator;

class Quest implements Comparable<Quest>{
    public static PriorityQueue<Quest> quests = new PriorityQueue<Quest>();
    public final int energyPt;
    public final int noMonster;
    public Quest() {
        energyPt = 0;
        noMonster = 0;
    }
    private Quest(int monster, int energy) {
        energyPt = energy;
        noMonster = monster;
    }

    public static Quest createNewQuest(int monster, int energy) {
        return new Quest(monster, energy);
    }

    public static void addNewQuest(int monster, int energy) {
        quests.add(new Quest(monster, energy));
    }

    public static Quest getEasiestQuest() {
        PriorityQueue<Quest> copy = new PriorityQueue<Quest>(quests);
        Iterator it = copy.iterator();
        Quest element = quests.peek();
        while(it.hasNext()) {
            Quest nextElement = (Quest)it.next();
            if(nextElement.compareTo(element) < 0) {
                element = nextElement;
            }
        }
        return element;
    }

    @Override
    public int compareTo(Quest quest) {
        if(this.energyPt < quest.energyPt) {
            return 1;
        } else if (this.energyPt == quest.energyPt) {
            if(this.noMonster > quest.noMonster) {
                return 1;
            } else if (this.noMonster == quest.noMonster) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String s1 = new String();
        s1 = "Quest: " + "\n      Energy Available: " + this.energyPt
           + "\n      Monsters: " + this.noMonster;
        return s1;
    }
}
