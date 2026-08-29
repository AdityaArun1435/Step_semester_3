package constructors.assigment_problems;

class DeliveryAccount {
    protected static double minimumSurgePercent;

    static {
        minimumSurgePercent = 1.0;
    }

    protected String studentId;
    protected double orderValue;

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    final double calculateSurgeFee(int delayMinutes) {
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
}

class PremiumAccount extends DeliveryAccount {

    public PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public PremiumAccount(String studentId) {
        super(studentId);
    }
}

public class NightlyMultiKitchenReconciliationEngine {

    void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        double surgeFee = account.calculateSurgeFee(delayMinutes);
        System.out.println(account.studentId + " | Amount: " + amount + " | Surge Fee: " + surgeFee);
    }

    static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        NightlyMultiKitchenReconciliationEngine engine = new NightlyMultiKitchenReconciliationEngine();

        int processedCount = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurgeFees = 0;

        int length = accounts.length;
        if (amounts.length < length) {
            length = amounts.length;
        }
        if (delayMinutesArray.length < length) {
            length = delayMinutesArray.length;
        }

        for (int i = 0; i < length; i++) {
            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            engine.processAccount(account, amounts[i], delayMinutesArray[i]);
            grandTotalSurgeFees += account.calculateSurgeFee(delayMinutesArray[i]);
            processedCount++;

            if (account instanceof PremiumAccount) {
                premiumCount++;
            } else {
                regularCount++;
            }
        }

        System.out.println(processedCount + " processed | " + nullSkipped + " null skipped | " +
                premiumCount + " premium | " + regularCount + " regular | grand total surge fees = " + grandTotalSurgeFees);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
                new PremiumAccount("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}
