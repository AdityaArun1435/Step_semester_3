package inheritance.assigment_problems;

class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + balanceDue;
    }
}

class RunnerEntry extends RaceEntry {
    protected String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + bibNumber + " | Category: " + category + " | Balance: " + balanceDue;
    }
}

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + bibNumber + " | Category: " + category + " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + balanceDue;
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + bibNumber + " | Team Size: " + teamSize + " | Balance: " + balanceDue;
    }
}

public class RaceFamilyTree {
    static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else if (entry instanceof RunnerEntry) {
            return "Direct child";
        } else {
            return "Base entry";
        }
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;
        for (RaceEntry e : entries) {
            total += e.getBalanceDue();
        }
        return total;
    }

    public static void main(String[] args) {
        RunnerEntry runnerEntry = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(runnerEntry.announce());
        System.out.println(eliteEntry.announce());
        System.out.println(relayEntry.announce());

        System.out.println(classifyGeneration(eliteEntry));
        System.out.println(classifyGeneration(relayEntry));

        System.out.println(getTotalBalanceDue(new RaceEntry[]{runnerEntry, eliteEntry, relayEntry}));
    }
}
