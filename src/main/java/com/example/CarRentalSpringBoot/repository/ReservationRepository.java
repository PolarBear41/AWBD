package com.example.CarRentalSpringBoot.repository;

import com.example.CarRentalSpringBoot.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.user.userId = :userId")
    List<Reservation> findByUserId(Long userId);

    @Query("SELECT r FROM Reservation r WHERE r.car.carId = :carId")
    List<Reservation> findByCarId(Long carId);
}