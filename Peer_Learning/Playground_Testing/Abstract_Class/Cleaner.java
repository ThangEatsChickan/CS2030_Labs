class Cleaner extends Employee {

    public Cleaner(String name, int id, String job, String interest) {
        super(name, id, job, interest);
    }

    @Override
    public void modifyDetails() {
        System.out.println("Cleaner details modified by abstract method!");
    }

    @Override
    public void nonAbstractMethod() {
        System.out.println("Cleaner Over-written non-abstract method");
    }
}
        
