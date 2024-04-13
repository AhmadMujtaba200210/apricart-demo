package io.engicodes.apricartdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFoundExHandler(
            ResourceNotFoundException e
    ) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceDuplicationException.class)
    public ResponseEntity<ApiError> resourceDuplicationExHandler(
            ResourceDuplicationException e
    ) {
        ApiError apiError = new ApiError(
                HttpStatus.ALREADY_REPORTED.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.ALREADY_REPORTED);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiError> inputMissingExHandler(
            NullPointerException e
    ) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceValidationException.class)
    public ResponseEntity<ApiError> resourceValidationExHandler(
            ResourceValidationException e
    ) {
        ApiError apiError = new ApiError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}