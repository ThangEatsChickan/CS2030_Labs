import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class Main{
    public static void main(String [] args){
    Scanner sc = new Scanner(System.in);
    String addHolder = new String();
    String foodType = new String();
    String foodName = new String();
    List <Integer> mealItems = new ArrayList<Integer>();
    List <Integer> orderItems = new ArrayList<Integer>();
    Menu menu = new Menu();
    int x = 0;
    while (sc.next().equals("add")){
        foodType = sc.next();
        if(foodType.equals("Combo")){
        mealItems.clear();
        foodName = sc.next();
        while (sc.hasNextInt()){
            mealItems.add(sc.nextInt());
        }
        menu = menu.add(foodType, foodName, mealItems);
		}
		else{
		    foodName = sc.next();
		    int foodPrice = sc.nextInt();
		    menu = menu.add(foodType, foodName, foodPrice);
		    }
		    
		}
	    menu.print();
	    while (sc.hasNextInt() == true){
                x = sc.nextInt();
		orderItems.add(x);
		if(sc.hasNextInt() == false){
		    Order o = new Order(menu).add(convertInteger(orderItems));
	            System.out.println("\n--- Order ---");
	            System.out.println(o);
		    break;
		}
	   }
	}
	
	public static int[] convertInteger(List<Integer> orderItem){
	    int [] intArray = new int[orderItem.size()];
	    Iterator<Integer> iterator = orderItem.iterator();
	    for(int i =0; i<intArray.length; i++){
	        intArray[i] = iterator.next().intValue();
	    }
            return intArray;
	}	    
}

