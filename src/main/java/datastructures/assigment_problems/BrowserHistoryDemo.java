package datastructures.assigment_problems;

public class BrowserHistoryDemo {
    static class Node {
        String data;
        Node prev, next;
        Node(String data) { this.data = data; }
    }

    static class BrowserHistory {
        Node current;

        BrowserHistory(String home) {
            current = new Node(home);
        }

        void visit(String url) {
            Node n = new Node(url);
            current.next = n;   // old forward nodes are dropped here
            n.prev = current;
            current = n;
        }

        String back(int steps) {
            while (steps > 0 && current.prev != null) {
                current = current.prev;
                steps--;
            }
            return current.data;
        }

        String forward(int steps) {
            while (steps > 0 && current.next != null) {
                current = current.next;
                steps--;
            }
            return current.data;
        }
    }

    public static void main(String[] args) {
        BrowserHistory h = new BrowserHistory("srm.edu");
        h.visit("a.com");
        h.visit("b.com");
        h.visit("c.com");
        System.out.println(h.back(1));      // b.com
        System.out.println(h.back(1));      // a.com
        System.out.println(h.forward(1));   // b.com
        h.visit("d.com");
        System.out.println(h.forward(2));   // d.com
        System.out.println(h.back(2));      // a.com
        System.out.println(h.back(7));      // srm.edu
    }
}
