package datastructures.assigment_problems;

import java.util.*;

public class NetBalancePeriodCounter {
    public static int countPeriods(int[] transactions, int k) {
        Map<Integer, Integer> prefixCounts = new HashMap<>();
        prefixCounts.put(0, 1);

        int sum = 0;
        int count = 0;
        for (int t : transactions) {
            sum += t;
            count += prefixCounts.getOrDefault(sum - k, 0);
            prefixCounts.put(sum, prefixCounts.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] transactions1 = {3, 4, -7, 1, 3, 3, 1, -4};
        System.out.println(countPeriods(transactions1, 7)); // 4

        int[] transactions2 = {1, 2, 3};
        System.out.println(countPeriods(transactions2, 10)); // 0
    }
}
