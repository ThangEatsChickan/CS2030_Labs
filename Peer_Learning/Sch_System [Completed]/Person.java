class Person {
    public final String ic;
    public final String name;
    public Person (String ic, String name) {
        this.ic = ic;
        this.name = name;
    }
    
    public String getIC() {
        return this.ic;
    }
    
    public String getName() {
        return this.name;
    }
   
    public void view() {
        System.out.println("Nric: " + this.getIC());
        System.out.println("Name: " + this.getName());
    }
}
