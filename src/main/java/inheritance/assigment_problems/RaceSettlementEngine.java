package inheritance.assigment_problems;

class RaceEntry {
    private static int counter = 0;
    private final String entryCode;
    protected double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {
        counter++;
        this.entryCode = "ENT-" + counter;
        this.balanceDue = entryFee;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    static int getBibCounter() {
        return counter;
    }

    static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'M') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2)) || !Character.isDigit(code.charAt(3))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }
        return true;
    }
}

class EliteRunnerEntry extends RaceEntry {
    public EliteRunnerEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}

public class RaceSettlementEngine {
    static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry e : entries) {
            if (e == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (e instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + relay + " relay | " + individual + " individual";
    }

    public static void main(String[] args) {
        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));

        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150);
        eliteEntry.pay(10, "UPI");

        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(settleNight(new RaceEntry[]{eliteEntry, null, relayEntry}));
        System.out.println(RaceEntry.getBibCounter());
    }
}
