class Dog extends Animal {
    public final String sound;
    public int soundCount;
    public int eatCount;
    public String log;

    public Dog(String name, int appetite, String sound) {
        super(name, appetite);
        this.sound = sound;
        this.eatCount = 0;
        this.soundCount = 1;
        this.log = ""; 
    }
   
    public Dog(String name, int appetite, String sound, int soundCount, String log) {
        super(name, appetite);
        this.sound = sound;
        this.soundCount = soundCount;
        this.log = log; 
    }
 
    public void eat(Food food) throws CannotEatException {
        if (food instanceof Chocolate) {
            throw new CannotEatException(super.name + " cannot eat " + food);
        } else if (this.eatCount >= super.appetite) {
            throw new CannotEatException(super.name + " cannot eat " + food + 
                " as it is full");
        } else {
            System.out.println(super.name + " eats " + food);
        }
        this.eatCount++;
    } 
    
    @Override
    public void greet(){
        String s1 = new String();
        String tempString = new String();
        s1 = super.name + " says ";
        for(int i = 0; i < this.soundCount; i++) {
            tempString = tempString + this.sound;
        }
        this.soundCount++;
        System.out.println(s1 + tempString);
    }
   
    public String toString() {
        String s1 = new String();
        s1 = super.name;
        return s1;
    }
}
