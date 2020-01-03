import java.util.Scanner;

class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int noIntern = Scanner.nextInt();
        Internship[] noInternship = new Internship[noIntern];
        int x = 0;
        while (x < noIntern) {
            String internName = Scanner.next();
            double internPts = Scanner.nextDouble();
            Internship newIntern = new Internship(internName, internPts);
            noInternship[x] = newIntern;
            x++;
        }
        x = 0;
        int noStudents = Scanner.nextInt();
        while (x < noStudents) {
            String name = Scanner.next();
            String[] grade = new String[6];
            int y = 0;
            while (y < 6) {
                String letter = Scanner.next();
                grade[y] = letter;
                y++;
            }
            int 


