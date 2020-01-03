class RubikRight extends Rubik{
	public RubikRight(Rubik rubikObj){
            super(rubikObj);
	}
	public RubikRight left(){
	    int newgrid[][][] = new int [6][3][3];
	    for(int x=0; x<6; x++){
                for(int y=0; y<3; y++){
	            for(int z=0; z<3; z++){
		        if(x==0 && z==2){
			    newgrid[x][y][z] = super.grid[5][y][z];
			}
			else if(x==2 && z==2){
			    newgrid[x][y][z] = super.grid[0][y][z];
			}
			else if(x==3){
			    newgrid[x][y][z] = super.grid[3][z][2-y];
			}
			else if(x==4 && z==2){
			    newgrid[x][y][z] = super.grid[2][y][z];
			}
			else if(x==5 && z==2){
			    newgrid[x][y][z] = super.grid[4][y][z];
			}
			else{
			    newgrid[x][y][z] = super.grid[x][y][z];
			}
		    }
		}
	    }
	    Rubik obj = new Rubik(newgrid);
	    return new RubikRight(obj);
	}
			    
	public RubikRight right(){
	    int newgrid[][][] = new int [6][3][3];
	    for(int x=0; x<6; x++){
                for(int y=0; y<3; y++){
	            for(int z=0; z<3; z++){
		        if(x==0 && z==2){
			    newgrid[x][y][z] = super.grid[2][y][z];
			}
			else if(x==2 && z==2){
			    newgrid[x][y][z] = super.grid[4][y][z];
			}
			else if(x==3){
			    newgrid[x][y][z] = super.grid[3][2-z][y];
			}
			else if(x==4 && z==2){
			    newgrid[x][y][z] = super.grid[5][y][z];
			}
			else if(x==5 && z==2){
			    newgrid[x][y][z] = super.grid[0][y][z];
			}
			else{
			    newgrid[x][y][z] = super.grid[x][y][z];
			}
		    }
		}
	    }
	    Rubik obj = new Rubik(newgrid);
	    return new RubikRight(obj);
	}

	public RubikRight half(){
	    int newgrid[][][] = new int [6][3][3];
	    for(int x=0; x<6; x++){
                for(int y=0; y<3; y++){
	            for(int z=0; z<3; z++){
		        if(x==0 && z==2){
			    newgrid[x][y][z] = super.grid[4][y][z];
			}
			else if(x==2 && z==2){
			    newgrid[x][y][z] = super.grid[5][y][z];
			}
			else if(x==3){
			    newgrid[x][y][z] = super.grid[3][2-y][2-z];
			}
			else if(x==4 && z==2){
			    newgrid[x][y][z] = super.grid[0][y][z];
			}
			else if(x==5 && z==2){
			    newgrid[x][y][z] = super.grid[2][y][z];
			}
			else{
			    newgrid[x][y][z] = super.grid[x][y][z];
			}
		    }
		}
	    }
	    Rubik obj = new Rubik(newgrid);
	    return new RubikRight(obj);
	}
}
