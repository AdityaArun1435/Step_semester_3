package constructors.class_problems;

public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("ticketFare and minutesLate must not be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penalty = 0.0;

        int tier1Minutes = Math.min(minutesLate, 5);
        penalty += tier1Minutes * 0.005 * ticketFare;

        if (minutesLate > 5) {
            int tier2Minutes = Math.min(minutesLate, 15) - 5;
            penalty += tier2Minutes * 0.01 * ticketFare;
        }

        if (minutesLate > 15) {
            int tier3Minutes = minutesLate - 15;
            penalty += tier3Minutes * 0.02 * ticketFare;
        }

        double minimumFloor = minimumPenaltyPercent / 100.0 * ticketFare;
        if (penalty < minimumFloor) {
            penalty = minimumFloor;
        }

        return penalty;
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calculator = new BoardingPenaltyCalculator(1);

        System.out.println("Rs " + calculator.calculatePenalty(1000, 0));
        System.out.println("Rs " + calculator.calculatePenalty(1000, 1));
        System.out.println("Rs " + calculator.calculatePenalty(1000, 16));
    }
}
