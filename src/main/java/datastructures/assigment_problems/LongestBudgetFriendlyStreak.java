package datastructures.assigment_problems;

import java.util.*;

public class LongestBudgetFriendlyStreak {
    public static int[] longestStreak(int[] costs, long budget) {
        int n = costs.length;
        int left = 0;
        long sum = 0;
        int bestLength = 0, bestStart = -1;

        for (int right = 0; right < n; right++) {
            sum += costs[right];
            while (sum > budget) {
                sum -= costs[left];
                left++;
            }
            int windowLength = right - left + 1;
            if (windowLength > bestLength) {
                bestLength = windowLength;
                bestStart = left;
            }
        }

        if (bestLength == 0) return new int[]{0, -1};
        return new int[]{bestLength, bestStart};
    }

    public static void main(String[] args) {
        int[] costs1 = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        System.out.println(Arrays.toString(longestStreak(costs1, 8))); // [4, 4]

        int[] costs2 = {9, 10};
        System.out.println(Arrays.toString(longestStreak(costs2, 8))); // [0, -1]
    }
}
