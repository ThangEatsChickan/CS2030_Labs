class B extends A {
    B (int x) {
       super(x); 
    }
    static void method () { //return type can only be changed till A since it cannot be a supertype of the return type of A's method. 
        System.out.println("B!");
    }

    public void finalMethod() {
        System.out.println("Overwritten final method!");
    }
    static void childMethod() {
        System.out.println("Child method!");
    }
}
