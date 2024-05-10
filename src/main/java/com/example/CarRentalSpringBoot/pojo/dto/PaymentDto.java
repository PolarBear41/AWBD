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
public class PaymentDto {

    @NotNull(message = "Payment date is required")
    @PastOrPresent(message = "Payment date must be in the past or present")
    private Date paymentDate;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.00", message = "Amount must be at least 0.00")
    @DecimalMax(value = "100000.00", message = "Amount cannot exceed 100000.00")
    private BigDecimal amount;

    @Size(max = 50, message = "Payment method cannot be longer than 50 characters")
    private String paymentMethod;

    @NotNull(message = "Invoice ID is required")
    private Long invoiceId;

}

