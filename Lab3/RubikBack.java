class RubikBack extends Rubik{
	public RubikBack(Rubik rubikObj){
            super(rubikObj);
	}
	public RubikBack left(){
	    int newgrid[][][] = new int [6][3][3];
	    for(int x=0; x<6; x++){
                for(int y=0; y<3; y++){
	            for(int z=0; z<3; z++){
		        if(x==0 && y==0){
			    newgrid[x][y][z] = super.grid[1][2-z][y];
			}
			else if(x==1 && z==0){
			    newgrid[x][y][z] = super.grid[4][2-z][y];
			}
			else if(x==3 && z==2){
			    newgrid[x][y][z] = super.grid[0][2-z][y];
			}
			else if(x==4 && y==2){
			    newgrid[x][y][z] = super.grid[3][2-z][y];
			}
			else if(x==5){
			    newgrid[x][y][z] = super.grid[5][z][2-y];
			}
			else{
			    newgrid[x][y][z] = super.grid[x][y][z];
			}
		    }
		}
	    }
	    Rubik obj = new Rubik(newgrid);
	    return new RubikBack(obj);
	}
			    
	public RubikBack right(){
	    int newgrid[][][] = new int [6][3][3];
	    for(int x=0; x<6; x++){
                for(int y=0; y<3; y++){
	            for(int z=0; z<3; z++){
		        if(x==0 && y==0){
			    newgrid[x][y][z] = super.grid[3][z][2-y];
			}
			else if(x==1 && z==0){
			    newgrid[x][y][z] = super.grid[0][z][2-y];
			}
			else if(x==3 && z==2){
			    newgrid[x][y][z] = super.grid[4][z][2-y];
			}
			else if(x==4 && y==2){
			    newgrid[x][y][z] = super.grid[1][z][2-y];
			}
			else if(x==5){
			    newgrid[x][y][z] = super.grid[5][2-z][y];
			}
			else{
			    newgrid[x][y][z] = super.grid[x][y][z];
			}
		    }
		}
	    }
	    Rubik obj = new Rubik(newgrid);
	    return new RubikBack(obj);
	}

	public RubikBack half(){
	    int newgrid[][][] = new int [6][3][3];
	    for(int x=0; x<6; x++){
                for(int y=0; y<3; y++){
	            for(int z=0; z<3; z++){
		        if(x==0 && y==0){
			    newgrid[x][y][z] = super.grid[4][2-y][2-z];
			}
			else if(x==1 && z==0){
			    newgrid[x][y][z] = super.grid[3][2-y][2-z];
			}
			else if(x==3 && z==2){
			    newgrid[x][y][z] = super.grid[1][2-y][2-z];
			}
			else if(x==4 && y==2){
			    newgrid[x][y][z] = super.grid[0][2-y][2-z];
			}
			else if(x==5){
			    newgrid[x][y][z] = super.grid[5][2-y][2-z];
			}
			else{
			    newgrid[x][y][z] = super.grid[x][y][z];
			}
		    }
		}
	    }
	    Rubik obj = new Rubik(newgrid);
	    return new RubikBack(obj);
	}
}
