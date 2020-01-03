import java.util.Scanner;

class Main{
	private final Point centre;
	private final double radius;
	private Main(Point centre, double radius){
		this.centre = centre;
		this.radius = radius;
	}
	public static void main(String[] args){
		Double i;
		i = getUserInput();
		printOther(i);
	}
	public static double getUserInput(){
		Scanner scanner;
		double [][] points;
		int numberofPoints;
		int counter = 0;
		Point [] storagePoints;
		double container;
		scanner = new Scanner(System.in);
		numberofPoints = scanner.nextInt();
		storagePoints = new Point[numberofPoints];
		points = new double[numberofPoints][2];
		for(double[] insidePoint: points)
		{
			insidePoint[0] = scanner.nextDouble();
			insidePoint[1] = scanner.nextDouble();
			storagePoints[counter] = new Point(insidePoint[0], insidePoint[1]);
			counter++;
		}
	        container = checkCoverage(storagePoints);
		return container;
	}
 	public static double checkCoverage(Point[] points){
		double distance;
		double coverage = 0;
		double MaximumCoverage = 0;
		double occurence = 0;
		double xRun = 0;
		double yRise = 0;
		for(int i = 0; i < points.length; i++){
			    coverage = 0;
			    for(int j = 0; j < points.length; j++)
			    {
			    	Point pCoords = points[i];
				Point qCoords = points[j];
				if(pCoords.distance(qCoords) < 2 && !pCoords.equals(qCoords))
				{
					Circle circle = createCircle1(pCoords, qCoords, 1);
					coverage = circle.getDiscCoverage(points);
					if (coverage > MaximumCoverage)
					{
						MaximumCoverage = coverage;
					}
				}
			    }
		         }
		return MaximumCoverage;
	}
	public static Circle createCircle1(Point pCoords, Point qCoords, double radius)
	{
		double rotatedangle; 
		double pToMidDist;
		double circleCentreToMidDist;
		if(pCoords.equals(qCoords))
		{
			return null;
		}
		Point midptCoords = pCoords.midPoint(qCoords);
		pToMidDist = pCoords.distance(midptCoords);
		rotatedangle = (Math.PI / 2) + pCoords.angleTo(qCoords);
		circleCentreToMidDist = Math.sqrt(radius * radius - pToMidDist * pToMidDist);
		Point circleCentre = midptCoords.moveTo(rotatedangle, circleCentreToMidDist);
		return Circle.getCircle(circleCentre,radius);
	}
	static void printOther(Double D){
		String s2;
		s2 = String.format("Maximum Disc Coverage: %.0f", D);
		System.out.println(s2);
	}
	static void printPoints(Main points) {
		String s1;
		if (points != null)
		{	
		s1 = String.format("Created: circle of radius %.1f centered at point, (%.3f. %.3f)", points.radius, points.centre.x, points.centre.y);
		}
		else
		{
		s1 = String.format("No valid circle can be created");
		}
		System.out.println(s1);
	}
	static Main createCircle(Point point1, Point point2, double radius){
		double newCentre_x;
		double newCentre_y;
		double circle_adjacent;
	    Double circle_opposite;	
		if(point1.x == point2.x &&  point1.y == point2.y)
        {
            return null;
        }
        else
		{	
		newCentre_x = (point2.x - point1.x) / 2;
		newCentre_y = (point2.y - point1.y) / 2;
		circle_adjacent = point2.x - newCentre_x;
        circle_opposite = Math.sqrt(Math.pow(radius, 2) - Math.pow(circle_adjacent,2));
		if (circle_opposite.isNaN() == true)
        {
            return null;
        }
        else
        {
        newCentre_y = newCentre_y + circle_opposite;
		return new Main(new Point(newCentre_x, newCentre_y), radius);
        }
        }
	}

	@Override
	public String toString()
	{
		String s1 = String.format("circle of radius %.1f centered at point, (%.3f. %.3f)", this.radius, this.centre.x, this.centre.y);
		return s1;
	}
}
