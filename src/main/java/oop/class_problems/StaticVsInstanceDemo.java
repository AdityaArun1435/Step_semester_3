package oop.class_problems;

class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;

    public BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }
}

class FixedSrmStudent {
    String name;
    String regNo;
    int attendance;

    static String university = "SRMIST";
    static int admissionCount = 0;

    public FixedSrmStudent(String name, int attendance) {
        this.name = name;
        admissionCount++;
        this.regNo = "RA23110030" + (1000 + admissionCount);
        this.attendance = attendance;
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}

public class StaticVsInstanceDemo {

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenSrmStudent brokenRavi = new BrokenSrmStudent("Ravi", "RA001", 82);
        BrokenSrmStudent brokenMeera = new BrokenSrmStudent("Meera", "RA002", 74);
        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);

        System.out.println();
        System.out.println("Fixed version:");
        FixedSrmStudent ravi = new FixedSrmStudent("Ravi", 82);
        FixedSrmStudent meera = new FixedSrmStudent("Meera", 74);
        ravi.printIdCard();
        meera.printIdCard();
        FixedSrmStudent.printTotalAdmissions();
    }
}
