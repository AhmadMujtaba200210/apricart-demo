package io.engicodes.apricartdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceValidationException extends Exception{
    public ResourceValidationException(String message) {
        super(message);
    }
}
