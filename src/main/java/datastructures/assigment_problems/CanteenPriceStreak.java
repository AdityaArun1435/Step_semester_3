package datastructures.assigment_problems;

public class CanteenPriceStreak {
    static int[] priceStreak(int[] price) {
        int n = price.length;
        int[] streak = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            while (top >= 0 && price[stack[top]] <= price[i]) {
                top--;
            }

            if (top == -1) {
                streak[i] = i + 1;
            } else {
                streak[i] = i - stack[top];
            }

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
        print(priceStreak(new int[]{100, 80, 60, 70, 60, 75, 85}));
        print(priceStreak(new int[]{10, 20, 30}));
    }
}
