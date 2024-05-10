package com.example.CarRentalSpringBoot.controller.v1;

import com.example.CarRentalSpringBoot.pojo.dto.UserDto;
import com.example.CarRentalSpringBoot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import java.util.List;

@RestController("UserControllerV1")
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create User", description = "Create a new user.")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update User", description = "Update an existing user record by its ID.")
    public ResponseEntity<Void> updateUser(@Parameter(description = "User ID") @PathVariable Long userId,
                                          @RequestBody @Valid UserDto userDto) {
        userService.updateUser(userId, userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get User by ID", description = "Retrieve user details by providing their unique ID.")
    public ResponseEntity<UserDto> getSpecificUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    @Operation(summary = "Get All Users", description = "Retrieve a list of all users.")
    public ResponseEntity<List<UserDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }
}

