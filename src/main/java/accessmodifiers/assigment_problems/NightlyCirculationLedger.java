package accessmodifiers.assigment_problems;

class LoanReceipt {
    private static final String BOOK_ID_PREFIX = "BK-";

    protected final String memberId;
    protected final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        if (!areAllBookIdsValid(bookIds)) {
            throw new IllegalArgumentException("Invalid book ID format");
        }
        this.memberId = memberId;
        this.bookIds = copyArray(bookIds);
    }

    private static boolean areAllBookIdsValid(String[] ids) {
        if (ids == null) {
            return false;
        }
        for (int i = 0; i < ids.length; i++) {
            if (!isValidBookId(ids[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidBookId(String id) {
        if (id == null || id.length() != 6 || !id.startsWith(BOOK_ID_PREFIX)) {
            return false;
        }
        for (int i = 3; i < 6; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static String[] copyArray(String[] source) {
        String[] copy = new String[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    String[] getBookIds() {
        return copyArray(bookIds);
    }

    LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] correctedIds = copyArray(bookIds);
        correctedIds[index] = newId;
        return new LoanReceipt(memberId, correctedIds);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

public class NightlyCirculationLedger {
    static {
        // one-time shared setup for the ledger
    }

    static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processedCount = 0;
        int nullSkipped = 0;
        int referenceOnlyCount = 0;
        int regularCount = 0;

        for (int i = 0; i < receipts.length; i++) {
            LoanReceipt receipt = receipts[i];

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processedCount++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnlyCount++;
            } else {
                regularCount++;
            }
        }

        return processedCount + " processed | " + nullSkipped + " null skipped | " +
                referenceOnlyCount + " reference-only | " + regularCount + " regular";
    }

    public static void main(String[] args) {
        try {
            LoanReceipt rejected = new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
            System.out.println("construction succeeded");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);

        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
                null,
                new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(processNightlyCirculation(receipts));
    }
}
