import java.lang.Comparable;

class Student implements Comparable<Student>{
    public final String name;
    public final String[] strArray;
    public final Internship[] intArray;
    public final double gpa;
    public final double totalPts;

    public Student(String name, String[] strArray, Internship[] intArray) {
        this.name = name;
        this.strArray = strArray;
        this.intArray = intArray;
        this.gpa = calculateGPA(strArray);
        this.totalPts = calculateTotalPoints(intArray, this.gpa);
    }

    public String getName() {
        return this.name;
    }

    public double calculateGPA(String[] strArray) {
        return Gpa.calculate(this.strArray);
    }
 
    public double getTotalPts() {
        return this.totalPts;
    }

    public double calculateTotalPoints(Internship[] intArray, double gpa) {
        double total = 0;
        int size = intArray.length;
        for(int i = 0; i < size; i++) {
            total = total + intArray[i].pts;
        }
        return gpa + total; 
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student) {
            Student s = (Student)obj;
            return this.getName() == s.getName();
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Student stud1) {
        if(this.getTotalPts() > stud1.getTotalPts()) {
            return -1;
        } else if(this.getTotalPts() == stud1.getTotalPts()) {
            return 0; 
        } else {
            return 1;
        }
    }
    @Override
    public String toString() {
        String s1 = new String();
        s1 = "\nName: " + this.name + 
             String.format("\nGPA: %.2f",  this.gpa) +
             String.format("\nTotal points: %.2f", this.totalPts);
        return s1;
    } 
}
