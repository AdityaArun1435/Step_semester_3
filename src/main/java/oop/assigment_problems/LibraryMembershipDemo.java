package oop.assigment_problems;

class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    public BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class FixedLibraryMember {
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "SRM Central Library";
    static int memberCount = 0;

    public FixedLibraryMember(String name, int booksIssued) {
        this.name = name;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
        this.booksIssued = booksIssued;
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class LibraryMembershipDemo {

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenLibraryMember brokenAditi = new BrokenLibraryMember("Aditi", "LM001", 2);
        BrokenLibraryMember brokenRohan = new BrokenLibraryMember("Rohan", "LM002", 1);
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);

        System.out.println();
        System.out.println("Fixed version:");
        FixedLibraryMember aditi = new FixedLibraryMember("Aditi", 2);
        FixedLibraryMember rohan = new FixedLibraryMember("Rohan", 1);
        aditi.printMemberCard();
        rohan.printMemberCard();
        FixedLibraryMember.printTotalMembers();
    }
}
