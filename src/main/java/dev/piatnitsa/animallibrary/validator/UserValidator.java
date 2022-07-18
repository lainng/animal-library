package dev.piatnitsa.animallibrary.validator;

import dev.piatnitsa.animallibrary.exception.ExceptionMessageCode;
import dev.piatnitsa.animallibrary.exception.FieldError;
import dev.piatnitsa.animallibrary.exception.IncorrectParameterException;
import dev.piatnitsa.animallibrary.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserValidator {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 64;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 64;
    private static final String EMAIL_REGEXP = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";

    public static void validate(User user) {
        if (nullCheck(user)) {
            throw new IncorrectParameterException(
                    Collections.singletonList(new FieldError(ExceptionMessageCode.USER_NOT_EXIST))
            );
        }

        List<FieldError> errors = new ArrayList<>(3);
        if (validateEmail(user.getEmail()) != null) {
            errors.add(validateEmail(user.getEmail()));
        }

        if (validateUsername(user.getName()) != null) {
            errors.add(validateUsername(user.getName()));
        }

        if (validatePassword(user.getPassword()) != null) {
            errors.add(validatePassword(user.getPassword()));
        }

        if (!errors.isEmpty()) {
            throw new IncorrectParameterException(errors);
        }
    }

    public static void validateName(String name) {
        if (name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IncorrectParameterException(Collections.singletonList(
                    new FieldError(ExceptionMessageCode.USER_BAD_NAME, name))
            );
        }
    }

    private static FieldError validateEmail(String email) {
        if (email == null || !email.matches(EMAIL_REGEXP)) {
            return new FieldError(ExceptionMessageCode.USER_BAD_EMAIL, email);
        }
        return null;
    }

    private static FieldError validateUsername(String name) {
        if (name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            new FieldError(ExceptionMessageCode.USER_BAD_NAME, name);
        }
        return null;
    }

    private static FieldError validatePassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            new FieldError(ExceptionMessageCode.USER_BAD_PASSWORD, password);
        }
        return null;
    }

    private static boolean nullCheck(User user) {
        return user == null;
    }
}
