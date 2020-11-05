package by.epamlab.model.utils;

import by.epamlab.exceptions.ValidationException;

public class InputValidate {

    private static final String LOGIN_NOT_VALID = "enter correct login";
    private static final String PASSWORDS_NOT_VALID = "enter correct password";

    public static void doValidate(String passwordFirst, String passwordSecond, String login) throws ValidationException {

        if (login.trim().isEmpty()) {
            throw new ValidationException(LOGIN_NOT_VALID);
        }

        if (passwordFirst.trim().isEmpty()) {
            throw new ValidationException(PASSWORDS_NOT_VALID);
        }

        if (!passwordFirst.equals(passwordSecond)) {
            throw new ValidationException(PASSWORDS_NOT_VALID);
        }
    }
}
