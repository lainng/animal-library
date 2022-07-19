package dev.piatnitsa.animallibrary.validator;

import dev.piatnitsa.animallibrary.exception.ExceptionMessageCode;
import dev.piatnitsa.animallibrary.exception.FieldError;
import dev.piatnitsa.animallibrary.exception.IncorrectParameterException;
import dev.piatnitsa.animallibrary.model.Animal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimalValidator {
    private static final int MIN_NICKNAME_SIZE = 3;
    private static final int MAX_NICKNAME_SIZE = 32;

    public static void validate(Animal entity) {
        nullCheck(entity);

        List<FieldError> errors = new ArrayList<>(2);
        if (validateNickname(entity.getNickname()) != null) {
            errors.add(validateNickname(entity.getNickname()));
        }

        if (validateDateOfBirth(entity.getDateOfBirth()) != null) {
            errors.add(validateDateOfBirth(entity.getDateOfBirth()));
        }

        if (!errors.isEmpty()) {
            throw new IncorrectParameterException(errors);
        }
    }

    public static void validateForUpdate(Animal entity) {
        nullCheck(entity);

        List<FieldError> errors = new ArrayList<>(2);
        if (entity.getNickname() != null) {
            if (validateNickname(entity.getNickname()) != null) {
                errors.add(validateNickname(entity.getNickname()));
            }
        }

        if (entity.getDateOfBirth() != null) {
            if (validateDateOfBirth(entity.getDateOfBirth()) != null) {
                errors.add(validateDateOfBirth(entity.getDateOfBirth()));
            }
        }

        if (!errors.isEmpty()) {
            throw new IncorrectParameterException(errors);
        }
    }

    private static FieldError validateNickname(String nickname) {
        if (nickname == null
                || nickname.length() < MIN_NICKNAME_SIZE
                || nickname.length() > MAX_NICKNAME_SIZE) {
            return new FieldError(ExceptionMessageCode.ANIMAL_BAD_NICKNAME, nickname);
        }
        return null;
    }

    private static FieldError validateDateOfBirth(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            return new FieldError(ExceptionMessageCode.ANIMAL_BAD_DATE_OF_BIRTH, date);
        }
        return null;
    }

    private static void nullCheck(Animal animal) {
        if (animal == null) {
            throw new IncorrectParameterException(
                    Collections.singletonList(new FieldError(ExceptionMessageCode.ANIMAL_NOT_EXIST))
            );
        }
    }
}
