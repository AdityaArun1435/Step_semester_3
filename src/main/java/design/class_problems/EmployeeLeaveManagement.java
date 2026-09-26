package design.class_problems;

import java.time.LocalDate;

enum LeaveStatus { PENDING, APPROVED, REJECTED }

abstract class Employee {
    private String name;

    public Employee(String name) { this.name = name; }

    public String getName() { return name; }

    public abstract String getType();
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) { super(name); }

    public String getType() { return "Full-time"; }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) { super(name); }

    public String getType() { return "Part-time"; }
}

class ContractEmployee extends Employee {
    public ContractEmployee(String name) { super(name); }

    public String getType() { return "Contract"; }
}

class LeaveRequest {
    private Employee employee;
    private LocalDate start;
    private LocalDate end;
    private LeaveStatus status = LeaveStatus.PENDING;

    public LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
        this.employee = employee;
        this.start = start;
        this.end = end;
        System.out.println("Leave request submitted by " + employee.getName() + " for " + start + " to " + end + ". Status: Pending.");
    }

    public boolean changeStatus(LeaveStatus newStatus) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change status: " + capitalize(status) + " request cannot revert to " + capitalize(newStatus) + ".");
            return false;
        }
        status = newStatus;
        String verb = newStatus == LeaveStatus.APPROVED ? "approved" : "rejected";
        System.out.println("Leave request for " + employee.getName() + " " + verb + ". Status: " + capitalize(newStatus) + ".");
        return true;
    }

    private String capitalize(LeaveStatus s) {
        String name = s.toString();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}

class LeaveManager {
    public void review(LeaveRequest request, LeaveStatus decision) {
        request.changeStatus(decision);
    }
}

public class EmployeeLeaveManagement {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John Doe");
        Employee jane = new PartTimeEmployee("Jane Smith");
        LeaveManager manager = new LeaveManager();

        LeaveRequest johnRequest = new LeaveRequest(john, LocalDate.of(2024, 10, 10), LocalDate.of(2024, 10, 12));
        manager.review(johnRequest, LeaveStatus.APPROVED);

        new LeaveRequest(jane, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5));

        johnRequest.changeStatus(LeaveStatus.PENDING);
    }
}
