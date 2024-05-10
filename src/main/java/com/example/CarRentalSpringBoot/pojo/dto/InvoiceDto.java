package com.example.CarRentalSpringBoot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDto {

    @NotNull(message = "Rental start date is required")
    @PastOrPresent(message = "Rental start date must be in the past or present")
    private Date rentalStartDate;

    @NotNull(message = "Rental end date is required")
    @FutureOrPresent(message = "Rental end date must be in the future or present")
    private Date rentalEndDate;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.00", message = "Total amount must be at least 0.00")
    @DecimalMax(value = "100000.00", message = "Total amount cannot exceed 100000.00")
    private BigDecimal totalAmount;

    @Size(max = 20, message = "Payment status cannot be longer than 20 characters")
    private String paymentStatus;

}

