package com.example.CarRentalSpringBoot.repository;

import com.example.CarRentalSpringBoot.entity.Car;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@EnabledIf(value = "#{'${spring.profiles.active}' == 'integration'}")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class CarRepositoryIntegrationTest {

    @Autowired
    CarRepository carRepository;

    @Test
    @Order(1)
    public void shouldSaveCar() {
        Car car = createCar();

        car = carRepository.save(car);

        assertNotNull(car);
    }

    @Test
    @Order(2)
    public void shouldReturnCars() {
        List cars = carRepository.findAll();

        assertNotNull(cars);
        assertEquals(1, cars.size());
    }

    @Test
    @Order(3)
    public void shouldReturnCarById() {
        Car car = carRepository.findById(1L).orElse(null);

        assertNotNull(car);
        assertEquals(1, car.getCarId());
    }

    @Test
    @Order(4)
    public void shouldThrowDataIntegrityViolationExceptionWhenRequiredFieldsNotProvided() {
        Car car = createCar();
        car.setRentalRate(null);

        assertThrows(DataIntegrityViolationException.class, () -> carRepository.save(car));
    }

    private Car createCar() {
        return Car.builder()
                .make("Ford")
                .licensePlate("123-456")
                .model("Escape")
                .year(2024)
                .rentalRate(BigDecimal.valueOf(125))
                .build();
    }

}