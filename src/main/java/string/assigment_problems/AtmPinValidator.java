package string.assigment_problems;

public class AtmPinValidator {

    public static void checkPinLength(String pin) {
        int pinLength = pin.length();

        if (pinLength != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        String pin = "482";
        checkPinLength(pin);
    }
}
