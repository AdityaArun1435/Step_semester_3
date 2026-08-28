package string.class_problems;

public class BankReferenceValidator {

    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String remainder = trimmed.substring(3);
        return bankCode + remainder;
    }

    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        String bankCode = reference.substring(0, 3);
        for (int i = 0; i < bankCode.length(); i++) {
            if (!Character.isLetter(bankCode.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        String body = reference.substring(3);
        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String day = reference.substring(3, 5);
        String month = reference.substring(5, 7);
        String year = reference.substring(7, 9);
        String sequence = reference.substring(9, 14);

        StringBuilder formattedLine = new StringBuilder();
        formattedLine.append("[").append(bankCode).append("] DATE: ")
                .append(day).append("/").append(month).append("/").append(year)
                .append(" | SEQ: ").append(sequence);

        return formattedLine.toString();
    }

    public static void main(String[] args) {
        String raw = " hdf03022600042 ";
        String normalized = normalizeReference(raw);
        String result = validateAndFormat(normalized);
        System.out.println(result);
    }
}
