package string.class_problems;

import java.util.Scanner;

public class UniqueLetterFinder {

    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[256];

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            frequency[(int) currentChar]++;
        }

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (frequency[(int) currentChar] == 1) {
                return currentChar;
            }
        }

        throw new RuntimeException("No Non-Repeating Character Found");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word or sentence: ");
        String inputText = scanner.nextLine();

        try {
            char result = findFirstNonRepeatingChar(inputText);
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
