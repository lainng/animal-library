package dev.piatnitsa.animallibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuilder details = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String errorField = ((FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            details.append(errorField).append("->").append(errorMessage);
        });
        return ResponseEntity.badRequest().body(details.toString());
    }
}
