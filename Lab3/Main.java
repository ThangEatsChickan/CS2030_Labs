import java.util.Scanner;
class Main{
	public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);
	    int cubeMatrices = 54;
	    int [][][] grid = new int [6][3][3];
	        for(int i=0 ;i<6; i++){
	            for(int j=0; j<3; j++){
		        for(int k=0; k<3; k++){
				int input = scanner.nextInt();
				grid[i][j][k] = input;
			}
		    }
		}
	    Rubik createRubik = new Rubik(grid); //Create a rubik object
            Rubik rubikObj = new Rubik(createRubik); //Original Rubik object
	    Rubik clonedObj = rubikObj.clone(); //Cloned Rubik object
	    String line = new String();
	    int i = 0;
	    while (scanner.hasNext()==true){
		line = scanner.next();
		if(line.equals("F")){
                    clonedObj = new Rubik(clonedObj).right();
		}
		else if(line.equals("R")){
		    clonedObj = new RubikRight(clonedObj).right();
		}
		else if(line.equals("U")){
		    clonedObj = new RubikUp(clonedObj).right();
		}
		else if(line.equals("L")){
                    clonedObj = new RubikLeft(clonedObj).right();
		}
		else if(line.equals("B")){
		    clonedObj = new RubikBack(clonedObj).right();
		}
		else if(line.equals("D")){
                    clonedObj = new RubikDown(clonedObj).right();
		}
		else if(line.equals("F'")){
                    clonedObj = new Rubik(clonedObj).left();
		}
		else if(line.equals("R'")){
		    clonedObj = new RubikRight(clonedObj).left();
		}
		else if(line.equals("U'")){
		    clonedObj = new RubikUp(clonedObj).left();
		}
		else if(line.equals("L'")){
		    clonedObj = new RubikLeft(clonedObj).left();
		}
		else if(line.equals("B'")){
		    clonedObj = new RubikBack(clonedObj).left();
		}
		else if(line.equals("D'")){
		    clonedObj = new RubikDown(clonedObj).left();
		}
		else if(line.equals("F2")){
		    clonedObj = new Rubik(clonedObj).half();
		}
		else if(line.equals("R2")){
		    clonedObj = new RubikRight(clonedObj).half();
		}
		else if(line.equals("U2")){
		    clonedObj = new RubikUp(clonedObj).half();
		}
		else if(line.equals("L2")){
		    clonedObj = new RubikLeft(clonedObj).half();
		}
		else if(line.equals("B2")){
		    clonedObj = new RubikBack(clonedObj).half();
		}
		else if(line.equals("D2")){
		    clonedObj = new RubikDown(clonedObj).half();
		}
		if(scanner.hasNext() == false){
		    break;
		}
	    }
	    System.out.println(clonedObj);
	}
}
