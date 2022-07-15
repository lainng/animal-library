package dev.piatnitsa.animallibrary.exception;

public class FieldError {
    private String messageCode;
    private Object errorValue;

    public FieldError(String messageCode, Object errorValue) {
        this.messageCode = messageCode;
        this.errorValue = errorValue;
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
