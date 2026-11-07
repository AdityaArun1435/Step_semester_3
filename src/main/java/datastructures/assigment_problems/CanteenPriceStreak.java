package datastructures.assigment_problems;

public class CanteenPriceStreak {
    static int[] priceStreak(int[] price) {
        int n = price.length;
        int[] streak = new int[n];
        int[] stack = new int[n];   // holds indices of earlier days
        int top = -1;

        for (int i = 0; i < n; i++) {
            // pop every earlier day whose price is <= today's price
            while (top >= 0 && price[stack[top]] <= price[i]) {
                top--;
            }

            // empty stack: today is the highest so far, streak covers all days
            if (top == -1) {
                streak[i] = i + 1;
            } else {
                // distance from today back to the day left on top
                streak[i] = i - stack[top];
            }

            // push today
            stack[++top] = i;
        }
        return streak;
    }

    static void print(int[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        print(priceStreak(new int[]{100, 80, 60, 70, 60, 75, 85}));   // [1, 1, 1, 2, 1, 4, 6]
        print(priceStreak(new int[]{10, 20, 30}));                     // [1, 2, 3]
    }
}
