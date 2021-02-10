package com.valid.practice.exception.handler;


import com.valid.practice.exception.DuplicatedException;
import com.valid.practice.exception.IdBlankException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionRestControllerHandler {

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<?> duplicateExceptionHandler(DuplicatedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IdBlankException.class)
    public ResponseEntity<?> idBlankException(IdBlankException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
