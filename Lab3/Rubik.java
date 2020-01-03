class Rubik implements SideViewable{
    protected final int [][][] grid;
    protected final Rubik Rubikobj;
    public Rubik(int [][][] grid){
	this.grid = grid;
	this.Rubikobj = null;
    }
    public Rubik(Rubik newRubik){
        this.grid = newRubik.grid;
	this.Rubikobj = newRubik;
    }
    public Rubik clone()
    {
	    int replica [][][] = new int [6][3][3];
	    for(int x=0; x<6; x++){
	        for(int y=0; y<3; y++){
		    for(int z=0; z<3; z++){
		        replica[x][y][z] = this.grid[x][y][z];
		    }
		}
	    }
	    return new Rubik(replica);
    }
    public Rubik left(){
        int newgrid[][][] = new int[6][3][3];
	for(int x=0; x<6; x++){
		for(int y=0;y<3;y++){
			for(int z= 0; z<3;z++){
	                    if(x==0 && y==2){
			        newgrid[x][y][z] = this.grid[3][z][0]; // Tp[
			    }
			    else if(x==1&& z==2){
		      	        newgrid[x][y][z] = this.grid[0][2][2-y]; //Left
			    }
			    else if(x==4&& y==0){
			        newgrid[x][y][z] = this.grid[1][z][2]; //Bottom
			    }
			    else if(x==3&& z==0){
		                newgrid[x][y][z] = this.grid[4][0][2-y]; //Right
			    }
			    else if(x==2){
			        newgrid[x][y][z] = this.grid[2][z][2-y]; //In-left
				    
			    }
			    else
			    { 
			        newgrid[x][y][z] = this.grid[x][y][z]; //No change
		            }
			}
		}
	}
        return new Rubik(newgrid);
    }
    public Rubik right(){
        return this.clone().left().left().left();
    }
    public Rubik half(){
	return this.clone().left().left();
    }
    public SideViewable upView(){
        int newgrid[][][] = new int[6][3][3];
	for(int x=0; x<6; x++){
	    for(int y=0; y<3; y++){
	        for(int z=0; z<3; z++){
		    if(x==0){
		        newgrid[x][y][z] = this.grid[5][y][z];
		    }
		    else if(x==1){
			newgrid[x][y][z] = this.grid[1][2-z][y];
		    }
		    else if(x==2){
                        newgrid[x][y][z] = this.grid[0][y][z];
		    }
		    else if(x==3){
                        newgrid[x][y][z] = this.grid[3][z][2-y];
		    }
		    else if(x==4){
                        newgrid[x][y][z] = this.grid[2][y][z];
		    }
		    else if(x==5){
		        newgrid[x][y][z] = this.grid[4][y][z];
		    }
		}
	    }
	}
	return new Rubik(newgrid);
    }
    public SideViewable rightView(){
        int newgrid[][][] = new int[6][3][3];
	for(int x=0; x<6; x++){
	    for(int y=0; y<3; y++){
                for(int z=0; z<3; z++){
		    if(x==0){
		        newgrid[x][y][z] = this.grid[0][2-z][y];
		    }
		    else if(x==1){
			newgrid[x][y][z] = this.grid[2][y][z];
		    }
		    else if(x==2){
			newgrid[x][y][z] = this.grid[3][y][z];
		    }
		    else if(x==3){
			newgrid[x][y][z] = this.grid[5][2-y][2-z];
		    }
		    else if(x==4){
			newgrid[x][y][z] = this.grid[4][z][2-y];
		    }
		    else if(x==5){
                        newgrid[x][y][z] = this.grid[1][2-y][2-z];
		    }
		}
	    }
	}
	return new Rubik(newgrid);
    }
    public SideViewable downView(){
        int newgrid[][][] = new int[6][3][3];
	for(int x=0; x<6; x++){
            for(int y=0; y<3; y++){
	        for(int z=0; z<3; z++){
	            if(x==0){
		        newgrid[x][y][z] = this.grid[2][y][z];
		    }
		    else if(x==1){
			newgrid[x][y][z] = this.grid[1][z][2-y];
		    }
		    else if(x==2){
			newgrid[x][y][z] = this.grid[4][y][z];
		    }
		    else if(x==3){
			newgrid[x][y][z] = this.grid[3][2-z][y];
		    }
		    else if(x==4){
			newgrid[x][y][z] = this.grid[5][y][z];
		    }
		    else if(x==5){
			newgrid[x][y][z] = this.grid[0][y][z];
		    }
		}
	    }
	}
        return new Rubik(newgrid);
    }
    public SideViewable leftView(){
        int newgrid[][][] = new int[6][3][3];
	for (int x=0; x<6; x++){
	    for(int y=0; y<3; y++){
	        for(int z=0; z<3; z++){
		    if(x==0){
		        newgrid[x][y][z] = this.grid[0][z][2-y];
		    }
		    else if(x==1){
			newgrid[x][y][z] = this.grid[5][2-y][2-z];
		    }
		    else if(x==2){
			newgrid[x][y][z] = this.grid[1][y][z];
		    }
		    else if(x==3){
			newgrid[x][y][z] = this.grid[2][y][z];
		    }
		    else if(x==4){
			newgrid[x][y][z] = this.grid[4][2-z][y];
		    }
		    else if(x==5){
			newgrid[x][y][z] = this.grid[3][2-y][2-z];
		    }
		}
	    }
	}
	return new Rubik(newgrid);
    }
    public SideViewable backView(){
	int newgrid[][][] = new int [6][3][3];
	for(int x=0; x<6; x++){
            for(int y=0; y<3; y++){
                for(int z=0; z<3; z++){
	            if(x==0){
	                newgrid[x][y][z] = this.grid[0][2-y][2-z];
		    }
		    else if(x==1){
			newgrid[x][y][z] = this.grid[3][y][z];
		    }
		    else if(x==2){
                        newgrid[x][y][z] = this.grid[5][2-y][2-z];
		    }
		    else if(x==3){
			newgrid[x][y][z] = this.grid[1][y][z];
		    }
		    else if(x==4){
			newgrid[x][y][z] = this.grid[4][2-y][2-z];
		    }
		    else if(x==5){
			newgrid[x][y][z] = this.grid[2][2-y][2-z];
		    }
		}
	    }
	}
	return new Rubik(newgrid);
    }
    public SideViewable frontView(){
	return new Rubik(this.grid);
    }
    @Override
    public String toString(){
        String s1 = new String();
	String s2 = new String();
	String s3 = new String();
	String s4 = new String();
	String s5 = new String();
	String s6 = new String();
	s2= s2+"\n";
	for(int x=0; x<6; x++){
	    for(int y=0; y<3; y++){
		    if(x==0){
			s1= s1+ "\n......";
		    }
		    if(x>=4){
		        s5 = s5+ "......";
		    }
	        for(int z=0; z<3; z++){
		    if(x==0){
	                s1=s1 + String.format("%02d", this.grid[x][y][z]);
			if(z==2){
			    s1 = s1 + "......";
			}
		    }
		    else if(x > 0 && x < 4){
		        if(y==0){
			    s2=s2+String.format("%02d", this.grid[x][y][z]);
			    if(z==2 & x==3){
		                s2=s2+"\n";
			    }
                        }
			else if(y==1){
			    s3=s3+String.format("%02d", this.grid[x][y][z]);
			    if(z==2 & x==3){
		                s3=s3+"\n";
			    }
			}
			else{
			    s4=s4+String.format("%02d", this.grid[x][y][z]);
			    if(z==2 & x==3){
		                s4=s4+"\n";
			    }
			}
		    }
		    else{
		        s5=s5+String.format("%02d", this.grid[x][y][z]);
			if(z==2){
			    s5 = s5 + "......\n";
			    }
		    }
		}
	    }
	}
	return s1+s2+s3+s4+s5;
    }
}
