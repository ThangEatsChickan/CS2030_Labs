class Rectangle {
    private final double length;
    private final double breadth;
    private final double area;
    private final double perimeter;
    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
        this.area = length * breadth;
        this.perimeter = (2 * length) + (2 * breadth);
    }

    public Rectangle setHeight(double height) {
        return new Rectangle(this.length, height);
    }

    public Rectangle setWidth(double width) {
        return new Rectangle(width, this.breadth);
    }
 
    @Override
    public String toString() {
        String s1 = new String();
        s1 = "area " + this.area + " and perimeter " + this.perimeter;
        return s1;
    }
}
    
