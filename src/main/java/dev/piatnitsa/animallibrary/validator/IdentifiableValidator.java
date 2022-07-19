package dev.piatnitsa.animallibrary.validator;

import dev.piatnitsa.animallibrary.exception.ExceptionMessageCode;
import dev.piatnitsa.animallibrary.exception.FieldError;
import dev.piatnitsa.animallibrary.exception.IncorrectParameterException;

import java.util.Collections;

public class IdentifiableValidator {

    public static void validateId(long id) {
        if (id < 0) {
            throw new IncorrectParameterException(
                    Collections.singletonList(new FieldError(ExceptionMessageCode.ENTITY_BAD_ID, id))
            );
        }
    }
}
