package com.example.CarRentalSpringBoot.pojo.converter;

import com.example.CarRentalSpringBoot.entity.Car;
import com.example.CarRentalSpringBoot.pojo.dto.CarDto;

public class CarConverter {
    public static CarDto carConvertToDto(Car car) {
        return CarDto.builder()
                .id(car.getCarId())
                .make(car.getMake())
                .model(car.getModel())
                .year(car.getYear())
                .rentalRate(car.getRentalRate())
                .licensePlate(car.getLicensePlate())
                .build();
    }
    public static Car dtoConvertToCar(CarDto carDto) {
        return Car.builder()
                .carId(carDto.getId())
                .make(carDto.getMake())
                .model(carDto.getModel())
                .year(carDto.getYear())
                .rentalRate(carDto.getRentalRate())
                .licensePlate(carDto.getLicensePlate())
                .build();
    }
}
