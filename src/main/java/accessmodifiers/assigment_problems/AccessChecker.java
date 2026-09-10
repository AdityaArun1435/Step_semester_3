package accessmodifiers.assigment_problems;

class LibraryMember {
    private String membershipId;
    private String branchCode;
    private double finesOwed;
    private String displayName;

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (!isValidMembershipId(membershipId)) {
            throw new IllegalArgumentException("Invalid membershipId");
        }
        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    private static boolean isValidMembershipId(String id) {
        if (id == null) {
            return false;
        }
        String trimmed = id.trim();
        return trimmed.length() >= 4;
    }

    public String getMembershipId() {
        return membershipId;
    }
}

public class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        boolean allowed;

        if (fieldModifier.equals("public")) {
            allowed = true;
        } else if (fieldModifier.equals("private")) {
            allowed = accessorContext.equals("SAME_CLASS");
        } else if (fieldModifier.equals("default")) {
            allowed = accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE");
        } else if (fieldModifier.equals("protected")) {
            allowed = accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE");
        } else {
            allowed = false;
        }

        return allowed ? "ALLOWED" : "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        int[] allowedCounts = new int[4];
        int[] deniedCounts = new int[4];

        for (int i = 0; i < attempts.length; i++) {
            String modifier = attempts[i][0];
            String context = attempts[i][1];
            String result = classifyAccess(modifier, context);

            for (int j = 0; j < modifiers.length; j++) {
                if (modifiers[j].equals(modifier)) {
                    if (result.equals("ALLOWED")) {
                        allowedCounts[j]++;
                    } else {
                        deniedCounts[j]++;
                    }
                }
            }
        }

        StringBuilder summary = new StringBuilder();
        for (int j = 0; j < modifiers.length; j++) {
            summary.append(modifiers[j]).append(": ").append(allowedCounts[j]).append(" allowed / ")
                    .append(deniedCounts[j]).append(" denied");
            if (j < modifiers.length - 1) {
                summary.append(" | ");
            }
        }

        return summary.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(attempts));

        try {
            LibraryMember rejected = new LibraryMember("LB9", "BR1", 0, "Priya Nair");
            System.out.println("construction succeeded");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LibraryMember accepted = new LibraryMember("LB94", "BR1", 0, "Priya Nair");
        System.out.println("construction succeeded: " + accepted.getMembershipId());
    }
}
