package inheritance.assigment_problems;

class RaceEntry {
    protected String bibNumber;
    protected double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.balanceDue = entryFee;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + balanceDue;
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + bibNumber + " | Category: " + category + " | Balance: " + balanceDue;
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

    public int getTeamSize() {
        return teamSize;
    }
}

public class RaceDayAnnouncer {
    static String announceAll(RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();
        for (RaceEntry e : entries) {
            sb.append(e.announce());
            if (e instanceof RelayTeamEntry) {
                RelayTeamEntry rt = (RelayTeamEntry) e;
                sb.append(" [Team size via downcast: ").append(rt.getTeamSize()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RunnerEntry runnerEntry = new RunnerEntry("BIB2001", 90, "Open 10K");
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] fleet = {runnerEntry, relayEntry};
        System.out.println(announceAll(fleet));
    }
}
