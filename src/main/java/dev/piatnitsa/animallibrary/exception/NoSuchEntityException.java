package dev.piatnitsa.animallibrary.exception;

/**
 * This class represents the exception that is thrown when entity does not found in the data source.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class NoSuchEntityException extends RuntimeException {
    private FieldError fieldError;

    public NoSuchEntityException() {
    }

    public NoSuchEntityException(FieldError fieldError) {
        this.fieldError = fieldError;
    }

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchEntityException(Throwable cause) {
        super(cause);
    }

    public FieldError getError() {
        return fieldError;
    }
}
