package inheritance.assigment_problems;

class RaceEntry {
    protected double balanceDue;
    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        this.balanceDue = entryFee;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    protected void applyLateFee(double amount) {
        balanceDue += amount;
        lateFeeHistory[lateFeeCount] = amount;
        lateFeeCount++;
    }

    public double[] getLateFeeHistory() {
        double[] copy = new double[lateFeeCount];
        for (int i = 0; i < lateFeeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }
        return copy;
    }
}

class RunnerEntry extends RaceEntry {
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LateWithdrawalAudit {
    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println(java.util.Arrays.toString(history));
        history[0] = 999;
        System.out.println(java.util.Arrays.toString(r.getLateFeeHistory()));
    }
}
