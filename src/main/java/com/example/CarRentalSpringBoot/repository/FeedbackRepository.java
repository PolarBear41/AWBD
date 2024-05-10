package com.example.CarRentalSpringBoot.repository;


import com.example.CarRentalSpringBoot.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("SELECT f FROM Feedback f WHERE f.car.carId = :carId")
    List<Feedback> findByCarId(@Param("carId") Long carId);

    @Query("SELECT f FROM Feedback f WHERE f.user.userId = :userId")
    List<Feedback> findByUserId(@Param("userId") Long userId);
}