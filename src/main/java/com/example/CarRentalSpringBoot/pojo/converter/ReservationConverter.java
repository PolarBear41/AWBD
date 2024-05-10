package com.example.CarRentalSpringBoot.pojo.converter;

import com.example.CarRentalSpringBoot.entity.Car;
import com.example.CarRentalSpringBoot.entity.Reservation;
import com.example.CarRentalSpringBoot.entity.User;
import com.example.CarRentalSpringBoot.pojo.dto.ReservationDto;


public class ReservationConverter {
    public static ReservationDto reservationConvertToDto(Reservation reservation) {
        return ReservationDto.builder()
                .reservationDate(reservation.getReservationDate())
                .reservationStatus(reservation.getStatus())
                .carId(reservation.getCar().getCarId())
                .userId(reservation.getUser().getUserId())
                .build();
    }

    public static Reservation dtoConvertToReservation(ReservationDto reservationDto, User user, Car car) {
        return Reservation.builder()
                .reservationDate(reservationDto.getReservationDate())
                .status(reservationDto.getReservationStatus())
                .car(car)
                .user(user)
                .build();
    }
}
