class Cat extends Animal {
    public final String color;
    public boolean isLazy;
    public int eatCount;
    public String log;

    public Cat(String name, int appetite, String color) {
        super(name, appetite);
        this.color = color;
        this.eatCount = 0;
        this.isLazy = false;
        this.log = ""; 
    }
   
    public Cat(String name, int appetite, String color, boolean isLazy, String log) {
        super(name, appetite);
        this.color = color;
        this.isLazy = isLazy;
        this.log = log; 
    }
    
    public void eat(Food food) throws CannotEatException {
        if (food instanceof Cheese) {
            throw new CannotEatException(super.name + "(" + this.color + ")" + " cannot eat " + food);
        } else if (this.eatCount >= super.appetite) {
            throw new CannotEatException(super.name + "(" + this.color + ")" + " cannot eat " + food + 
                " as it is full");
        } else {
            System.out.println(super.name + "(" + this.color + ")" + " eats " + food);
        }
        this.eatCount++;
    }
 
    @Override
    public void greet(){
        String s1 = new String();
        s1 = super.name + "(" + this.color + ")";
        if(this.isLazy == false) {
            this.isLazy = true; //Change Lazy mode
            s1 = s1 + " says meow and gets lazy";
        } else {
            this.isLazy = false; //Change Lazy mode
            s1 = s1 + " is lazy";
        }
        System.out.println(s1);
    }
   
    public String toString() {
        String s1 = new String();
        s1 = super.name + "(" + this.color + ")";
        return s1;
    }
}
