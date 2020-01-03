import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class Job{
    private final String jobRole; 
    private final double ptReq;
    private final int vacancies;
    private static List<Student> studList;
    private static List<Student> hiredStudList = new ArrayList<>();
    public Job(String jobRole, double ptReq, int vacancies) {
        this.jobRole = jobRole;
        this.ptReq = ptReq;
        this.vacancies = vacancies;
        this.studList = new ArrayList<>();
    }

    public void apply(Student[] studArray) {
        int size = studArray.length;
        for(int i = 0; i < size; i++) {
            if(studArray[i].totalPts > this.ptReq) {
                if(studList.size() == 0) {
                    studList.add(studArray[i]);
                } else {
                    for(int j = 0; j < studList.size(); j++) {
                        if(studArray[i].getName().equals(studList.get(j).getName())) {
                            break;
                        } else if(j == studList.size() - 1) {
                            studList.add(studArray[i]);
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
    }
    
    public void hire() {
        Collections.sort(studList);
        int slots = this.vacancies;
        int leftOverStuds = studList.size();
        int index = 1;
        System.out.println("Hired: ");
        for(int h = 0; h < leftOverStuds; h++) {
            Student s = studList.get(h);
            if(slots < 1) {
                break;
            } else if(s.totalPts > this.ptReq) {
                if (hiredStudList.size() == 0) {
                    hiredStudList.add(s);
                    System.out.println(index + ": " + s.getName());
                    slots = slots - 1;
                } else {
                    for(int k = 0; k < hiredStudList.size(); k++) {
                        Student existed = hiredStudList.get(k);
                        if(s.equals(existed)) {
                            break;
                        } else if(k == hiredStudList.size() - 1) {
                            hiredStudList.add(s);
                            index = index + 1;
                            System.out.println(index + ": " + s.getName());
                            slots = slots - 1;
                        } else {
                            continue;
                        }
                    }
                }
            } else if (h == leftOverStuds - 1) {
                break;
            } else {
                continue;
            }
        }
    }

    @Override    
    public String toString() {
        String s1 = new String();
        s1 = "\nFull-Time Job: " + this.jobRole + 
             "\nVacancies: " + this.vacancies + 
             String.format("\nPoints Requirements: %.2f" ,this.ptReq); 
        return s1;
    }
}
