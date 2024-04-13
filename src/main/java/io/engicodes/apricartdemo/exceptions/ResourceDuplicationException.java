package io.engicodes.apricartdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class ResourceDuplicationException extends Exception{
    public ResourceDuplicationException(String message) {
        super(message);
    }
}
