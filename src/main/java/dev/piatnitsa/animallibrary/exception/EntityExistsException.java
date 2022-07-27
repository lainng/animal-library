package dev.piatnitsa.animallibrary.exception;

/**
 * This class represents the exception that is thrown when an entity already exists in the data source.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class EntityExistsException extends RuntimeException {
    private FieldError fieldError;

    public EntityExistsException(FieldError fieldError) {
        this.fieldError = fieldError;
    }

    public EntityExistsException(String message) {
        super(message);
    }

    public EntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExistsException(Throwable cause) {
        super(cause);
    }

    public FieldError getError() {
        return fieldError;
    }
}
