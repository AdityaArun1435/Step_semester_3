package inheritance.class_problems;

class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().isEmpty() || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid attendeeId");
        }
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public void pay(double amount) {
        balanceDue -= amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + balanceDue;
    }
}

class WorkshopTicket extends EventTicket {
    protected String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: " + track + " | Balance Due: " + balanceDue;
    }
}

class PremiumWorkshopTicket extends WorkshopTicket {
    private double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    public String printTicket() {
        return "Premium Workshop Ticket | Track: " + track + " | Kit Fee: " + kitFee + " | Balance Due: " + balanceDue;
    }
}

class HackathonTicket extends EventTicket {
    private String teamName;

    public HackathonTicket(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    public String printTicket() {
        return "Hackathon Ticket | Team: " + teamName + " | Balance Due: " + balanceDue;
    }
}

public class TicketFamilyTree {
    static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        } else if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        } else if (ticket instanceof WorkshopTicket) {
            return "Direct child";
        } else {
            return "Base ticket";
        }
    }

    static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0;
        for (EventTicket t : tickets) {
            total += t.getBalanceDue();
        }
        return total;
    }

    public static void main(String[] args) {
        EventTicket standardTicket = new EventTicket("STU1", 500);
        WorkshopTicket workshopTicket = new WorkshopTicket("STU2", 1200, "AI/ML");
        PremiumWorkshopTicket premiumTicket = new PremiumWorkshopTicket("STU3", 2000, "Cloud Native", 300);
        HackathonTicket hackathonTicket = new HackathonTicket("STU4", 800, "Byte Force");

        System.out.println(standardTicket.printTicket());
        System.out.println(workshopTicket.printTicket());
        System.out.println(premiumTicket.printTicket());
        System.out.println(hackathonTicket.printTicket());

        System.out.println(classifyGeneration(premiumTicket));
        System.out.println(classifyGeneration(hackathonTicket));

        System.out.println(getTotalBalanceDue(new EventTicket[]{standardTicket, workshopTicket, premiumTicket, hackathonTicket}));
    }
}
