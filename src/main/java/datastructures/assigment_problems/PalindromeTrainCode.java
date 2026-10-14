package datastructures.assigment_problems;

public class PalindromeTrainCode {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    static Node reverse(Node head) {
        Node prev = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // find the end of the first half
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second half
        Node secondHalf = reverse(slow.next);

        // compare the two halves
        boolean result = true;
        Node p1 = head;
        Node p2 = secondHalf;
        while (p2 != null) {
            if (p1.data != p2.data) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // restore the list
        slow.next = reverse(secondHalf);

        return result;
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
        Node c1 = build(new int[]{1, 2, 3, 2, 1});
        System.out.println(isPalindrome(c1));   // true

        Node c2 = build(new int[]{1, 2, 3, 2});
        System.out.println(isPalindrome(c2));   // false

        Node c3 = build(new int[]{1, 2, 2, 1});
        System.out.println(isPalindrome(c3));   // true
        print(c3);                              // 1 -> 2 -> 2 -> 1
    }
}
