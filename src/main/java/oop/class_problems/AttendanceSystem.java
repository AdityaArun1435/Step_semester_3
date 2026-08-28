package oop.class_problems;

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return attendance >= 75;
    }

    public static double classAverage(SrmStudent[] students) {
        int total = 0;
        for (int i = 0; i < students.length; i++) {
            total = total + students[i].attendance;
        }
        return total / (double) students.length;
    }
}

public class AttendanceSystem {

    public static void main(String[] args) {
        SrmStudent[] students = new SrmStudent[5];
        students[0] = new SrmStudent("Ravi", "RA001", 82);
        students[1] = new SrmStudent("Anitha", "RA002", 68);
        students[2] = new SrmStudent("Karthik", "RA003", 91);
        students[3] = new SrmStudent("Meera", "RA004", 74);
        students[4] = new SrmStudent("Suresh", "RA005", 60);

        for (int i = 0; i < students.length; i++) {
            String status = students[i].isEligible() ? "Eligible" : "Detained";
            System.out.println(students[i].name + " - " + students[i].attendance + "% - " + status);
        }

        double average = SrmStudent.classAverage(students);
        System.out.println("Class average: " + average + "%");
    }
}
