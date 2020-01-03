
class Student extends Person implements Comparable<Student>{
    public final String studID;
    public final double testMark;
    public final double examMark;
    
    public Student (String ic, String name, String id, double test, double exam) {
        super(ic,name);
        this.studID = id;
        this.testMark = test;
        this.examMark = exam;
    } 
    public String getStudID() { 
        return this.studID;
    } 
    
    public double getTestMark() {
        return this.testMark;
    }
    
    public double getExamMark() {
        return this.examMark;
    }

    public double computeFinalMark() {
        return ((getExamMark() + getTestMark()) / 2);
    }
 
    @Override
    public int compareTo(Student first) {
        if (this.computeFinalMark() > first.computeFinalMark()) {
           return -1;
        } else if(this.computeFinalMark() < first.computeFinalMark()) {
           return 1; 
        } else {
           return 0;
        }
    }

    public void view() {
        System.out.println("Nric: " + super.getIC());
        System.out.println("Name: " + super.getName());
        System.out.println("Admin No: " + this.getStudID());
        System.out.println("Test Mark: " + this.getTestMark());
        System.out.println("Exam Mark: " + this.getExamMark());
        System.out.println("Final Mark: " + this.computeFinalMark() + "\n");
    }
}
