package dev.piatnitsa.animallibrary.exception;

import java.util.List;

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
