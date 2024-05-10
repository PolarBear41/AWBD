package com.example.CarRentalSpringBoot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address cannot be longer than 200 characters")
    private String address;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.000000", message = "Latitude must be between -90.000000 and 90.000000")
    @DecimalMax(value = "90.000000", message = "Latitude must be between -90.000000 and 90.000000")
    private BigDecimal latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.000000", message = "Longitude must be between -180.000000 and 180.000000")
    @DecimalMax(value = "180.000000", message = "Longitude must be between -180.000000 and 180.000000")
    private BigDecimal longitude;

    @Size(max = 50, message = "City cannot be longer than 50 characters")
    private String city;

    @Size(max = 50, message = "State cannot be longer than 50 characters")
    private String state;

    @Size(max = 50, message = "Country cannot be longer than 50 characters")
    private String country;

    private Long id;
}

