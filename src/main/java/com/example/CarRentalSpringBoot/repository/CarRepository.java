package com.example.CarRentalSpringBoot.repository;

import com.example.CarRentalSpringBoot.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByCarId(Long carId);
}
