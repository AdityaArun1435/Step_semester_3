package inheritance.class_problems;

class EventTicket {
    private static int counter = 1000;
    private final String ticketId;
    protected double balanceDue;

    public EventTicket(double basePrice) {
        counter++;
        this.ticketId = "TCK-" + counter;
        this.balanceDue = basePrice;
    }

    public String getTicketId() {
        return ticketId;
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

    static int getTicketsIssued() {
        return counter - 1000;
    }

    static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'F') {
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

class GroupTicket extends EventTicket {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }
}

public class TicketIssuanceEngine {
    static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (EventTicket t : tickets) {
            if (t == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (t instanceof GroupTicket) {
                group++;
            } else {
                individual++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        EventTicket t1 = new EventTicket(500);
        System.out.println(t1.getTicketId());
        System.out.println(EventTicket.getTicketsIssued());

        System.out.println(EventTicket.isValidPromoCode("F123A"));
        System.out.println(EventTicket.isValidPromoCode("F12A"));
        System.out.println(EventTicket.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");
        System.out.println(t1.getBalanceDue());

        System.out.println(processNightlySettlement(new EventTicket[]{
            new GroupTicket(2000, 5), null, new EventTicket(500)
        }));
    }
}
