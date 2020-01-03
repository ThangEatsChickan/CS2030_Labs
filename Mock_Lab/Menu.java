import java.util.List;
import java.util.ArrayList;

class Menu {
    protected ArrayList<MenuItem> items = new ArrayList<MenuItem>();
    protected ArrayList<String> outputs = new ArrayList<String>();
    protected int foodCount = 0;

    public Menu() {
        this.foodCount = foodCount;
        this.items = items;
    }

    public Menu(ArrayList<MenuItem> newItems, int foodCount) {
        this.foodCount = foodCount;
        this.items = newItems;
    }

    public Menu add(String foodType, String foodName, int price) {
        Food foodItem = new Food(foodCount, foodType, foodName, price);
        this.items.add(foodItem); 
        this.foodCount = this.foodCount + 1;
        return new Menu(this.items, this.foodCount);
    }

    public Menu add(String comboType, String comboName, List<Integer> input) {
        ArrayList<MenuItem> selectedItems = new ArrayList<MenuItem>();
        int totalSum = -50;
        int inputValue = 0;
        for (int i = 0; i < input.size(); i++) {
            inputValue = input.get(i).intValue();
            for (int j = 0; j < this.items.size(); j++) {
                if (this.items.get(j).getID() == inputValue) {
                    totalSum = totalSum + this.items.get(j).getPrice();
                    selectedItems.add(this.items.get(j));
                    break;
	        }
                else{
	            continue;
                }	
	    }
	}	
        Combo c = new Combo(this.foodCount, comboType, comboName, selectedItems, totalSum);
        this.items.add(c);
        this.foodCount = this.foodCount + 1;
        return new Menu(this.items, this.foodCount);	
    }
    public void print(){
	String outputString = new String();
	String comparison = new String();
	String temp = new String();
	for (int j=0 ; j<this.items.size(); j++){ //Find unique types of food and allign them according to input
	    if(j==0){
                comparison = this.items.get(j).getFoodType();
		this.outputs.add(comparison);
	    }
	    else if(comparison.equals(this.items.get(j).getFoodType())){
                continue;
	    }
	    else{
		for(int k=0; k < this.outputs.size(); k++){
		    if(this.items.get(j).getFoodType().equals(this.outputs.get(k))){
		        break;
		    }
		    else if (k == this.outputs.size() - 1){
		        if(this.outputs.get(k).equals("Combo")){
			    comparison = this.items.get(j).getFoodType();
			    temp = this.outputs.get(k);
			    this.outputs.add(temp);
			    this.outputs.set(k, comparison);
			    break;
			}
			else{
		       	    comparison = this.items.get(j).getFoodType();
                            this.outputs.add(comparison);
			}
		    }
		    else{
			continue;
		    }
	        }
	    }
	}
       for (int i=0; i < this.outputs.size(); i++){
           for(int l=0; l < this.items.size(); l++){
	           if(this.items.get(l).getFoodType().equals(this.outputs.get(i))){
	               outputString = String.format("#" + this.items.get(l).getID() + " " + 
                   this.items.get(l).getFoodType() + ": " + this.items.get(l).getFoodName()+ 
                   " (" + this.items.get(l).getPrice() + ")");
		           System.out.println(outputString);
		           if(this.items.get(l).getFoodType().equals("Combo")){
		               for(int z=0; z<this.items.get(l).getList().size(); z++){
		                   outputString = String.format("   #" + this.items.get(l).getList().get(z).getID()
			               + " " + this.items.get(l).getList().get(z).getFoodType() + ": " + 
			               this.items.get(l).getList().get(z).getFoodName() + " (" +
			               this.items.get(l).getList().get(z).getPrice() + ")");
			               System.out.println(outputString);
			               if(this.items.get(l).getList().get(z).getFoodType().equals("Combo")){
			                   for (int a=0; a<this.items.get(l).getList().get(z).getList().size(); a++){	
                                   outputString = String.format("   #" + this.items.get(l).getList().get(z).getList().get(a).getID()
	                               + " " + this.items.get(l).getList().get(z).getList().get(a).getFoodType() + ": " + 
	                               this.items.get(l).getList().get(z).getList().get(a).getFoodName() + " (" +
	                               this.items.get(l).getList().get(z).getList().get(a).getPrice() + ")");
                                   System.out.println(outputString);
                                   if(this.items.get(l).getList().get(z).getList().get(a).getFoodType().equals("Combo")){       
			                           for (int b=0; b<this.items.get(l).getList().get(z).getList().get(a).getList().size(); b++){	
                                       outputString = String.format("   #" + this.items.get(l).getList().get(z).getList().get(a).getList().get(b).getID()
	                                   + " " + this.items.get(l).getList().get(z).getList().get(a).getList().get(b).getFoodType() + ": " + 
	                                   this.items.get(l).getList().get(z).getList().get(a).getList().get(b).getFoodName() + " (" +
	                                   this.items.get(l).getList().get(z).getList().get(a).getList().get(b).getPrice() + ")");
                                       System.out.println(outputString);
                                       }
                                   }
			                   }
			               }
		                }
		           }
	             }
	       else{
		       continue;
	       }
	   }
       }
    }
    @Override
    public String toString(){
        String s1 = new String();
	String s2 = new String();
        s1 = "#" + this.items.get(this.foodCount - 1).getID() + " " + 
        this.items.get(this.foodCount -1 ).getFoodType() + ": " + this.items.get(this.foodCount -1 ).getFoodName() + 
        " (" + this.items.get(this.foodCount-1).getPrice() + ")"; //Default String
	if(this.items.get(this.foodCount - 1) instanceof Combo){
            for(int z=0; z<this.items.get(this.foodCount -1).getList().size(); z++){ //checking inside combo list
                s2 = s2 + String.format("\n   #" + this.items.get(this.foodCount -1).getList().get(z).getID()
	        + " " + this.items.get(this.foodCount -1).getList().get(z).getFoodType() + ": " + 
	        this.items.get(this.foodCount -1).getList().get(z).getFoodName() + " (" +
	        this.items.get(this.foodCount -1).getList().get(z).getPrice() + ")");
		if(this.items.get(this.foodCount-1).getList().get(z).getFoodType().equals("Combo")){
		    for (int a=0; a<this.items.get(this.foodCount-1).getList().get(z).getList().size(); a++){	
                    s2 = s2 + String.format("\n   #" + this.items.get(this.foodCount -1).getList().get(z).getList().get(a).getID()
	            + " " + this.items.get(this.foodCount -1).getList().get(z).getList().get(a).getFoodType() + ": " + 
	            this.items.get(this.foodCount -1).getList().get(z).getList().get(a).getFoodName() + " (" +
	            this.items.get(this.foodCount -1).getList().get(z).getList().get(a).getPrice() + ")");
		    }
		}
	    }
	    return s1 + s2;
	}
	else{
	    return s1;
	}
    }
}
