package string.assigment_problems;

public class LibraryIsbnValidator {

    public static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        String publisherCode = trimmed.substring(0, 3).toUpperCase();
        String remainder = trimmed.substring(3);
        return publisherCode + remainder;
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        String publisherCode = code.substring(0, 3);
        for (int i = 0; i < publisherCode.length(); i++) {
            if (!Character.isLetter(publisherCode.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        String body = code.substring(3);
        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String year = code.substring(3, 7);
        String catalogNumber = code.substring(7, 13);

        StringBuilder formattedLine = new StringBuilder();
        formattedLine.append("[").append(publisherCode).append("] YEAR: ")
                .append(year).append(" | CATALOG: ").append(catalogNumber);

        return formattedLine.toString();
    }

    public static void main(String[] args) {
        String raw = " pen2026004251 ";
        String normalized = normalizeCode(raw);
        String result = validateAndFormat(normalized);
        System.out.println(result);
    }
}
