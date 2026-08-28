package oop.class_problems;

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public void allot(String name) {
        if (occupied < beds) {
            occupied++;
        }
    }
}

public class HostelAllocationService {

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].occupied < rooms[i].beds) {
                return rooms[i];
            }
        }
        return null;
    }

    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom availableRoom = findAvailableRoom(rooms);

        if (availableRoom == null) {
            System.out.println("No rooms available for " + studentName);
        } else {
            availableRoom.allot(studentName);
            System.out.println(studentName + " allotted to room " + availableRoom.roomNo);
        }
    }

    public static void main(String[] args) {
        HostelRoom[] roomsWithSpace = {
                new HostelRoom("C-214", 3, 2),
                new HostelRoom("C-507", 2, 2)
        };
        safeAllot(roomsWithSpace, "Divya");

        HostelRoom[] fullRooms = {
                new HostelRoom("C-214", 3, 3),
                new HostelRoom("C-507", 2, 2)
        };
        safeAllot(fullRooms, "Divya");
    }
}
