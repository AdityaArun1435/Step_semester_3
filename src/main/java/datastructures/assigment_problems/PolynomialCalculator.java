package datastructures.assigment_problems;

public class PolynomialCalculator {
    static class Node {
        int coef, exp;
        Node next;
        Node(int coef, int exp) {
            this.coef = coef;
            this.exp = exp;
        }
    }

    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exp > q.exp) {
                tail.next = new Node(p.coef, p.exp);
                tail = tail.next;
                p = p.next;
            } else if (q.exp > p.exp) {
                tail.next = new Node(q.coef, q.exp);
                tail = tail.next;
                q = q.next;
            } else {
                int sum = p.coef + q.coef;
                if (sum != 0) {
                    tail.next = new Node(sum, p.exp);
                    tail = tail.next;
                }
                p = p.next;
                q = q.next;
            }
        }

        while (p != null) {
            tail.next = new Node(p.coef, p.exp);
            tail = tail.next;
            p = p.next;
        }
        while (q != null) {
            tail.next = new Node(q.coef, q.exp);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    static Node build(int[][] terms) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;
        for (int[] t : terms) {
            tail.next = new Node(t[0], t[1]);
            tail = tail.next;
        }
        return dummy.next;
    }

    static void print(Node head) {
        if (head == null) {
            System.out.println("0");
            return;
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Node cur = head; cur != null; cur = cur.next) {
            int c = cur.coef;
            if (first) {
                if (c < 0) sb.append("-");
            } else {
                sb.append(c < 0 ? " - " : " + ");
            }
            int abs = Math.abs(c);
            if (cur.exp == 0) {
                sb.append(abs);
            } else {
                if (abs != 1) sb.append(abs);
                sb.append("x");
                if (cur.exp != 1) sb.append("^").append(cur.exp);
            }
            first = false;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Node p1 = build(new int[][]{{5, 3}, {4, 2}, {2, 0}});
        Node q1 = build(new int[][]{{5, 2}, {-3, 1}, {1, 0}});
        print(addPolynomials(p1, q1));   // 5x^3 + 9x^2 - 3x + 3

        Node p2 = build(new int[][]{{3, 2}, {1, 0}});
        Node q2 = build(new int[][]{{-3, 2}, {4, 1}});
        print(addPolynomials(p2, q2));   // 4x + 1
    }
}
