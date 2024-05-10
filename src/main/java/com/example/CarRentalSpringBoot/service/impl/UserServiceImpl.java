package com.example.CarRentalSpringBoot.service.impl;


import com.example.CarRentalSpringBoot.pojo.converter.UserConverter;
import com.example.CarRentalSpringBoot.pojo.dto.UserDto;
import com.example.CarRentalSpringBoot.service.UserService;
import lombok.RequiredArgsConstructor;
import com.example.CarRentalSpringBoot.repository.UserRepository;
import com.example.CarRentalSpringBoot.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserDto userDto) {
        User user =UserConverter.dtoConvertToUser(userDto);
        userRepository.save(user);
    }
    @Override
    public void updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            throw new RuntimeException("Car not found");
        }
        user.setAddress(userDto.getAddress());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        userRepository.save(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        var userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        return UserConverter.userConvertToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(UserConverter::userConvertToDto).collect(Collectors.toList());
    }
}

