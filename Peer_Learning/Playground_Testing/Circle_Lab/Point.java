class Point {
   private final double x;
   private final double y;
   
   public Point(double x, double y) {
        this.x = x;
        this.y = y;
   }

   public String toString() {
       String s1 = new String();
       s1 = "Point x : " + this.x + " and Point y: " + this.y;
       return s1;
   }
}
