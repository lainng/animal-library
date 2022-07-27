package dev.piatnitsa.animallibrary.exception;

import java.util.List;

/**
 * This class represents the exception that is thrown when the validation of entity parameters fails.
 * Contains a {@link List} of {@link FieldError} entities.
 * @author Vlad Piatnitsa
 * @version 1.0
 * @see FieldError
 */
public class IncorrectParameterException extends RuntimeException {
    private List<FieldError> errors;

    public IncorrectParameterException(List<FieldError> errors) {
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
