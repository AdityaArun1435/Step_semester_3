package datastructures.assigment_problems;

public class BusBayRing {
    static class BusBay {
        int[] data;
        int capacity;
        int front;
        int size;

        BusBay(int capacity) {
            this.capacity = capacity;
            data = new int[capacity];
            front = 0;
            size = 0;
        }

        boolean insertFront(int bus) {
            if (isFull()) return false;
            front = (front - 1 + capacity) % capacity;
            data[front] = bus;
            size++;
            return true;
        }

        boolean insertLast(int bus) {
            if (isFull()) return false;
            data[(front + size) % capacity] = bus;
            size++;
            return true;
        }

        boolean deleteFront() {
            if (isEmpty()) return false;
            front = (front + 1) % capacity;
            size--;
            return true;
        }

        boolean deleteLast() {
            if (isEmpty()) return false;
            size--;
            return true;
        }

        int getFront() {
            if (isEmpty()) return -1;
            return data[front];
        }

        int getRear() {
            if (isEmpty()) return -1;
            return data[(front + size - 1) % capacity];
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == capacity;
        }
    }

    public static void main(String[] args) {
        BusBay b = new BusBay(3);
        System.out.println(b.insertLast(101));
        System.out.println(b.insertLast(102));
        System.out.println(b.insertFront(100));
        System.out.println(b.insertFront(99));
        System.out.println(b.getRear());
        System.out.println(b.isFull());
        System.out.println(b.deleteLast());
        System.out.println(b.insertFront(98));
        System.out.println(b.getFront());
    }
}
