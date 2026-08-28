package oop.class_problems;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Payment rejected: amount must be positive");
        } else {
            amountPaid = amountPaid + amount;
        }
    }

    public double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    public void payInTwoInstallments(double amount) {
        double firstInstallment = amount / 2;
        double secondInstallment = amount - firstInstallment;
        pay(firstInstallment);
        pay(secondInstallment);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    public double effectiveDue() {
        double due = getDue();
        return due - (due * scholarshipPercent / 100);
    }
}

public class FeeAccountDemo {

    public static void main(String[] args) {
        FeeAccount plainAccount = new FeeAccount("RA001", 150000, 150000);
        HostelFeeAccount hostelAccount = new HostelFeeAccount("RA002", 200000, 60000);
        ScholarshipFeeAccount scholarshipAccount = new ScholarshipFeeAccount("RA003", 180000, 0, 20);

        FeeAccount[] accounts = {plainAccount, hostelAccount, scholarshipAccount};

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount scholarship = (ScholarshipFeeAccount) accounts[i];
                System.out.println("Scholarship account effective due: Rs " + scholarship.effectiveDue());
            } else if (accounts[i] instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + accounts[i].getDue());
            } else {
                System.out.println("Plain account due: Rs " + accounts[i].getDue());
            }
        }
    }
}
