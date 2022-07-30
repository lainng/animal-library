package dev.piatnitsa.animallibrary.validator;

import dev.piatnitsa.animallibrary.exception.ExceptionMessageCode;
import dev.piatnitsa.animallibrary.exception.FieldError;
import dev.piatnitsa.animallibrary.exception.IncorrectParameterException;
import dev.piatnitsa.animallibrary.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    private static final String CORRECT_EMAIL = "harrypotter@gmail.com";
    private static final String INCORRECT_EMAIL = "lord!voldemort@gmail.com";
    private static final String CORRECT_PASSWORD = "StrongPassword";
    private static final String INCORRECT_PASSWORD = "qwerty";
    private static final String CORRECT_USERNAME = "Harry Potter";
    private static final String INCORRECT_USERNAME = "V";

    private final User CORRECT_USER = new User(0, CORRECT_EMAIL, CORRECT_PASSWORD, CORRECT_USERNAME);
    private final User INCORRECT_USER = new User(0, INCORRECT_EMAIL, INCORRECT_PASSWORD, INCORRECT_USERNAME);

    private final FieldError EMAIL_ERROR = new FieldError(ExceptionMessageCode.USER_BAD_EMAIL, INCORRECT_EMAIL);
    private final FieldError USERNAME_ERROR = new FieldError(ExceptionMessageCode.USER_BAD_NAME, INCORRECT_USERNAME);
    private final FieldError PASSWORD_ERROR = new FieldError(ExceptionMessageCode.USER_BAD_PASSWORD);
    private final FieldError NULL_USER = new FieldError(ExceptionMessageCode.USER_NOT_EXIST);
    private final FieldError NULL_USERNAME = new FieldError(ExceptionMessageCode.USER_BAD_NAME);

    @Test
    void validateCorrectUser_thanOk() {
        assertDoesNotThrow(() -> UserValidator.validate(CORRECT_USER));
    }

    @Test
    void validateIncorrectUser_thanThrowEx() {
        IncorrectParameterException ex = assertThrows(IncorrectParameterException.class,
                () -> UserValidator.validate(INCORRECT_USER));
        List<FieldError> errors = ex.getErrors();
        assertFalse(errors.isEmpty());
        assertTrue(errors.contains(EMAIL_ERROR));
        assertTrue(errors.contains(USERNAME_ERROR));
        assertTrue(errors.contains(PASSWORD_ERROR));
    }

    @Test
    void validateNullUser_ThanThrowEx() {
        IncorrectParameterException ex = assertThrows(IncorrectParameterException.class,
                () -> UserValidator.validate(null));
        assertTrue(ex.getErrors().contains(NULL_USER));
    }

    @Test
    void validateCorrectName_thanOk() {
        assertDoesNotThrow(() -> UserValidator.validateName(CORRECT_USERNAME));
    }

    @Test
    void validateIncorrectName_thanThrowEx() {
        IncorrectParameterException ex = assertThrows(IncorrectParameterException.class,
                () -> UserValidator.validateName(INCORRECT_USERNAME));
        List<FieldError> errors = ex.getErrors();
        assertFalse(errors.isEmpty());
        assertTrue(errors.contains(USERNAME_ERROR));
    }

    @Test
    void validateNullName_thanThrowEx() {
        IncorrectParameterException ex = assertThrows(IncorrectParameterException.class,
                () -> UserValidator.validateName(null));
        List<FieldError> errors = ex.getErrors();
        assertFalse(errors.isEmpty());
        assertTrue(errors.contains(NULL_USERNAME));
    }
}