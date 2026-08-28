package string.class_problems;

public class CsvStudentRecordParser {

    public static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            String name = fields[0];
            String rollNumber = fields[1];
            String department = fields[2];
            System.out.println("Name: " + name + " | Roll No: " + rollNumber + " | Dept: " + department);
        }
    }

    public static void main(String[] args) {
        String csvLine = "Ananya Verma,RA2211003010123,CSE";
        parseStudentRecord(csvLine);
    }
}
