package datastructures.assigment_problems;

public class CancelKthLastOrder {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node lead = dummy;
        Node trail = dummy;

        for (int i = 0; i < k + 1; i++) {
            lead = lead.next;
        }

        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        trail.next = trail.next.next;
        return dummy.next;
    }

    static Node build(int[] values) {
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
        Node orders1 = build(new int[]{10, 20, 30, 40, 50});
        print(removeKthFromEnd(orders1, 2)); // 10 -> 20 -> 30 -> 50

        Node orders2 = build(new int[]{10, 20, 30, 40, 50});
        print(removeKthFromEnd(orders2, 5)); // 20 -> 30 -> 40 -> 50
    }
}
