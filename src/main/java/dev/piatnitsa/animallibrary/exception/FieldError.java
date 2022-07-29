package dev.piatnitsa.animallibrary.exception;

import java.util.Objects;

/**
 * This class is an entity that contains an exception message code and an invalid field value.
 * Required for entity validation.
 * @author Vlad Piatnitsa
 * @version 1.0
 * @see dev.piatnitsa.animallibrary.validator.AnimalValidator
 * @see dev.piatnitsa.animallibrary.validator.UserValidator
 */
public class FieldError {
    private String messageCode;
    private Object errorValue;

    public FieldError(String messageCode, Object errorValue) {
        this.messageCode = messageCode;
        this.errorValue = errorValue;
    }

    public FieldError(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Object getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(Object errorValue) {
        this.errorValue = errorValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldError error = (FieldError) o;
        return Objects.equals(messageCode, error.messageCode) && Objects.equals(errorValue, error.errorValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageCode, errorValue);
    }
}
