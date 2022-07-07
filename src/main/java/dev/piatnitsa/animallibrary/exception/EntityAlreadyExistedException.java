package dev.piatnitsa.animallibrary.exception;

public class EntityAlreadyExistedException extends RuntimeException {
    public EntityAlreadyExistedException() {
    }

    public EntityAlreadyExistedException(String message) {
        super(message);
    }

    public EntityAlreadyExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyExistedException(Throwable cause) {
        super(cause);
    }
}
