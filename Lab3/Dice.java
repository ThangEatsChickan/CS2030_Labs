class Dice implements SideViewable{
    protected String [] faces;
    public Dice(){ 
	String [] sides = {"U", "F", "R", "B", "L", "D"};
        this.faces = sides;
    }
    @Override
    public SideViewable upView(){
        String newgrid[] = new String[6];
	for(int x=0; x<6; x++){
	    if(x==0){
	        newgrid[x] = this.faces[3];
	    }
	    else if(x==1){
		newgrid[x] = this.faces[0];
	    }
	    else if(x==2){
		newgrid[x] = this.faces[2];
	    }
	    else if(x==3){
		newgrid[x] = this.faces[5];
	    }
	    else if(x==4){
		newgrid[x] = this.faces[4];
	    }
	    else{
		newgrid[x] = this.faces[1];
	    }
	}
	Dice obj = new Dice();
        obj.faces = newgrid;
	return obj;
    }
    public SideViewable frontView(){
        String newgrid[] = new String[6];
	for(int x=0; x<6; x++){
	        newgrid[x] = this.faces[x];
	    }
	Dice obj = new Dice();
        obj.faces = newgrid;
	return obj;
    }
    public SideViewable rightView(){
        String newgrid[] = new String[6];
	for(int x=0; x<6; x++){
	    if(x>0 && x<4){
		newgrid[x] = this.faces[x+1];
	    }
	    else if(x==4){
		newgrid[x] = this.faces[1];
	    }
	    else{
		newgrid[x] = this.faces[x];
	    }
	}
	Dice obj = new Dice();
        obj.faces = newgrid;
	return obj;
    }
    public SideViewable backView(){
        String newgrid[] = new String[6];
	for(int x=0; x<6; x++){
	    if(x==1 || x==2){
	        newgrid[x] = this.faces[x+2];
	    }
	    else if(x==3 || x==4){
		newgrid[x] = this.faces[x-2];
	    }
	    else{
		newgrid[x] = this.faces[x];
	     }    
	}
	Dice obj = new Dice();
        obj.faces = newgrid;
	return obj;
    }
    public SideViewable leftView(){
        String newgrid[] = new String[6];
	for(int x=0; x<6; x++){
	    if(x==1){
	        newgrid[x] = this.faces[4];
	    }
	    else if(x>1 && x<5){
		newgrid[x] = this.faces[x-1];
	    }
	    else{
		newgrid[x] = this.faces[x];
	    }
	}
	Dice obj = new Dice();
        obj.faces = newgrid;
	return obj;
    }
    public SideViewable downView(){
        String newgrid[] = new String[6];
	for(int x=0; x<6; x++){
	    if(x==0){
	        newgrid[x] = this.faces[1];
	    }
	    else if(x==1){
		newgrid[x] = this.faces[5];
	    }
	    else if(x==2){
		newgrid[x] = this.faces[x];
	    }
	    else if(x==3){
		newgrid[x] = this.faces[0];
	    }
	    else if(x==4){
		newgrid[x] = this.faces[x];
	    }
	    else{
		newgrid[x] = this.faces[3];
	    }
	}
	Dice obj = new Dice();
        obj.faces = newgrid;
	return obj;
    }
    @Override
    public String toString(){
	String s1 = new String();
	s1 = "\n" + this.faces[0] + "\n" + this.faces[1]+ this.faces[2] + this.faces[3] + this.faces[4] + "\n" + "   "  + this.faces[5];
	return s1;
}
}
