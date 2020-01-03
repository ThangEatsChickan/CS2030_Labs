class Employee extends Person {

    public Employee(String name, int id, String job, String interest) {
        super(name, id, job, interest);
    }

    @Override
    public void modifyDetails() {
        System.out.println("Employee details modified by abstract method!");
    }

    @Override
    public void nonAbstractMethod() {
        System.out.println("Over-written non-abstract method");
    }
}
        
