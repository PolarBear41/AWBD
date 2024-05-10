package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.entity.User;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<User> findByUsername(String username);
}
