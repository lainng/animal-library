package dev.piatnitsa.animallibrary.exception;

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
}
