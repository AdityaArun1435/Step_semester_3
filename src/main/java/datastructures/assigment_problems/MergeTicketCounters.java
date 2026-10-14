package datastructures.assigment_problems;

public class MergeTicketCounters {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static Node mergeSorted(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        if (a != null) tail.next = a;
        else tail.next = b;

        return dummy.next;
    }

    static Node build(int[] values) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node cur = head;
        for (int i = 1; i < values.length; i++) {
            cur.next = new Node(values[i]);
            cur = cur.next;
        }
        return head;
    }

    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data);
            if (cur.next != null) sb.append(" -> ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Node a = build(new int[]{3, 8, 15});
        Node b = build(new int[]{5, 8, 12, 20});
        print(mergeSorted(a, b));   // 3 -> 5 -> 8 -> 8 -> 12 -> 15 -> 20

        print(mergeSorted(null, build(new int[]{4})));   // 4
    }
}
