package design.assigment_problems;

import java.util.*;

enum ParcelStatus { BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, CANCELLED }

interface ShippingType {
    double calculateCharge(double weightKg);
    String getName();
}

class StandardShipping implements ShippingType {
    public double calculateCharge(double weightKg) { return 40 + 10 * weightKg; }

    public String getName() { return "Standard"; }
}

class ExpressShipping implements ShippingType {
    public double calculateCharge(double weightKg) { return 80 + 15 * weightKg; }

    public String getName() { return "Express"; }
}

class FragileShipping implements ShippingType {
    private StandardShipping standard = new StandardShipping();

    public double calculateCharge(double weightKg) { return standard.calculateCharge(weightKg) + 50; }

    public String getName() { return "Fragile"; }
}

interface NotificationChannel {
    void notify(String parcelId, ParcelStatus status);
}

class SmsChannel implements NotificationChannel {
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}

class Parcel {
    private static final Map<ParcelStatus, ParcelStatus> NEXT = new HashMap<>();
    static {
        NEXT.put(ParcelStatus.BOOKED, ParcelStatus.PICKED_UP);
        NEXT.put(ParcelStatus.PICKED_UP, ParcelStatus.IN_TRANSIT);
        NEXT.put(ParcelStatus.IN_TRANSIT, ParcelStatus.OUT_FOR_DELIVERY);
        NEXT.put(ParcelStatus.OUT_FOR_DELIVERY, ParcelStatus.DELIVERED);
    }

    private String id;
    private double weightKg;
    private ParcelStatus status = ParcelStatus.BOOKED;
    private List<NotificationChannel> channels = new ArrayList<>();
    private double charge;

    public Parcel(String id, ShippingType shippingType, double weightKg) {
        this.id = id;
        this.weightKg = weightKg;
        this.charge = shippingType.calculateCharge(weightKg);
    }

    public void subscribe(NotificationChannel channel) { channels.add(channel); }

    public double getCharge() { return charge; }

    public ParcelStatus getStatus() { return status; }

    public void announceBooked() {
        for (NotificationChannel c : channels) c.notify(id, ParcelStatus.BOOKED);
    }

    public void advance(ParcelStatus target) {
        ParcelStatus expected = NEXT.get(status);
        if (expected != target) {
            System.out.println("Invalid transition: " + status + " -> " + target + " is not allowed.");
            return;
        }
        status = target;
        for (NotificationChannel c : channels) c.notify(id, status);
    }

    public void cancel() {
        if (status != ParcelStatus.BOOKED) {
            System.out.println("Cancellation failed: " + id + " can be cancelled only while BOOKED.");
            return;
        }
        status = ParcelStatus.CANCELLED;
        System.out.println(id + " cancelled.");
    }
}

class ParcelService {
    public Parcel book(String id, ShippingType shippingType, double weightKg) {
        Parcel parcel = new Parcel(id, shippingType, weightKg);
        System.out.printf("Parcel %s booked (%s, %.0f kg). Charge: ?%.2f%n", id, shippingType.getName(), weightKg, parcel.getCharge());
        return parcel;
    }
}

public class SwiftShipParcelTracker {
    public static void main(String[] args) {
        ParcelService service = new ParcelService();
        Parcel p101 = service.book("P101", new ExpressShipping(), 2);
        p101.subscribe(new SmsChannel());
        p101.subscribe(new EmailChannel());
        p101.announceBooked();

        p101.advance(ParcelStatus.PICKED_UP);
        p101.cancel();
        p101.advance(ParcelStatus.IN_TRANSIT);
        p101.advance(ParcelStatus.DELIVERED);
    }
}
