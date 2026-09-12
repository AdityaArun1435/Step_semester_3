package inheritance.class_problems;

class EventTicket {
    protected double balanceDue;

    public EventTicket(double basePrice) {
        this.balanceDue = basePrice;
    }

    public String printTicket() {
        return "Standard | Balance: " + balanceDue;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: " + track + " | Balance: " + balanceDue;
    }

    public String getTrack() {
        return track;
    }
}

public class NightlyAnnouncer {
    static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();
        for (EventTicket t : tickets) {
            sb.append(t.printTicket());
            if (t instanceof WorkshopTicket) {
                WorkshopTicket wt = (WorkshopTicket) t;
                sb.append(" [Track via downcast: ").append(wt.getTrack()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(batchPrint(new EventTicket[]{
            new EventTicket(500),
            new WorkshopTicket(1200, "AI/ML")
        }));
    }
}
