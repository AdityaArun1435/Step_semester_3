package oop.assigment_problems;

class EmployeeHr {
    private String empId;
    private String empName;
    private double salary;

    public EmployeeHr(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

class ManagerEmployeeHr extends EmployeeHr {
    private double teamBonus;

    public ManagerEmployeeHr(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployeeHr extends EmployeeHr {
    private double stipendCap;

    public InternEmployeeHr(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

class ParkingSlotHr {
    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingSlotHr(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
        }
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    EmployeeHr employee;
    ParkingSlotHr slot;

    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, EmployeeHr employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = null;
        totalRecords++;
    }

    public String fullProfile() {
        double pay;
        if (employee instanceof ManagerEmployeeHr) {
            pay = ((ManagerEmployeeHr) employee).effectiveSalary();
        } else if (employee instanceof InternEmployeeHr) {
            pay = ((InternEmployeeHr) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String slotInfo = (slot == null) ? "no parking assigned" : slot.slotNo;
        return name + " | Pay: Rs " + pay + " | Slot: " + slotInfo;
    }
}

public class HrParkingMiniSystem {

    public static void main(String[] args) {
        ManagerEmployeeHr divyaEmployee = new ManagerEmployeeHr("E001", "Divya", 70000, 8000);
        EmployeeHr karanEmployee = new EmployeeHr("E002", "Karan", 40000);
        InternEmployeeHr meeraEmployee = new InternEmployeeHr("E003", "Meera", 12000, 10000);

        CompanyEmployeeRecord divya = new CompanyEmployeeRecord("Divya", "E001", divyaEmployee);
        CompanyEmployeeRecord karan = new CompanyEmployeeRecord("Karan", "E002", karanEmployee);
        CompanyEmployeeRecord meera = new CompanyEmployeeRecord("Meera", "E003", meeraEmployee);

        ParkingSlotHr slotA1 = new ParkingSlotHr("A1", 1, 0);
        ParkingSlotHr slotA2 = new ParkingSlotHr("A2", 1, 0);

        slotA1.allot(divya.empId);
        divya.slot = slotA1;

        slotA2.allot(karan.empId);
        karan.slot = slotA2;

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());

        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
