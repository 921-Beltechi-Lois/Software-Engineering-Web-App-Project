package com.example.destinationManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DestinationNotFoundException extends RuntimeException {

    public DestinationNotFoundException(Integer id) {
        super("Could not find Destination " + id);
    }
}

@ControllerAdvice
class DestinationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DestinationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(DestinationNotFoundException ex) {
        return ex.getMessage();
    }
}
