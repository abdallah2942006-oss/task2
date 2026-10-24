package vehicle.registration.util;

import vehicle.registration.exception.InvalidInputException;

import java.time.Year;

public final class InputValidator {
    private InputValidator() { }

    public static String requireText(String value, String fieldName)
            throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    public static String validatePlate(String plate) throws InvalidInputException {
        String value = requireText(plate, "Plate number").toUpperCase();
        if (!value.matches("[A-Z0-9-]{2,15}")) {
            throw new InvalidInputException(
                    "Plate number must contain 2-15 letters, digits, or hyphens.");
        }
        return value;
    }

    public static int validateYear(int year) throws InvalidInputException {
        if (year < 1886 || year > Year.now().getValue()) {
            throw new InvalidInputException("Enter a valid manufacturing year.");
        }
        return year;
    }
}
