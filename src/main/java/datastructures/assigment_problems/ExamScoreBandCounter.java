package datastructures.assigment_problems;

public class ExamScoreBandCounter {
    public static int countInBand(int[] scores, int low, int high) {
        int firstAtLeastLow = lowerBound(scores, low);
        int firstAboveHigh = lowerBound(scores, high + 1);
        return firstAboveHigh - firstAtLeastLow;
    }

    private static int lowerBound(int[] scores, int target) {
        int lo = 0, hi = scores.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (scores[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores, 42, 58)); // 6
        System.out.println(countInBand(scores, 90, 100)); // 0
    }
}
