package dev.piatnitsa.animallibrary.validator;

import dev.piatnitsa.animallibrary.exception.ExceptionMessageCode;
import dev.piatnitsa.animallibrary.exception.FieldError;
import dev.piatnitsa.animallibrary.exception.IncorrectParameterException;
import dev.piatnitsa.animallibrary.model.Animal;
import dev.piatnitsa.animallibrary.model.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalValidatorTest {
    private static final String CORRECT_NICKNAME = "Ron";
    private static final String INCORRECT_NICKNAME = "Ro";
    private final LocalDate CORRECT_DATE = LocalDate.now().minusDays(1);
    private final LocalDate INCORRECT_DATE = LocalDate.now().plusDays(1);

    private final Animal CORRECT_ANIMAL = new Animal(0, CORRECT_NICKNAME, CORRECT_DATE, Gender.MALE);
    private final Animal INCORRECT_ANIMAL = new Animal(0, INCORRECT_NICKNAME, INCORRECT_DATE, Gender.MALE);

    private final FieldError INCORRECT_NAME_ERROR =
            new FieldError(ExceptionMessageCode.ANIMAL_BAD_NICKNAME, INCORRECT_NICKNAME);
    private final FieldError INCORRECT_DATE_ERROR =
            new FieldError(ExceptionMessageCode.ANIMAL_BAD_DATE_OF_BIRTH, INCORRECT_DATE);
    private final FieldError NULL_ENTITY =
            new FieldError(ExceptionMessageCode.ANIMAL_NOT_EXIST);

    @Test
    void validateCorrectAnimalEntity_thanOk() {
        assertDoesNotThrow(() -> AnimalValidator.validate(CORRECT_ANIMAL));
    }

    @Test
    void validateIncorrectAnimalEntity_thanThrowEx() {
        IncorrectParameterException ex = assertThrows(IncorrectParameterException.class,
                () -> AnimalValidator.validate(INCORRECT_ANIMAL));
        List<FieldError> errors = ex.getErrors();
        assertFalse(errors.isEmpty());
        assertTrue(errors.contains(INCORRECT_NAME_ERROR));
        assertTrue(errors.contains(INCORRECT_DATE_ERROR));
    }

    @Test
    void validateNullEntity_thanThrowEx() {
        IncorrectParameterException ex = assertThrows(IncorrectParameterException.class,
                () -> AnimalValidator.validate(null));
        List<FieldError> errors = ex.getErrors();
        assertFalse(errors.isEmpty());
        assertTrue(errors.contains(NULL_ENTITY));
    }

    @Test
    void validateForUpdateByCorrectData_thanOk() {
        assertDoesNotThrow(() -> AnimalValidator.validateForUpdate(CORRECT_ANIMAL));
    }

    @Test
    void validateForUpdateByIncorrectData_thanOk() {
        IncorrectParameterException ex = assertThrows(IncorrectParameterException.class,
                () -> AnimalValidator.validateForUpdate(INCORRECT_ANIMAL));
        List<FieldError> errors = ex.getErrors();
        assertFalse(errors.isEmpty());
        assertTrue(errors.contains(INCORRECT_NAME_ERROR));
        assertTrue(errors.contains(INCORRECT_DATE_ERROR));
    }

    @Test
    void validateForUpdateByNullEntity_thanThrowEx() {
        IncorrectParameterException ex = assertThrows(IncorrectParameterException.class,
                () -> AnimalValidator.validateForUpdate(null));
        List<FieldError> errors = ex.getErrors();
        assertFalse(errors.isEmpty());
        assertTrue(errors.contains(NULL_ENTITY));
    }

    @Test
    void validateForUpdateByNullField_thanOk() {
        assertDoesNotThrow(() -> AnimalValidator.validateForUpdate(
                new Animal(0, CORRECT_NICKNAME, null, null)));
    }
}