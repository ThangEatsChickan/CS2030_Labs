class Cruise{
    protected final String cruiseID;
    protected final int arrivalTime;
    protected final int noLoaders;
    protected final int minutes;
    public Cruise(String cruiseID, int arrivalTime){
        this.cruiseID = cruiseID;
        this.arrivalTime = arrivalTime;
	this.noLoaders = 0;
        this.minutes = 0;	
    }
    public Cruise(String cruiseID, int arrivalTime, int noLoaders, int minutes){
        this.cruiseID = cruiseID;
	this.arrivalTime = arrivalTime;
	this.noLoaders = noLoaders;
	this.minutes = minutes;
    }
    public String getCruiseID(){
        return this.cruiseID;
    }
    public int getDefaultArrivalTime(){
        return this.arrivalTime;
    }
    public int getArrivalTime(){
        int convertHours; // 7hours
        int convertMinutes;
	int convertHourstoMinutes;
        int finalMinutes;
	convertHours = this.arrivalTime / 100;
	convertMinutes = this.arrivalTime % 100;
	convertHourstoMinutes = convertHours * 60;
	finalMinutes = convertHourstoMinutes + convertMinutes;
	return finalMinutes;
    }
    public int getNumLoadersRequired(){
	    if(this.cruiseID.contains("B"))
	    {
		    return this.noLoaders;
	    }
	    else
	    {
	            return 1;
	    }
    }
    public int getServiceCompletionTime(){
    	int timeTaken;
	if (this.cruiseID.contains("A"))
	    {
	        timeTaken = 30;
	    }
        else if(this.cruiseID.contains("B"))
	    {
		timeTaken = this.minutes;
	    }	    
	else
	    {     
	        timeTaken = getArrivalTime();
		return timeTaken;
	    }
	return timeTaken;
    }
    @Override
    public String toString()
    {
        String s1 = String.format(this.cruiseID + "@");
        String s2 = String.format("%04d", this.arrivalTime);
        return s1 + s2;
    }
}




