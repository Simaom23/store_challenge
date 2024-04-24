package com.simaom23.store.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.simaom23.store.dto.ErrorDetailsDTO;

import jakarta.servlet.http.HttpServletRequest;

// Global Exception Handler
@RestControllerAdvice(annotations = RestController.class)
@Order(1)
public class GlobalExceptionRestController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetailsDTO> handleAllExceptions(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        }

        LocalDateTime timestamp = LocalDateTime.now();
        String formattedTimestamp = timestamp.format(DateTimeFormatter.ISO_DATE_TIME);
        String message = ex.getMessage() != null ? ex.getMessage() : status.getReasonPhrase();
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(formattedTimestamp, status.value(), message,
                request.getRequestURI());
        return ResponseEntity.status(status).body(errorDetails);
    }
}