package constructors.assigment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("orderValue and delayMinutes must not be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        int tier1Minutes = Math.min(delayMinutes, 5);
        fee += tier1Minutes * 0.005 * orderValue;

        if (delayMinutes > 5) {
            int tier2Minutes = Math.min(delayMinutes, 15) - 5;
            fee += tier2Minutes * 0.01 * orderValue;
        }

        if (delayMinutes > 15) {
            int tier3Minutes = delayMinutes - 15;
            fee += tier3Minutes * 0.02 * orderValue;
        }

        double minimumFloor = minimumSurgePercent / 100.0 * orderValue;
        if (fee < minimumFloor) {
            fee = minimumFloor;
        }

        return fee;
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1);

        System.out.println("Rs " + calculator.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calculator.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calculator.calculateSurgeFee(500, 16));
    }
}
