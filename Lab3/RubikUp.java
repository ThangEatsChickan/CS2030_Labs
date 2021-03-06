class RubikUp extends Rubik{
	public RubikUp(Rubik rubikObj){
            super(rubikObj);
	}
	@Override
	public RubikUp left(){
	    int newgrid[][][] = new int[6][3][3];
	    for(int x=0; x<6; x++){
                for(int y=0; y<3; y++){
		    for(int z=0; z<3; z++){
		        if(x==0){
			    newgrid[x][y][z] = super.grid[0][z][2-y];
			}
			else if(x==1 && y==0){
			    newgrid[x][y][z] = super.grid[5][2-y][2-z];
			}
			else if(x==2 && y==0){
			    newgrid[x][y][z] = super.grid[1][y][z];
			}
			else if(x==3 && y==0){
			    newgrid[x][y][z] = super.grid[2][y][z];
			}
			else if(x==5 && y==2){
			    newgrid[x][y][z] = super.grid[3][2-y][2-z];
			}
			else{
			    newgrid[x][y][z] = super.grid[x][y][z];
			}
		    }
		}
	    }
            Rubik obj = new Rubik(newgrid);
	    return new RubikUp(obj);
	}
	public RubikUp right(){
	    int newgrid[][][] = new int[6][3][3];
	    for(int x=0; x<6; x++){
	        for(int y=0; y<3; y++){
		    for(int z=0; z<3; z++){
		        if(x==0){
			    newgrid[x][y][z] = super.grid[0][2-z][y];
			}
			else if(x==1 && y==0){
			    newgrid[x][y][z] = super.grid[2][y][z];
			}
			else if(x==2 && y==0){
			    newgrid[x][y][z] = super.grid[3][y][z];
			}
			else if(x==3 && y==0){
			    newgrid[x][y][z] = super.grid[5][2-y][2-z];
			}
			else if(x==5 && y==2){
			    newgrid[x][y][z] = super.grid[1][2-y][2-z];
			}
			else{
			    newgrid[x][y][z] = super.grid[x][y][z];
			}
		    }
		}
	    }
	    Rubik obj = new Rubik(newgrid);
	    return new RubikUp(obj);
	}
	public RubikUp half(){
	    int newgrid[][][] = new int[6][3][3];
	    for(int x=0; x<6; x++){
	        for(int y=0; y<3; y++){
		    for(int z=0; z<3; z++){
		        if(x==0){
			     newgrid[x][y][z] = super.grid[0][2-y][2-z];
			}
			else if(x==1 && y==0){
			    newgrid[x][y][z] = super.grid[3][y][z];
			}
			else if(x==2 && y==0){
			    newgrid[x][y][z] = super.grid[5][2-y][2-z];
			}
			else if(x==3 && y==0){
			    newgrid[x][y][z] = super.grid[1][y][z];
			}
			else if(x==5 && y==2){
			    newgrid[x][y][z] = super.grid[2][2-y][2-z];
			}
			else{
			    newgrid[x][y][z] = super.grid[x][y][z];
			}
		    }
		}
	    }
	    Rubik obj = new Rubik(newgrid);
	    return new RubikUp(obj);
	}
			
}
