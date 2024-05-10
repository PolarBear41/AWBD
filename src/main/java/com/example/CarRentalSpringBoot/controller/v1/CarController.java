package com.example.CarRentalSpringBoot.controller.v1;

import com.example.CarRentalSpringBoot.pojo.dto.CarDto;
import com.example.CarRentalSpringBoot.service.impl.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

@RestController("CarControllerV1")
@RequestMapping("/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarServiceImpl carService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a Car", description = "Create a new car record.")
    public ResponseEntity<Void> createCar(@RequestBody @Valid CarDto carDto) {
        carService.addCar(carDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{carId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a Car", description = "Update an existing car record by its ID.")
    public ResponseEntity<Void> updateCar(@Parameter(description = "Car ID") @PathVariable Long carId,
                                          @RequestBody @Valid CarDto carDto) {
        carService.updateCar(carId, carDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a Car by ID", description = "Retrieve car details by providing its unique ID.")
    public ResponseEntity<CarDto> getCar(@Parameter(description = "Car ID") @PathVariable Long carId) {
        CarDto carDto = carService.getCarById(carId);
        if (carDto != null) {
            return new ResponseEntity<>(carDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get All Cars", description = "Retrieve a list of all available cars.")
    public ResponseEntity<List<CarDto>> getAllCars(Pageable pageable) {
        List<CarDto> cars = carService.getAllCars(pageable);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
