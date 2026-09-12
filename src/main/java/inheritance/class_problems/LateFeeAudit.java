package inheritance.class_problems;

class EventTicket {
    protected double balanceDue;
    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public EventTicket(double basePrice) {
        this.balanceDue = basePrice;
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

class WorkshopTicket extends EventTicket {
    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LateFeeAudit {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        System.out.println(java.util.Arrays.toString(history));
        history[0] = 999;
        System.out.println(java.util.Arrays.toString(w.getLateFeeHistory()));
    }
}
