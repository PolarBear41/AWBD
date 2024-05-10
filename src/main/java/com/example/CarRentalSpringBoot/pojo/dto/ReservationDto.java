package com.example.CarRentalSpringBoot.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {

    @NotNull(message = "Reservation date is required")
    @PastOrPresent(message = "Reservation date must be in the past or present")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationDate;

    @Size(max = 20, message = "Status cannot be longer than 20 characters")
    private String reservationStatus;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Car ID is required")
    private Long carId;
}

