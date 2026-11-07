package datastructures.assigment_problems;

public class QuizScoringFormula {
    static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        return 2;
    }

    static int apply(int a, int b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        if (op == '*') return a * b;
        return a / b;
    }

    static int evaluate(String expr) {
        int n = expr.length();
        int[] nums = new int[n];
        char[] ops = new char[n];
        int numTop = -1;
        int opTop = -1;

        int i = 0;
        while (i < n) {
            char ch = expr.charAt(i);

            if (ch == ' ') {
                i++;
                continue;
            }

            if (ch >= '0' && ch <= '9') {
                int value = 0;
                while (i < n && expr.charAt(i) >= '0' && expr.charAt(i) <= '9') {
                    value = value * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                nums[++numTop] = value;
                continue;
            }

            if (ch == '(') {
                ops[++opTop] = ch;
            } else if (ch == ')') {
                while (ops[opTop] != '(') {
                    int b = nums[numTop--];
                    int a = nums[numTop--];
                    nums[++numTop] = apply(a, b, ops[opTop--]);
                }
                opTop--;
            } else {
                while (opTop >= 0 && ops[opTop] != '(' && precedence(ops[opTop]) >= precedence(ch)) {
                    int b = nums[numTop--];
                    int a = nums[numTop--];
                    nums[++numTop] = apply(a, b, ops[opTop--]);
                }
                ops[++opTop] = ch;
            }
            i++;
        }

        while (opTop >= 0) {
            int b = nums[numTop--];
            int a = nums[numTop--];
            nums[++numTop] = apply(a, b, ops[opTop--]);
        }
        return nums[numTop];
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));
        System.out.println(evaluate("(8 - 2) * (1 + 2) / 4"));
    }
}
