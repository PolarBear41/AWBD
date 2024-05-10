package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.pojo.dto.CarDto;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CarService {
    void addCar(CarDto carDto);
    CarDto getCarById(Long carId);
    List<CarDto> getAllCars(Pageable pageable);
    void updateCar(Long carId, CarDto carDto);
    void deleteById(Long id);
}

