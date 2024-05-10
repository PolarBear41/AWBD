package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.pojo.dto.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    void createUser(UserDto userDto);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers(Pageable pageable);
    void updateUser(Long userId, UserDto userDto);
}
