package datastructures.assigment_problems;

public class BusBayRing {
    static class BusBay {
        int[] data;      // fixed-size array used as a ring buffer
        int capacity;
        int front;       // index of the front bus
        int size;        // number of buses in the bay

        BusBay(int capacity) {
            this.capacity = capacity;
            data = new int[capacity];
            front = 0;
            size = 0;
        }

        boolean insertFront(int bus) {
            if (isFull()) return false;
            front = (front - 1 + capacity) % capacity;   // move front backwards
            data[front] = bus;
            size++;
            return true;
        }

        boolean insertLast(int bus) {
            if (isFull()) return false;
            data[(front + size) % capacity] = bus;       // first free slot after the rear
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
            return data[(front + size - 1) % capacity];  // rear slot
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
        System.out.println(b.insertLast(101));   // true
        System.out.println(b.insertLast(102));   // true
        System.out.println(b.insertFront(100));  // true
        System.out.println(b.insertFront(99));   // false
        System.out.println(b.getRear());         // 102
        System.out.println(b.isFull());          // true
        System.out.println(b.deleteLast());      // true
        System.out.println(b.insertFront(98));   // true
        System.out.println(b.getFront());        // 98
    }
}
