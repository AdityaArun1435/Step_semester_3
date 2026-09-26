package design.class_problems;

import java.util.*;

abstract class Vehicle {
    private String name;
    private boolean rented = false;

    public Vehicle(String name) { this.name = name; }

    public String getName() { return name; }

    public boolean isRented() { return rented; }

    public void setRented(boolean rented) { this.rented = rented; }

    public abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {
    public StandardCar(String name) { super(name); }

    public double calculateCharge(int days) { return days * 50.0; }
}

class LuxuryCar extends Vehicle {
    public LuxuryCar(String name) { super(name); }

    public double calculateCharge(int days) { return days * 100.0; }
}

class SUV extends Vehicle {
    public SUV(String name) { super(name); }

    public double calculateCharge(int days) { return days * 75.0; }
}

class Rental {
    private Vehicle vehicle;
    private double charge;

    public Rental(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.charge = vehicle.calculateCharge(days);
    }

    public Vehicle getVehicle() { return vehicle; }

    public double getCharge() { return charge; }
}

class RentalService {
    private List<Rental> activeRentals = new ArrayList<>();

    public void rent(Vehicle vehicle, int days) {
        if (vehicle.isRented()) {
            System.out.println(vehicle.getName() + " is not available, already rented.");
            return;
        }
        Rental rental = new Rental(vehicle, days);
        vehicle.setRented(true);
        activeRentals.add(rental);
        System.out.printf("%s rented for %d days. Total charge: $%.2f%n", vehicle.getName(), days, rental.getCharge());
    }

    public void returnVehicle(Vehicle vehicle) {
        vehicle.setRented(false);
        activeRentals.removeIf(r -> r.getVehicle() == vehicle);
        System.out.println(vehicle.getName() + " returned. Now available.");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle luxuryCarA = new LuxuryCar("Luxury Car A");
        Vehicle standardCarB = new StandardCar("Standard Car B");

        RentalService service = new RentalService();
        service.rent(luxuryCarA, 3);
        service.rent(standardCarB, 5);
        service.returnVehicle(luxuryCarA);
    }
}
