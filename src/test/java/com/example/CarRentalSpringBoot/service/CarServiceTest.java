package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.entity.Car;
import com.example.CarRentalSpringBoot.pojo.dto.CarDto;
import com.example.CarRentalSpringBoot.repository.CarRepository;
import com.example.CarRentalSpringBoot.service.impl.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;


    private CarDto carDto;
    private Car car;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        carDto = new CarDto();
        car = new Car();
        pageable = PageRequest.of(0, 5);
    }

    @Test
    void addCar() {
        carService.addCar(carDto);
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void getCarByIdFound() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));
        CarDto result = carService.getCarById(1L);
        assertNotNull(result);
    }

    @Test
    void getCarByIdNotFound() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.empty());
        CarDto result = carService.getCarById(1L);
        assertNull(result);
    }

    @Test
    void getAllCars() {
        when(carRepository.findAll(pageable)).thenReturn(new PageImpl(Arrays.asList(car)));

        List<CarDto> result = carService.getAllCars(pageable);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getAllCarsEmptyList() {
        when(carRepository.findAll(pageable)).thenReturn(new PageImpl(Arrays.asList()));

        List<CarDto> result = carService.getAllCars(pageable);

        assertNull(result);
    }
}

