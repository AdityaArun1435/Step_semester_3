package constructors.class_problems;

class BusTicketAccount {
    protected static double minimumPenaltyPercent;

    static {
        minimumPenaltyPercent = 1.0;
    }

    protected String bookingId;
    protected double ticketFare;

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    final double calculatePenalty(int minutesLate) {
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
}

class SleeperCoachAccount extends BusTicketAccount {

    public SleeperCoachAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public SleeperCoachAccount(String bookingId) {
        super(bookingId);
    }
}

public class NightlyReconciliationEngine {

    void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        double penalty = account.calculatePenalty(minutesLate);
        System.out.println(account.bookingId + " | Amount: " + amount + " | Penalty: " + penalty);
    }

    static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        NightlyReconciliationEngine engine = new NightlyReconciliationEngine();

        int processedCount = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalty = 0;

        int length = accounts.length;
        if (amounts.length < length) {
            length = amounts.length;
        }
        if (minutesLateArray.length < length) {
            length = minutesLateArray.length;
        }

        for (int i = 0; i < length; i++) {
            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            engine.processAccount(account, amounts[i], minutesLateArray[i]);
            grandTotalPenalty += account.calculatePenalty(minutesLateArray[i]);
            processedCount++;

            if (account instanceof SleeperCoachAccount) {
                sleeperCount++;
            } else {
                regularCount++;
            }
        }

        System.out.println(processedCount + " processed | " + nullSkipped + " null skipped | " +
                sleeperCount + " sleeper | " + regularCount + " regular | grand total penalties = " + grandTotalPenalty);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
                new SleeperCoachAccount("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}
