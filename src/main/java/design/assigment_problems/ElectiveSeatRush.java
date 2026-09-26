package design.assigment_problems;

import java.util.*;

interface CreditPolicy {
    int getCreditLimit();
}

class RegularPolicy implements CreditPolicy {
    public int getCreditLimit() { return 24; }
}

class HonorsPolicy implements CreditPolicy {
    public int getCreditLimit() { return 28; }
}

class ExchangePolicy implements CreditPolicy {
    public int getCreditLimit() { return 20; }
}

class Student {
    private String name;
    private CreditPolicy policy;
    private int currentCredits;
    private String typeName;

    public Student(String name, CreditPolicy policy, String typeName, int currentCredits) {
        this.name = name;
        this.policy = policy;
        this.typeName = typeName;
        this.currentCredits = currentCredits;
    }

    public String getName() { return name; }

    public int getCreditLimit() { return policy.getCreditLimit(); }

    public String getTypeName() { return typeName; }

    public int getCurrentCredits() { return currentCredits; }

    public void addCredits(int amount) { currentCredits += amount; }

    public void removeCredits(int amount) { currentCredits -= amount; }
}

class Elective {
    private String name;
    private int credits;
    private int capacity;
    private Set<Student> enrolled = new LinkedHashSet<>();
    private Queue<Student> waitlist = new LinkedList<>();

    public Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public String getName() { return name; }

    public int getCredits() { return credits; }

    public boolean hasSeat() { return enrolled.size() < capacity; }

    public boolean isTaken(Student s) { return enrolled.contains(s) || waitlist.contains(s); }

    public void enroll(Student s) { enrolled.add(s); }

    public void drop(Student s) { enrolled.remove(s); }

    public void addToWaitlist(Student s) { waitlist.add(s); }

    public int waitlistPosition(Student s) { return new ArrayList<>(waitlist).indexOf(s) + 1; }

    public Student pollWaitlist() { return waitlist.poll(); }
}

class EnrollmentService {
    public void enroll(Student s, Elective e) {
        if (e.isTaken(s)) {
            System.out.println(s.getName() + " is already enrolled in or waiting for " + e.getName() + ".");
            return;
        }
        int projected = s.getCurrentCredits() + e.getCredits();
        if (projected > s.getCreditLimit()) {
            System.out.printf("Enrollment failed: %s would exceed the %s credit limit (%d/%d).%n", s.getName(), s.getTypeName(), projected, s.getCreditLimit());
            return;
        }
        if (!e.hasSeat()) {
            e.addToWaitlist(s);
            System.out.println(e.getName() + " is full.");
            System.out.println(s.getName() + " added to waitlist (position " + e.waitlistPosition(s) + ").");
            return;
        }
        e.enroll(s);
        s.addCredits(e.getCredits());
        System.out.printf("%s enrolled in %s (credits: %d/%d).%n", s.getName(), e.getName(), s.getCurrentCredits(), s.getCreditLimit());
    }

    public void drop(Student s, Elective e) {
        e.drop(s);
        s.removeCredits(e.getCredits());
        System.out.printf("%s dropped %s (credits: %d/%d).%n", s.getName(), e.getName(), s.getCurrentCredits(), s.getCreditLimit());
        promoteFromWaitlist(e);
    }

    private void promoteFromWaitlist(Elective e) {
        while (e.hasSeat()) {
            Student next = e.pollWaitlist();
            if (next == null) return;
            int projected = next.getCurrentCredits() + e.getCredits();
            if (projected > next.getCreditLimit()) continue;
            e.enroll(next);
            next.addCredits(e.getCredits());
            System.out.printf("%s promoted from waitlist and enrolled in %s (credits: %d/%d).%n", next.getName(), e.getName(), next.getCurrentCredits(), next.getCreditLimit());
            return;
        }
    }
}

public class ElectiveSeatRush {
    public static void main(String[] args) {
        Elective cloudComputing = new Elective("Cloud Computing", 4, 2);
        EnrollmentService service = new EnrollmentService();

        Student asha = new Student("Asha", new RegularPolicy(), "Regular", 20);
        Student ravi = new Student("Ravi", new HonorsPolicy(), "Honors", 22);
        Student neha = new Student("Neha", new ExchangePolicy(), "Exchange", 12);
        Student kiran = new Student("Kiran", new RegularPolicy(), "Regular", 22);

        service.enroll(asha, cloudComputing);
        service.enroll(ravi, cloudComputing);
        service.enroll(neha, cloudComputing);
        service.enroll(kiran, cloudComputing);
        service.drop(asha, cloudComputing);
    }
}
