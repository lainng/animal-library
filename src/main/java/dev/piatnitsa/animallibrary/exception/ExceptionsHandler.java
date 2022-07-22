package dev.piatnitsa.animallibrary.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class ExceptionsHandler {
    private final MessageSource messageSource;

    @Autowired
    public ExceptionsHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(IncorrectParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIncorrectParameterException(IncorrectParameterException ex) {
        StringBuilder details = new StringBuilder();
        ex.getErrors().forEach(fieldError -> {
            String message = formatExceptionMessage(fieldError);
            details.append(message).append(" ");
        });
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), details.toString());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NoSuchEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoSuchEntityException(NoSuchEntityException ex) {
        String details = formatExceptionMessage(ex.getError());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(), details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleEntityExistsException(EntityExistsException ex) {
        String details = formatExceptionMessage(ex.getError());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(), details);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException() {
        String details = messageSource.getMessage(
                "exception.msgNoReadable",
                null,
                Locale.getDefault());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), details);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private String formatExceptionMessage(FieldError fieldError) {
        String details = messageSource.getMessage(
                fieldError.getMessageCode(),
                null,
                Locale.getDefault()
        );
        return String.format(details, fieldError.getErrorValue());
    }
}
