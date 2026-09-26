package design.assigment_problems;

import java.util.*;

interface PricingPlan {
    double applyPrice(double basePrice);
}

class DayScholarPlan implements PricingPlan {
    public double applyPrice(double basePrice) { return basePrice; }
}

class HostellerPlan implements PricingPlan {
    public double applyPrice(double basePrice) { return basePrice * 0.9; }
}

class StaffPlan implements PricingPlan {
    public double applyPrice(double basePrice) { return basePrice * 0.8; }
}

class Transaction {
    private String description;
    private double amount;
    private boolean refunded = false;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public double getAmount() { return amount; }

    public String getDescription() { return description; }

    public boolean isRefunded() { return refunded; }

    public void markRefunded() { refunded = true; }
}

class SmartCard {
    private static final double MIN_TOPUP = 100;
    private static final double MAX_BALANCE = 5000;

    private String id;
    private PricingPlan plan;
    private List<Transaction> transactions = new ArrayList<>();
    private boolean blocked = false;

    public SmartCard(String id, PricingPlan plan) {
        this.id = id;
        this.plan = plan;
    }

    public double getBalance() {
        double total = 0;
        for (Transaction t : transactions) total += t.getAmount();
        return total;
    }

    public void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up rejected: " + id + " is blocked.");
            return;
        }
        if (amount < MIN_TOPUP) {
            System.out.println("Top-up rejected: minimum top-up is ?" + MIN_TOPUP + ".");
            return;
        }
        if (getBalance() + amount > MAX_BALANCE) {
            System.out.println("Top-up rejected: would exceed maximum balance of ?" + MAX_BALANCE + ".");
            return;
        }
        transactions.add(new Transaction("Top-up", amount));
        System.out.printf("%s topped up with ?%.2f. Balance: ?%.2f%n", id, amount, getBalance());
    }

    public Transaction purchase(String item, double basePrice) {
        if (blocked) {
            System.out.println("Purchase rejected: " + id + " is blocked.");
            return null;
        }
        double price = plan.applyPrice(basePrice);
        if (price > getBalance()) {
            System.out.printf("Purchase failed: Insufficient balance (required ?%.2f, available ?%.2f).%n", price, getBalance());
            return null;
        }
        Transaction t = new Transaction(item, -price);
        transactions.add(t);
        System.out.printf("%s purchased for ?%.2f. Balance: ?%.2f%n", item, price, getBalance());
        return t;
    }

    public void refund(Transaction t) {
        if (t == null) return;
        if (t.isRefunded()) {
            System.out.println("Refund rejected: " + t.getDescription() + " has already been refunded.");
            return;
        }
        double amount = -t.getAmount();
        transactions.add(new Transaction("Refund: " + t.getDescription(), amount));
        t.markRefunded();
        System.out.printf("Refund of ?%.2f for %s processed. Balance: ?%.2f%n", amount, t.getDescription(), getBalance());
    }

    public void block() { blocked = true; }

    public void unblock() { blocked = false; }

    public void miniStatement() {
        List<String> parts = new ArrayList<>();
        for (Transaction t : transactions) {
            double amt = t.getAmount();
            parts.add((amt >= 0 ? "+" : "") + String.format("%.2f", amt));
        }
        System.out.printf("Mini-statement for %s: %s = ?%.2f%n", id, String.join(", ", parts), getBalance());
    }
}

public class CampusCanteenSmartCard {
    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());
        card.topUp(500);
        Transaction thali = card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Extra Combo", 400);
        card.refund(thali);
        card.refund(thali);
        card.miniStatement();
    }
}
