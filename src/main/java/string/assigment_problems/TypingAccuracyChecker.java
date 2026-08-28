package string.assigment_problems;

public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int matchedCount = 0;
        int firstMismatchPosition = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matchedCount++;
            } else if (firstMismatchPosition == -1) {
                firstMismatchPosition = i + 1;
            }
        }

        double accuracy = (matchedCount * 100.0) / original.length();

        System.out.print("Matched: " + matchedCount + "/" + original.length() + " | Accuracy: " + accuracy + "% | ");

        if (firstMismatchPosition == -1) {
            System.out.println("No Mismatches");
        } else {
            System.out.println("First Mismatch at position " + firstMismatchPosition +
                    " ('" + original.charAt(firstMismatchPosition - 1) + "' vs '" + typed.charAt(firstMismatchPosition - 1) + "')");
        }
    }

    public static void main(String[] args) {
        String original = "hello world";
        String typed = "hello worlt";
        checkTypingAccuracy(original, typed);
    }
}
