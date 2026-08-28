package string.class_problems;

public class VowelConsonantCounter {

    public static void countVowelsAndConsonants(String text) {
        int vowelCount = 0;
        int consonantCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = Character.toLowerCase(text.charAt(i));

            if (currentChar == ' ') {
                continue;
            } else if (currentChar == 'a' || currentChar == 'e' || currentChar == 'i' || currentChar == 'o' || currentChar == 'u') {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        System.out.println("Vowels: " + vowelCount + " | Consonants: " + consonantCount);
    }

    public static void main(String[] args) {
        String text = "Java Programming";
        countVowelsAndConsonants(text);
    }
}
