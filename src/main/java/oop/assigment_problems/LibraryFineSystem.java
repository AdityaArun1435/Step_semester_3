package oop.assigment_problems;

class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }
        return 0;
    }

    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    public static double totalFineCollected(BookIssue[] issues) {
        double total = 0;
        for (int i = 0; i < issues.length; i++) {
            total = total + issues[i].fineAmount();
        }
        return total;
    }
}

public class LibraryFineSystem {

    public static void main(String[] args) {
        BookIssue[] issues = new BookIssue[5];
        issues[0] = new BookIssue("Clean Code", "Ravi", 18);
        issues[1] = new BookIssue("Effective Java", "Anitha", 5);
        issues[2] = new BookIssue("Refactoring", "Karthik", 0);
        issues[3] = new BookIssue("DSA Handbook", "Meera", 21);
        issues[4] = new BookIssue("Design Patterns", "Suresh", 9);

        for (int i = 0; i < issues.length; i++) {
            String status = issues[i].isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(issues[i].title + " - " + issues[i].daysOverdue + " days - " + status);
        }

        double totalFine = BookIssue.totalFineCollected(issues);
        System.out.println("Total fine collected: Rs " + totalFine);
    }
}
