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
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}

public class TicketRegistration {
    static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;
        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());

        System.out.println(registerBatch(new String[]{"STU1", "ST1", "STU2", " ", "STU3"}, 500));
    }
}
