package string.assigment_problems;

import java.util.HashMap;
import java.util.ArrayList;

public class StopWordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        String cleanedText = feedback.toLowerCase();
        cleanedText = cleanedText.replace(".", "");
        cleanedText = cleanedText.replace(",", "");

        String[] words = cleanedText.split("\\s+");

        HashMap<String, Integer> wordCounts = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean isStopWord = false;

            for (int j = 0; j < stopWords.length; j++) {
                if (word.equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                if (wordCounts.containsKey(word)) {
                    wordCounts.put(word, wordCounts.get(word) + 1);
                } else {
                    wordCounts.put(word, 1);
                }
            }
        }

        ArrayList<String> uniqueWords = new ArrayList<>(wordCounts.keySet());

        for (int i = 0; i < uniqueWords.size(); i++) {
            for (int j = i + 1; j < uniqueWords.size(); j++) {
                int countI = wordCounts.get(uniqueWords.get(i));
                int countJ = wordCounts.get(uniqueWords.get(j));

                if (countJ > countI) {
                    String temp = uniqueWords.get(i);
                    uniqueWords.set(i, uniqueWords.get(j));
                    uniqueWords.set(j, temp);
                }
            }
        }

        for (int i = 0; i < uniqueWords.size(); i++) {
            String word = uniqueWords.get(i);
            System.out.println(word + ": " + wordCounts.get(word));
        }
    }

    public static void main(String[] args) {
        String feedback = "The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(feedback);
    }
}
