package accessmodifiers.class_problems;

class PatientRecord {
    private String patientId;
    private String wardCode;
    private double vitalsScore;
    private String facilityName;

    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        if (!isValidPatientId(patientId)) {
            throw new IllegalArgumentException("Invalid patientId");
        }
        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    private static boolean isValidPatientId(String id) {
        if (id == null) {
            return false;
        }
        String trimmed = id.trim();
        return trimmed.length() >= 4;
    }

    public String getPatientId() {
        return patientId;
    }
}

public class AccessRuleEngine {

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

    static String summarizeBatch(String[][] attempts) {
        int allowedCount = 0;
        int deniedCount = 0;

        for (int i = 0; i < attempts.length; i++) {
            String result = classifyAccess(attempts[i][0], attempts[i][1]);
            if (result.equals("ALLOWED")) {
                allowedCount++;
            } else {
                deniedCount++;
            }
        }

        return "Allowed: " + allowedCount + " | Denied: " + deniedCount;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("default", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
                {"protected", "SAME_PACKAGE"},
                {"protected", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeBatch(attempts));

        try {
            PatientRecord rejected = new PatientRecord("MT9", "W3", 98.2, "MediTrack Central");
            System.out.println("construction succeeded");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}
