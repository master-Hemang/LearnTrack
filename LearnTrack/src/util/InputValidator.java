package util;


public class InputValidator {

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z ]+");
    }

    public static boolean isPositiveNumber(String input) {
        try {
            int num = Integer.parseInt(input);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
