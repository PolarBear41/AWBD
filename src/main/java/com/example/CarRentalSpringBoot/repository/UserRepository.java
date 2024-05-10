package com.example.CarRentalSpringBoot.repository;

import com.example.CarRentalSpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long userId);
    Optional<User> findByUsername(String username);
}
