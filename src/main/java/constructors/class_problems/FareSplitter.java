package constructors.class_problems;

public class FareSplitter {
    private static final int DEFAULT_PASSENGER_COUNT = 2;
    private static final double DEFAULT_FARE = 0;

    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, DEFAULT_PASSENGER_COUNT);
    }

    public FareSplitter(String tripId) {
        this(tripId, DEFAULT_FARE);
    }

    double[] fareBreakdown() {
        long totalCents = Math.round(totalFare * 100);
        long baseCents = totalCents / passengerCount;
        long remainder = totalCents % passengerCount;

        double[] shares = new double[passengerCount];
        for (int i = 0; i < passengerCount; i++) {
            long cents = baseCents;
            if (i >= passengerCount - remainder) {
                cents = cents + 1;
            }
            shares[i] = cents / 100.0;
        }
        return shares;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        FareSplitter fullSplit = new FareSplitter("TRIP001", 100000, 3);
        double[] breakdown1 = fullSplit.fareBreakdown();
        for (int i = 0; i < breakdown1.length; i++) {
            System.out.print(breakdown1[i] + " ");
        }
        System.out.println();

        FareSplitter provisionalSplit = new FareSplitter("TRIP003");
        double[] breakdown2 = provisionalSplit.fareBreakdown();
        for (int i = 0; i < breakdown2.length; i++) {
            System.out.print(breakdown2[i] + " ");
        }
        System.out.println();
    }
}
