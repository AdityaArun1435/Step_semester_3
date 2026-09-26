package design.class_problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Room {
    private String name;
    private List<Reservation> reservations = new ArrayList<>();

    public Room(String name) { this.name = name; }

    public String getName() { return name; }

    public abstract double getRatePerDay();

    public boolean isAvailable(LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.isActive() && r.overlaps(start, end)) return false;
        }
        return true;
    }

    public void addReservation(Reservation r) { reservations.add(r); }
}

class StandardRoom extends Room {
    public StandardRoom(String name) { super(name); }

    public double getRatePerDay() { return 150.0; }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String name) { super(name); }

    public double getRatePerDay() { return 200.0; }
}

class SuiteRoom extends Room {
    public SuiteRoom(String name) { super(name); }

    public double getRatePerDay() { return 350.0; }
}

class Reservation {
    private Room room;
    private LocalDate start;
    private LocalDate end;
    private double price;
    private boolean cancelled = false;
    private LocalDate cancellationDeadline;

    public Reservation(Room room, LocalDate start, LocalDate end) {
        this.room = room;
        this.start = start;
        this.end = end;
        long days = ChronoUnit.DAYS.between(start, end);
        this.price = days * room.getRatePerDay();
        this.cancellationDeadline = start.minusDays(1);
    }

    public boolean overlaps(LocalDate otherStart, LocalDate otherEnd) {
        return start.isBefore(otherEnd) && otherStart.isBefore(end);
    }

    public boolean isActive() { return !cancelled; }

    public double getPrice() { return price; }

    public Room getRoom() { return room; }

    public boolean cancel(LocalDate today) {
        if (today.isAfter(cancellationDeadline)) return false;
        cancelled = true;
        return true;
    }
}

class BookingManager {
    public Reservation book(Room room, LocalDate start, LocalDate end) {
        if (!room.isAvailable(start, end)) {
            System.out.println("Booking failed: " + room.getName() + " is not available for " + start + " to " + end + ".");
            return null;
        }
        Reservation reservation = new Reservation(room, start, end);
        room.addReservation(reservation);
        System.out.printf("%s booked from %s to %s. Total price: $%.2f%n", room.getName(), start, end, reservation.getPrice());
        return reservation;
    }

    public void cancel(Reservation reservation, LocalDate today) {
        if (reservation == null) return;
        if (reservation.cancel(today)) {
            System.out.println("Reservation for " + reservation.getRoom().getName() + " cancelled successfully.");
        } else {
            System.out.println("Cannot cancel: past cancellation deadline.");
        }
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Room deluxe101 = new DeluxeRoom("Deluxe Room 101");
        Room standard205 = new StandardRoom("Standard Room 205");
        BookingManager manager = new BookingManager();

        Reservation r1 = manager.book(deluxe101, LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5));
        manager.book(standard205, LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));
        manager.book(deluxe101, LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));
        manager.cancel(r1, LocalDate.of(2024, 11, 1));
    }
}
