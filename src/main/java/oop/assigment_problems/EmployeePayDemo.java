package oop.assigment_problems;

class Employee {
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeePayDemo {

    public static void main(String[] args) {
        Employee plainEmployee = new Employee("E001", "Karan", 40000);
        ManagerEmployee managerEmployee = new ManagerEmployee("E002", "Divya", 70000, 8000);
        InternEmployee internEmployee = new InternEmployee("E003", "Meera", 12000, 10000);

        Employee[] employees = {plainEmployee, managerEmployee, internEmployee};

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] instanceof ManagerEmployee) {
                ManagerEmployee manager = (ManagerEmployee) employees[i];
                System.out.println("Manager effective pay: Rs " + manager.effectiveSalary());
            } else if (employees[i] instanceof InternEmployee) {
                InternEmployee intern = (InternEmployee) employees[i];
                System.out.println("Intern effective pay: Rs " + intern.effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " + employees[i].getSalary());
            }
        }
    }
}
