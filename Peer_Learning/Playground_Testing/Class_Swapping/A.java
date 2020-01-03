class A { 
    private final int x;
    A (int x) {
      this.x = x;
    }

    public int getX(){ 
        return this.x;
    }

    public  void finalMethod() {
        System.out.println("This is a final method!");
    }
    static void method () {
        System.out.println("A!");
    }
}
