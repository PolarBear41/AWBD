package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.entity.User;
import com.example.CarRentalSpringBoot.pojo.dto.UserDto;
import com.example.CarRentalSpringBoot.repository.UserRepository;
import com.example.CarRentalSpringBoot.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        user = new User();
        userDto = new UserDto("test", "test", "test@gmail.com", "test", "test", "test", 1L);
        pageable = PageRequest.of(0, 5);
    }

    @Test
    void createUser() {
        userService.createUser(userDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void getUserById() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        UserDto result = userService.getUserById(userId);

        assertNotNull(result);
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll(pageable)).thenReturn(new PageImpl(Collections.singletonList(user)));
        List<UserDto> result = userService.getAllUsers(pageable);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}

