package datastructures.assigment_problems;

public class HackathonBannerPrinter {
    static String expandBanner(String code) {
        int n = code.length();
        int[] counts = new int[n];              // stack of repeat counts
        StringBuilder[] texts = new StringBuilder[n];   // stack of text built so far
        int top = -1;

        StringBuilder current = new StringBuilder();
        int k = 0;                              // number being read

        for (int i = 0; i < n; i++) {
            char ch = code.charAt(i);

            if (ch >= '0' && ch <= '9') {
                // numbers can have more than one digit
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // push the count and the text so far, start a fresh builder
                top++;
                counts[top] = k;
                texts[top] = current;
                current = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                // pop and append the current text to the popped builder count times
                int times = counts[top];
                StringBuilder previous = texts[top];
                top--;
                for (int t = 0; t < times; t++) {
                    previous.append(current);
                }
                current = previous;
            } else {
                // plain letter
                current.append(ch);
            }
        }
        return current.toString();
    }

    public static void main(String[] args) {
        System.out.println(expandBanner("3[ab]2[c]"));    // abababcc
        System.out.println(expandBanner("2[x3[y]]z"));    // xyyyxyyyz
    }
}
