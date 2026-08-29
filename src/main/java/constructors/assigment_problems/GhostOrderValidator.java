package constructors.assigment_problems;

class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (!isValidField(studentName) || !isValidField(dishName)) {
            throw new IllegalArgumentException("Invalid student name or dish name");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    private static boolean isValidField(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public void markDelivered() {
        if (delivered) {
            System.out.println(studentName + "'s order already marked delivered");
        } else {
            delivered = true;
            System.out.println(studentName + "'s order marked delivered");
        }
    }

    static void processBatch(String[][] rawOrders) {
        int validCount = 0;
        int rejectedCount = 0;

        for (int i = 0; i < rawOrders.length; i++) {
            String studentName = rawOrders[i][0];
            String dishName = rawOrders[i][1];

            try {
                new FoodOrder(studentName, dishName);
                validCount++;
            } catch (IllegalArgumentException e) {
                rejectedCount++;
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount);
    }
}

public class GhostOrderValidator {

    public static void main(String[] args) {
        String[][] rawOrders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "},
                {"Divya", "Veg Biryani"}
        };

        FoodOrder.processBatch(rawOrders);
    }
}
