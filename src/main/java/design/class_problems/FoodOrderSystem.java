package design.class_problems;

import java.util.*;

interface PaymentMethod {
    boolean pay(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {
    public boolean pay(double amount) { return true; }

    public String getName() { return "Credit Card"; }
}

class DigitalWalletPayment implements PaymentMethod {
    private boolean simulateFailure;

    public DigitalWalletPayment(boolean simulateFailure) { this.simulateFailure = simulateFailure; }

    public boolean pay(double amount) { return !simulateFailure; }

    public String getName() { return "Digital Wallet"; }
}

class CashOnDeliveryPayment implements PaymentMethod {
    public boolean pay(double amount) { return true; }

    public String getName() { return "Cash on Delivery"; }
}

class LineItem {
    private String itemName;
    private int quantity;

    public LineItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String describe() { return itemName + " (Qty " + quantity + ")"; }
}

class Order {
    private static int nextId = 123;
    private int id;
    private List<LineItem> items = new ArrayList<>();
    private String status = "New";

    public Order() { this.id = nextId++; }

    public void addItem(String name, int qty) { items.add(new LineItem(name, qty)); }

    public String itemsSummary() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i).describe());
            if (i < items.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    public boolean place() {
        if (items.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
            return false;
        }
        status = "Pending Payment";
        System.out.println("Order placed.");
        return true;
    }

    public void pay(PaymentMethod method) {
        boolean success = method.pay(0);
        if (success) {
            status = "Paid";
            System.out.println("Payment via " + method.getName() + " successful. Order status: Paid.");
            System.out.println("Notification: Order #" + id + " placed and paid.");
        } else {
            System.out.println("Payment via " + method.getName() + " failed. Order status: Pending Payment.");
            System.out.println("Notification: Order #" + id + " placed, awaiting payment.");
        }
    }
}

public class FoodOrderSystem {
    public static void main(String[] args) {
        Order order1 = new Order();
        order1.addItem("Pizza", 2);
        order1.addItem("Soda", 1);
        System.out.println("Order created. Added " + order1.itemsSummary() + ".");

        new Order().place();

        order1.place();
        order1.pay(new CreditCardPayment());

        Order order2 = new Order();
        order2.addItem("Burger", 1);
        order2.place();
        order2.pay(new DigitalWalletPayment(true));
    }
}
