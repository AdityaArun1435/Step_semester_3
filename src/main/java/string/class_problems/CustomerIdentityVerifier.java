package string.class_problems;

import java.util.Scanner;

public class CustomerIdentityVerifier {

    public static String reverseCustomerName(String customerName) {
        if (customerName.length() == 0) {
            throw new RuntimeException("Customer name cannot be empty");
        }

        char[] nameCharacters = customerName.toCharArray();
        String reversedName = "";

        for (int i = nameCharacters.length - 1; i >= 0; i--) {
            reversedName = reversedName + nameCharacters[i];
        }

        return reversedName;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        try {
            String reversedName = reverseCustomerName(customerName);
            System.out.println("Original Name: " + customerName);
            System.out.println("Reversed Name: " + reversedName);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
