
class Circle implements PrintableShape{
    private final Point point;
    private final double radius;

    public Circle() {
        this.point = new Point(0,0);
        this.radius = 0;
    }
    public Circle(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }
    
    public double getArea() {
        return (3.14 * radius * radius);
    }
   
    public void print() { 
        System.out.println("Circle is created");
    } 

    public String toString(){
        String s1 = new String();
        s1 = "Circle with Point " + this.point + " created with " +
             this.radius + " as radius.";
        return s1;
    }
}
