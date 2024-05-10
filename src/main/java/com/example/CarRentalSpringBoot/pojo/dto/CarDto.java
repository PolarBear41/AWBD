package com.example.CarRentalSpringBoot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {

    @NotBlank(message = "Make is required")
    @Size(max = 50, message = "Make cannot be longer than 50 characters")
    private String make;

    @NotBlank(message = "Model is required")
    @Size(max = 50, message = "Model cannot be longer than 50 characters")
    private String model;

    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Year must be at least 1900")
    private Integer year;

    @NotNull(message = "Rental rate is required")
    @DecimalMin(value = "0.00", message = "Rental rate must be at least 0.00")
    @DecimalMax(value = "100000.00", message = "Rental rate cannot exceed 100000.00")
    private BigDecimal rentalRate;

    @Size(max = 20, message = "License plate cannot be longer than 20 characters")
    private String licensePlate;

    private Long id;
}

