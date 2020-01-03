import java.util.Comparator;
class Employee{
    private final String name;
    private final int age;
    private final int salary;
    public Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public int getAge(){
        return this.age;
    }
    public int getSalary() {
        return this.salary;
    }

    /*public int compare(Employee emp2) {
        if(this.getSalary() > emp2.getSalary()) {
            return 1;
        } else if (this.getSalary() < emp2.getSalary()) {
            return -1;
        } else {
            return 0;
        }
    }*/

    public static Comparator<Employee>EmployeeAgeComparator = new Comparator<Employee>() { 
        @Override
        public int compare(Employee emp1, Employee emp2) {
            if(emp1.getSalary() > emp2.getSalary()) {
                return 1;
            } else if (emp1.getSalary() < emp2.getSalary()) {
                return -1;
            }     else {
                return 0;
            }
        }        
    };

    @Override
    public String toString() {
        String s1 = new String();
        s1 = "Employee: " + this.name + "\nSalary: " + this.getSalary() + "\nAge: " + this.getAge();
        return s1;
    }
}

