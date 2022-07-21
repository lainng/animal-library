package dev.piatnitsa.animallibrary.exception;

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
