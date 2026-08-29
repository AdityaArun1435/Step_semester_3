package constructors.assigment_problems;

public class Canteen {
    private static final int DEFAULT_TRUST_SCORE = 3;

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, DEFAULT_TRUST_SCORE);
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    int compareTo(Canteen other) {
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        int codeComparison = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeComparison != 0) {
            return codeComparison;
        }

        return this.canteenName.length() - other.canteenName.length();
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] result = new Canteen[canteens.length];
        for (int i = 0; i < canteens.length; i++) {
            result[i] = canteens[i];
        }

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - 1 - i; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    Canteen temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);
        for (int i = 0; i < ranked.length; i++) {
            System.out.println(ranked[i].getCanteenCode());
        }
    }
}
