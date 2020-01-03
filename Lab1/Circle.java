class Circle{
	private final Point centre;
	private final double radius;
	private Circle(Point centre, double radius){
		this.centre = centre;
		this.radius = radius;
	}
	static Circle getCircle(Point centre, double radius){
		if (radius <= 0)
		{
			return null;
		}
		else if(Double.isNaN(centre.getY()))
		{
			return null;
		}
		else if(Double.isNaN(centre.getX()))
		{
			return null;
		}
		else
		{
			return new Circle(centre,radius);
		}
	}
	public int getDiscCoverage(Point points[]){
		int numofDiscCoverage = 0;
		for(Point pt: points){
			if(this.centre.distance(pt) <= radius){ //checks if distance from pt P to mid is less than radius
				numofDiscCoverage = numofDiscCoverage + 1;
			}
		}
		return numofDiscCoverage;
	}
	@Override
	public String toString(){
		String s1 = String.format("circle of radius %.1f centered at point, (%.3f, %.3f)", this.radius, this.centre.x, this.centre.y);
		return s1;
	}

}
