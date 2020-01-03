abstract class Person {
    private final String name;
    private final int id;
    private final String job;
    private final String interest;

    public Person(String name, int id, String job, String interest) {
        this.name = name;
        this.id = id;
        this.job = job;
        this.interest = interest;
    }

    public abstract void modifyDetails();

    public void nonAbstractMethod() {
        System.out.println("Non-abstract method");
    }

    public String toString() {
        String s1 = new String();
        s1 = "Name: " + this.name + "\nID: " + this.id;
        return s1;
    }
}
        
