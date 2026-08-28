package oop.class_problems;

class FeeAccountCap {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccountCap(String regNo, double totalFee, double amountPaid) {
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

class HostelFeeAccountCap extends FeeAccountCap {

    public HostelFeeAccountCap(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }
}

class HostelRoomCap {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoomCap(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public void allot(String name) {
        if (occupied < beds) {
            occupied++;
        }
    }
}

class SrmStudentCap {
    String name;
    String regNo;
    HostelFeeAccountCap feeAccount;
    HostelRoomCap room;

    static int totalStudents = 0;

    public SrmStudentCap(String name, String regNo, HostelFeeAccountCap feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null;
        totalStudents++;
    }

    public String fullStatus() {
        String roomInfo = (room == null) ? "unallotted" : room.roomNo;
        return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomInfo;
    }
}

public class FeeHostelMiniSystem {

    public static void main(String[] args) {
        HostelFeeAccountCap raviAccount = new HostelFeeAccountCap("RA001", 200000, 60000);
        HostelFeeAccountCap anithaAccount = new HostelFeeAccountCap("RA002", 180000, 0);
        HostelFeeAccountCap karthikAccount = new HostelFeeAccountCap("RA003", 200000, 0);

        SrmStudentCap ravi = new SrmStudentCap("Ravi", "RA001", raviAccount);
        SrmStudentCap anitha = new SrmStudentCap("Anitha", "RA002", anithaAccount);
        SrmStudentCap karthik = new SrmStudentCap("Karthik", "RA003", karthikAccount);

        HostelRoomCap roomC214 = new HostelRoomCap("C-214", 2, 0);
        HostelRoomCap roomC507 = new HostelRoomCap("C-507", 2, 0);

        roomC214.allot(ravi.name);
        ravi.room = roomC214;

        roomC507.allot(anitha.name);
        anitha.room = roomC507;

        ravi.feeAccount.pay(80000);
        anitha.feeAccount.pay(-5000);
        karthik.feeAccount.pay(0);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println("Total students: " + SrmStudentCap.totalStudents);
    }
}
