class Animal implements Comparable<Animal>{
    public String name;
    public int appetite;

    public Animal(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }
    
    public void greet() {
        System.out.println("Input greet method here");
    }
  
    public void eat(Food food) throws CannotEatException{
        System.out.println("Input eat method here");
    }
   
    public int compareTo(Animal animal) {
        return this.name.compareTo(animal.name);
    }
}
