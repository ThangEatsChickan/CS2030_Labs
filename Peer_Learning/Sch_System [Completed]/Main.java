import java.util.Scanner;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<Student>();
        int noStudent = sc.nextInt();
        int x = 0;
        int highestMark = 0;
        while(x < noStudent) {
            String ic = sc.next();
            String name = sc.next();
            String id = sc.next();
            double test = sc.nextDouble();
            double exam = sc.nextDouble();
            Student newStud = new Student(ic,name,id,test,exam);
            students.add(newStud);
            x++;
        } 
        Collections.sort(students);
        students.get(0).view();
    }
}
