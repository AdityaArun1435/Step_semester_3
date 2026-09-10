package accessmodifiers.class_problems;

class DischargeSummary {
    private static final String MEDICATION_PREFIX = "MED-";

    protected final String patientId;
    protected final String[] medicationCodes;

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (!areAllCodesValid(medicationCodes)) {
            throw new IllegalArgumentException("Invalid medication code format");
        }
        this.patientId = patientId;
        this.medicationCodes = copyArray(medicationCodes);
    }

    private static boolean areAllCodesValid(String[] codes) {
        if (codes == null) {
            return false;
        }
        for (int i = 0; i < codes.length; i++) {
            if (!isValidCode(codes[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidCode(String code) {
        if (code == null || code.length() != 5 || !code.startsWith(MEDICATION_PREFIX)) {
            return false;
        }
        char lastChar = code.charAt(4);
        return Character.isUpperCase(lastChar) && Character.isLetter(lastChar);
    }

    private static String[] copyArray(String[] source) {
        String[] copy = new String[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    String[] getMedicationCodes() {
        return copyArray(medicationCodes);
    }

    DischargeSummary withCorrectedMedication(int index, String newCode) {
        String[] corrected = copyArray(medicationCodes);
        corrected[index] = newCode;
        return new DischargeSummary(patientId, corrected);
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}

public class NightlyLedger {
    static {
        // one-time shared setup for the ledger
    }

    static String processNightlyBatch(DischargeSummary[] summaries) {
        int processedCount = 0;
        int nullSkipped = 0;
        int criticalCareCount = 0;
        int routineCount = 0;

        for (int i = 0; i < summaries.length; i++) {
            DischargeSummary summary = summaries[i];

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processedCount++;
            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCareCount++;
            } else {
                routineCount++;
            }
        }

        return processedCount + " processed | " + nullSkipped + " null skipped | " +
                criticalCareCount + " critical-care | " + routineCount + " routine";
    }

    public static void main(String[] args) {
        try {
            DischargeSummary rejected = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
            System.out.println("construction succeeded");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary[] summaries = {
                new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
                null,
                new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(summaries));
    }
}
