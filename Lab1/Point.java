class Point{
	protected final double x;
	protected final double y;
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public Point midPoint(Point point){
		double mid_x;
		double mid_y;
		mid_x = (point.x + this.x) / 2;
		mid_y = (point.y + this.y) / 2;
		return new Point(mid_x,mid_y);
	}
	public double angleTo(Point qCoords){
		double pCX = this.x;
		double pCY = this.y;
		double qCX = qCoords.getX();
		double qCY = qCoords.getY();
		double angle = 0;
		double opposite;
		double adjacent;
		double referenceAngle;
		
		opposite = Math.abs(pCY - qCY);
		adjacent = Math.abs(pCX - qCX);
		referenceAngle = Math.atan(opposite/adjacent);

		if(pCX == qCX && pCY < qCY){
			angle = Math.PI/2;
			return angle;
		}
		else if(pCX > qCX && pCY == qCY){
			angle = Math.PI;
			return angle;
		}
		else if(pCX == qCX && pCY > qCY){
			angle = -Math.PI /2;
			return angle; 
		}
		else if(pCX < qCX && pCY == qCY){
			angle = 0;
			return angle;
		}

		if(qCX > pCX && qCY > pCY){
			angle = referenceAngle;
			return angle; //In the 1st quad
		}
		else if(qCX < pCX && qCY > pCY){
			angle = Math.PI - referenceAngle; // In the 2nd quad
			return angle;
		}
		else if(qCX < pCX && qCY < pCY){
			angle = -Math.PI + referenceAngle; // In the 3rd quad
			return angle;
		}
		else if(qCX > pCX && qCY < pCY){
			angle = -referenceAngle; //In the 4th quad
			return angle;
		}
		return angle; 
		 //No rotation needed when they are on the asme point
		/*double lineAngle;
		if(point.x >=0 || point.y >=0)
		{	
		      	lineAngle = Math.atan((point.y-this.y) / (point.x - this.x));
		}
		else
		{	
		 	lineAngle = -(Math.PI) + Math.atan((point.y-this.y) / (point.x - this.x));
		}
			return lineAngle;*/
	}
	public double distance(Point a){
		double xRun = this.x - a.getX();
		double yRise = this.y - a.getY();
		return Math.sqrt(xRun * xRun + yRise * yRise);
	}
	public Point moveTo(double angle, double distance){
		double newPoint_x;
		double newPoint_y;
		newPoint_x = this.x + (distance * Math.cos(angle));
		newPoint_y = this.y + (distance * Math.sin(angle));
	        return new Point(newPoint_x, newPoint_y);	
	}
	@Override
	public String toString()
	{
		String s1 = String.format("point (%.3f, %.3f)", this.x, this.y);
		return s1;
	}
	@Override
	public boolean equals(Object object){
		if(object instanceof Point)
		{
			Point a = (Point) object;
			return Math.abs(this.x - a.getX()) < 1E-15 &&
				Math.abs(this.y - a.getY()) < 1E-15;
		}
		else if (this == object)
		{
			return true;
		}
		else{
			return false;
		}
	}
}

