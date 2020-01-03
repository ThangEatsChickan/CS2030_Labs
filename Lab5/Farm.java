import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

class Farm {
    public ArrayList<Animal> animalList;
    public ArrayList<Food> foodList;
    public Farm() {
        this.animalList = new ArrayList<Animal>();
        this.foodList = new ArrayList<Food>();
    }
    public Farm(ArrayList<Animal> animalList, ArrayList<Food> foodList) {
        this.animalList = animalList;
        this.foodList = foodList;
    }

    public void runInstruction(String instruct) throws IllegalInstructionException{
        String [] parts = instruct.split(" ");
        if(instruct.contains("new cat")) {
            if(parts.length != 5) {
                throw new IllegalInstructionException("Too few arguments");
            } else {
                Cat newCat = new Cat(parts[2], Integer.parseInt(parts[3]), parts[4]);
                animalList.add(newCat);
                System.out.println(newCat + " was created");
            }
        } else if (instruct.contains("new dog")) {
            if(parts.length != 5) {
                throw new IllegalInstructionException("Too few arguments");
            } else {
                Dog newDog = new Dog(parts[2], Integer.parseInt(parts[3]), parts[4]);
                animalList.add(newDog);
                System.out.println(newDog + " was created");
            }
        } else if (instruct.contains("add chocolate")) {
            if(parts.length != 4) {
                throw new IllegalInstructionException("Too few arguments");
            } else {
                Chocolate newChocolate = new Chocolate(parts[2], parts[3]);
                foodList.add(newChocolate);
                System.out.println(newChocolate + " was added");
            }
        } else if (instruct.contains("add cheese")) {
            if(parts.length != 3) {
                throw new IllegalInstructionException("Too few arguments");
            } else {
                Cheese newCheese = new Cheese(parts[2]);
                foodList.add(newCheese);
                System.out.println(newCheese + " was added");
            }
        } else if (instruct.contains("add tuna")) {
            if(parts.length != 3) {
                throw new IllegalInstructionException("Too few arguments");
            } else {
                Tuna newTuna = new Tuna(parts[2]);
                foodList.add(newTuna);
                System.out.println(newTuna + " was added");
            }
        } else if (instruct.contains("eat")) {
            if(animalList.size() > 0) {
                for(int i = 0; i < animalList.size(); i++) {
                    Animal animal = animalList.get(i);
                    ArrayList<Food> clonedFoodList = new ArrayList<>(foodList);
                    int foodSize = clonedFoodList.size();
                    for(int j = 0; j < foodSize; j++) {
                        try {
                            animal.eat(clonedFoodList.get(j));
                            Iterator<Food> it = foodList.iterator();
                            while(it.hasNext()) {
                                Food food = it.next();
                                if(food.foodBrand.equals(clonedFoodList.get(j).foodBrand)) {
                                    it.remove();
                                    break;
                                }
                            }
                        } catch (CannotEatException ex) {
                        }
                    }
                }
            }
       }  else if (instruct.contains("greet")) {
            if (animalList.size() > 0) {
                for(int i =0; i < animalList.size(); i++) {
                    Animal animal = animalList.get(i);
                    animal.greet();
                }
            }
       } else {
            if (instruct.contains("new")) {
                throw new IllegalInstructionException(parts[1] + " is not a valid species");
            } else if (instruct.contains("add")) {
                throw new IllegalInstructionException(parts[1] + " is not a valid food type");
            } else {
                throw new IllegalInstructionException(parts[0] + " is not a valid instruction");
            }
        }
        Collections.sort(animalList);
    }
    
    @Override
    public String toString() {
        String s1 = new String();
        if (animalList.size() > 0) {
            s1 = "Animals:\n";
        }
        for(int i = 0; i < animalList.size(); i++) {
            s1 = s1 + animalList.get(i) + "\n";
        }
        if (foodList.size() > 0) {
            s1 = s1 + "\nFood:\n";
        }
        for(int j = 0; j < foodList.size(); j++) {
            if(j < foodList.size() - 1) {
                s1 = s1 + foodList.get(j) + "\n";
            } else {
                s1 = s1 + foodList.get(j);
            }
        }
        return s1;
    }
}            
