package string.assigment_problems;

public class TrafficSignalStreakAnalyzer {

    public static void findLongestStreak(String signalLog) {
        char longestStreakColor = signalLog.charAt(0);
        int longestStreakLength = 1;

        char currentColor = signalLog.charAt(0);
        int currentStreakLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentColor) {
                currentStreakLength++;
            } else {
                currentColor = signalLog.charAt(i);
                currentStreakLength = 1;
            }

            if (currentStreakLength > longestStreakLength) {
                longestStreakLength = currentStreakLength;
                longestStreakColor = currentColor;
            }
        }

        System.out.println("Longest Streak: '" + longestStreakColor + "' repeated " + longestStreakLength + " times");
    }

    public static void main(String[] args) {
        String signalLog = "RRGGGYRR";
        findLongestStreak(signalLog);
    }
}
