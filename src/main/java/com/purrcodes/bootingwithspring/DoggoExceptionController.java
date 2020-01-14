package com.purrcodes.bootingwithspring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DoggoExceptionController {
    @ExceptionHandler( value = DoggoNotFoundException.class)
    public ResponseEntity<Object> exception(DoggoNotFoundException exception) {
        return new ResponseEntity<>("Doggo Not Found", HttpStatus.NOT_FOUND);
    }
}

