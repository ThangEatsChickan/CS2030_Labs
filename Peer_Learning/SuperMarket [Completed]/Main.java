import java.util.Scanner;

class Main {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        int x = 0;
        double sum = 0;
        Shop s = new Shop();
        while(x < 10) {
            String foodName = sc.next();
            double price = sc.nextDouble();
            if(x < 2) {
                s.addItem(new Beverages(foodName, price));
            } else if(x < 4) {
                s.addItem(new Canned(foodName, price));
            } else if(x < 6) {
                s.addItem(new Dairy(foodName, price));
            } else if(x < 8) {
                s.addItem(new Meat(foodName, price));
            } else {
                s.addItem(new Produce(foodName, price));
            }
            if(x == 9) {
                break;
            }
            x++;
        }
        System.out.println(s.toString());
        x = 0;
        while(x < 5) {
            String food = sc.next();
            for(Product prod : s.productList) {
                if(prod.getItem().equals(food)) {
                    sum = sum + prod.getPrice();
                }
            }
            x++;
        }
        System.out.println("Total spent: " + String.format("$%.2f", sum)); 
    }
}

