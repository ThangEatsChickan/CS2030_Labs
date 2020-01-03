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
    public boolean isRecyclable(int loaderID)
    {
	    if(this.loaderID % 3 == 0)
	    {
		    return true; // Is recyclable
	    }
	    else
	    {
		    return false; // Not recyclable
	    }
    }
    public String isRecyclableText(boolean isRecyclable)
    {
	    if(isRecyclable == true)
	    {
		    return " (recycled)";
	    }
	    else
	    {
		    return "";
	    }
    }
    public Loader serve(Cruise newCruise)
    {
	    int previousCruiseExpectedCompletedTime = 0;
	    if(this.serviceCruise!= null)
            {
                if(isRecyclable(this.loaderID) == true)
		{
		    previousCruiseExpectedCompletedTime = previousCruiseExpectedCompletedTime + 60;
		}
		if(this.serviceCruise.cruiseID.contains("A") | this.serviceCruise.cruiseID.contains("B"))
		{
	            previousCruiseExpectedCompletedTime = this.serviceCruise.getArrivalTime() + this.serviceCruise.getServiceCompletionTime() + previousCruiseExpectedCompletedTime;
		}
		else
		{
                    previousCruiseExpectedCompletedTime = this.serviceCruise.getServiceCompletionTime() + 30 + previousCruiseExpectedCompletedTime;
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
 	        String s1 = String.format("Loader " + this.loaderID + isRecyclableText(isRecyclable(this.loaderID)) + " serving " + this.serviceCruise.cruiseID + "@%04d", this.serviceCruise.arrivalTime);
 	        return s1;
	    }
           else
	    {
		String s2 = String.format("Loader " + this.loaderID);
		return s2;
	    	    
            }

    }
}
