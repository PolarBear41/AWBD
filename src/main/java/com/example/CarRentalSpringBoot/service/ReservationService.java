package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.pojo.dto.ReservationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> getAllReservations(Pageable pageable);

    void addReservation(ReservationDto reservation);

    List<ReservationDto> getAllReservationsByCarId(Long carId);

    List<ReservationDto> getAllReservationsByUserId(Long userId);
}
