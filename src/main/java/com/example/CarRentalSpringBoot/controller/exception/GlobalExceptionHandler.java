package com.example.CarRentalSpringBoot.controller.exception;


import com.example.CarRentalSpringBoot.exception.ValidationErrorResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ValidationErrorResponse errorResponse = new ValidationErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setMessage("Validation Error");
        errorResponse.setDetails(errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ValidationErrorResponse> handlePropertyReferenceExceptions(PropertyReferenceException ex) {
        List<String> details = new ArrayList();
        details.add("Property name: " + ex.getPropertyName());
        details.add("Base name: " + ex.getBaseProperty());
        details.add("Message: " + ex.getMessage());

        ValidationErrorResponse errorResponse = new ValidationErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setMessage("Unknown property to sort by.");
        errorResponse.setDetails(details);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
