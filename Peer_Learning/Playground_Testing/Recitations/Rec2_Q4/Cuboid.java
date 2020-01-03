
class Cuboid implements Volume, Density {
   private final double side;
   private final double mass;
   public Cuboid(double side, double mass) {
       this.side = side;
       this.mass = mass;
   }

   public double getSide() {
       return this.side;
   }
   
   public double getMass() {
       return this.mass;
   }

   public double getVolume() {
       return Math.pow(this.side, 3);
   }

   public double getDensity() {
       return (this.getMass() / this.getVolume());
   }

   @Override
   public String toString() {
       String s1 = new String();
       s1 = "Side: " + this.side + " Mass: " + this.mass;
       return s1;
   }
}
  
