class BigCruise extends Cruise{
	protected final String bigCruiseID;
	protected final int arrivalTime;
	protected final int noLoaders;
	protected final int minutes;
	public BigCruise(String bigCruiseID, int arrivalTime, int noLoaders, int minutes){
		super(bigCruiseID, arrivalTime, noLoaders, minutes);
		this.bigCruiseID = bigCruiseID;
		this.arrivalTime = arrivalTime;
		this.noLoaders = noLoaders;
		this.minutes = minutes;
	}
}
