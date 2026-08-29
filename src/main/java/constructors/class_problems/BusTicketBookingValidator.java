package constructors.class_problems;

import java.util.ArrayList;

class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (!isValidField(passengerName) || !isValidField(destination)) {
            throw new IllegalArgumentException("Invalid passenger name or destination");
        }
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    private static boolean isValidField(String value) {
        if (value == null) {
            return false;
        }
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            return false;
        }
        for (int i = 0; i < trimmed.length(); i++) {
            char currentChar = trimmed.charAt(i);
            if (!Character.isLetter(currentChar) && currentChar != ' ') {
                return false;
            }
        }
        return true;
    }

    public void markCheckedIn() {
        if (checkedIn) {
            System.out.println(passengerName + " is already checked in");
        } else {
            checkedIn = true;
            System.out.println(passengerName + " checked in successfully");
        }
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }

    static void processBatch(String[][] rawBookings) {
        int validCount = 0;
        int rejectedCount = 0;
        int duplicateCount = 0;
        ArrayList<String> acceptedPairs = new ArrayList<>();

        for (int i = 0; i < rawBookings.length; i++) {
            String name = rawBookings[i][0];
            String destination = rawBookings[i][1];

            try {
                BusTicket ticket = new BusTicket(name, destination);
                String pairKey = ticket.getPassengerName() + "|" + ticket.getDestination();

                if (acceptedPairs.contains(pairKey)) {
                    duplicateCount++;
                } else {
                    acceptedPairs.add(pairKey);
                    validCount++;
                }
            } catch (IllegalArgumentException e) {
                rejectedCount++;
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount + " | Duplicates skipped: " + duplicateCount);
    }
}

public class BusTicketBookingValidator {

    public static void main(String[] args) {
        String[][] rawBookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        BusTicket.processBatch(rawBookings);
    }
}
