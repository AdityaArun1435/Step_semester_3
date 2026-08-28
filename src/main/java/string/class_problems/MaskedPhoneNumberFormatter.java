package string.class_problems;

public class MaskedPhoneNumberFormatter {

    public static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        String lastFourDigits = phone.substring(6);

        StringBuilder maskedNumber = new StringBuilder("XXXXXX");
        maskedNumber.insert(6, "-" + lastFourDigits);

        return maskedNumber.toString();
    }

    public static void main(String[] args) {
        String phone = "9876543210";
        String result = maskPhoneNumber(phone);
        System.out.println(result);
    }
}
