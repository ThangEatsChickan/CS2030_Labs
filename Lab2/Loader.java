class Loader{
    protected final int loaderID;
    protected final Cruise serviceCruise;
    public Loader(int loaderID)
    {
	    this.loaderID = loaderID;
	    this.serviceCruise = null;
    }
    public Loader(int loaderID, Cruise newCruise)
    {
	    this.loaderID = loaderID;
	    this.serviceCruise = newCruise;
    }
    public Loader serve(Cruise newCruise)
    {
	    if(this.serviceCruise!= null)
            {
		int previousCruiseExpectedCompletedTime;
		if(this.serviceCruise.cruiseID.contains("A") | this.serviceCruise.cruiseID.contains("B"))
		{
	            previousCruiseExpectedCompletedTime = this.serviceCruise.getArrivalTime() + this.serviceCruise.getServiceCompletionTime();
		}
		else
		{
                    previousCruiseExpectedCompletedTime = this.serviceCruise.getServiceCompletionTime() + 30;
		}
		int currentCruiseTime = newCruise.getArrivalTime();
	        if(previousCruiseExpectedCompletedTime <= currentCruiseTime)
	        {
                    return new Loader(this.loaderID, newCruise);
	        }   
	        else
	        {
		    return null;
	        }
	    }
	    else
            {
		return new Loader(this.loaderID, newCruise);
	    }
    }
   public String toString()
    {
           if(this.serviceCruise != null)
            {
 	        String s1 = String.format("Loader " + this.loaderID + " serving " + this.serviceCruise.cruiseID + "@%04d", this.serviceCruise.arrivalTime);
 	        return s1;
	    }
           else
	    {
		String s2 = String.format("Loader " + this.loaderID);
		return s2;
	    	    
            }

    }
}
