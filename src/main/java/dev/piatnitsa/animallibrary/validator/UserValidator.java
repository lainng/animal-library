package dev.piatnitsa.animallibrary.validator;

import dev.piatnitsa.animallibrary.exception.ExceptionMessageCode;
import dev.piatnitsa.animallibrary.exception.FieldError;
import dev.piatnitsa.animallibrary.exception.IncorrectParameterException;
import dev.piatnitsa.animallibrary.model.User;

import java.util.Collections;

public class UserValidator {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 64;

    public static void validate(User user) {

    }

    public static void validateName(String name) {
        if (name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IncorrectParameterException(Collections.singletonList(
                    new FieldError(ExceptionMessageCode.USER_BAD_NAME, name))
            );
        }
    }
}
