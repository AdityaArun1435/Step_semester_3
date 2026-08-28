package string.assigment_problems;

public class MovieReviewWordLengthProfiler {

    public static void classifyWordLengths(String review) {
        String[] words = review.split(" ");

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (int i = 0; i < words.length; i++) {
            int wordLength = words[i].length();

            if (wordLength <= 4) {
                shortCount++;
            } else if (wordLength <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        String review = "This movie was absolutely fantastic and thrilling";
        classifyWordLengths(review);
    }
}
