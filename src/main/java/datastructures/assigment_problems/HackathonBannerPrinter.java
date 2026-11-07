package datastructures.assigment_problems;

public class HackathonBannerPrinter {
    static String expandBanner(String code) {
        int n = code.length();
        int[] counts = new int[n];
        StringBuilder[] texts = new StringBuilder[n];
        int top = -1;

        StringBuilder current = new StringBuilder();
        int k = 0;

        for (int i = 0; i < n; i++) {
            char ch = code.charAt(i);

            if (ch >= '0' && ch <= '9') {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                top++;
                counts[top] = k;
                texts[top] = current;
                current = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                int times = counts[top];
                StringBuilder previous = texts[top];
                top--;
                for (int t = 0; t < times; t++) {
                    previous.append(current);
                }
                current = previous;
            } else {
                current.append(ch);
            }
        }
        return current.toString();
    }

    public static void main(String[] args) {
        System.out.println(expandBanner("3[ab]2[c]"));
        System.out.println(expandBanner("2[x3[y]]z"));
    }
}
