package com.simaom23.store.controller;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.simaom23.store.model.ErrorDetails;

/* Exception Handler */
@RestControllerAdvice(annotations = RestController.class)
@Order(1)
public class GlobalExceptionRestController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, HttpStatus status) {
        ErrorDetails errorDetails = new ErrorDetails(status.toString(),
                "Something went wrong!");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}