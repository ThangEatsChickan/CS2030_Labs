Dog dog = new Dog("A", 2, "Woof!");
try {
dog.eat(new Cheese("Magnolia"));
} catch (CannotEatException e) {
System.err.println(e.getMessage());
}
try {
dog.eat(new Chocolate("Cadbury", "Dark"));
} catch (CannotEatException e) {
System.err.println(e.getMessage());
}
try {
dog.eat(new Tuna("Ayam"));
} catch (CannotEatException e) {
System.err.println(e.getMessage());
}
try {
dog.eat(new Cheese("Marigold"));
} catch (CannotEatException e) {
System.err.println(e.getMessage());
}
Cat cat = new Cat("Meowie", 1, "Black");
try {
cat.eat(new Tuna("Brands"));
} catch (CannotEatException e) {
System.err.println(e.getMessage());
}
try {
cat.eat(new Cheese("Magnolia"));
} catch (CannotEatException e) {
System.err.println(e.getMessage());
}
try {
cat.eat(new Chocolate("Hershey's", "Milk"));
} catch (CannotEatException e) {
System.err.println(e.getMessage()); 
}
/exit
