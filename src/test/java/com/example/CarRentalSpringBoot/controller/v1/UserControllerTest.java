package com.example.CarRentalSpringBoot.controller.v1;

import com.example.CarRentalSpringBoot.pojo.dto.UserDto;
import com.example.CarRentalSpringBoot.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
        pageable = PageRequest.of(0, 5);
    }

    @Test
    void createUser() throws Exception {
        UserDto userDto = new UserDto("username", "password", "email@gmail.com", "address", "firstName", "lastName", 1L);

        mockMvc.perform(post("/v1/user")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).createUser(any(UserDto.class));
    }

    @Test
    void updateUser() throws Exception {
        Long userId = 1L;
        UserDto userDto = new UserDto("username", "password", "email@gmail.com", "address", "firstName", "lastName", 1L);

        mockMvc.perform(put("/v1/user/{userId}", userId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateUser(eq(userId), any(UserDto.class));
    }

    @Test
    void getSpecificUser() throws Exception {
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(new UserDto());

        mockMvc.perform(get("/v1/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    void getAllUsers() throws Exception {
        when(userService.getAllUsers(pageable)).thenReturn(Collections.singletonList(new UserDto()));

        mockMvc.perform(get("/v1/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}

