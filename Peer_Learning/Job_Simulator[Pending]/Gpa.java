class Gpa {
    public Gpa() {
    
    }
    
    public static double calculate(String[] strArray) {
        double points = 0;
        double average = 0;
        int size = strArray.length;
        for(int i = 0; i < size; i++) {
            String s = strArray[i];
            switch(s){
                case "A+":
                    points = points + 5;
                    break;
                case "A":
                    points = points + 5;
                    break;
                case "A-":
                    points = points + 4.5;
                    break;
                case "B+":
                    points = points + 4;
                    break;
                case "B":
                    points = points + 3.5;
                    break;
                case "B-":
                    points = points + 3;
                    break;
                case "C+":
                    points = points + 2.5;
                    break;
                case "C":
                    points = points + 2;
                    break;
                case "D+":
                    points = points + 1.5;
                    break;
                case "D":
                    points = points + 1;
                    break;
                case "F":
                    points = points + 0;
                    break;
            }
        }
        average = points / size;
        return average;
    }
}
