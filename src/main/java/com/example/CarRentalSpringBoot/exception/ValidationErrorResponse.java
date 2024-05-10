package com.example.CarRentalSpringBoot.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse {
    private Date timestamp;
    private String message;
    private List<String> details;


}
