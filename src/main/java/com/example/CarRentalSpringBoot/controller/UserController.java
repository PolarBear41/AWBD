package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.pojo.dto.UserDto;
import com.example.CarRentalSpringBoot.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class UserController {

    private UserService userService;

    @GetMapping(value = {"/users"})
    public ModelAndView displayUserPage() {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", userService.getAllUsers(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/usersAdd")
    public ModelAndView displayUserAddPage() {
        ModelAndView mav = new ModelAndView("usersAdd");
        mav.addObject("userDto", new UserDto());

        return mav;
    }

    @PostMapping(value = "/users")
    public ModelAndView addUser(@ModelAttribute(value = "userDto") @Valid UserDto userDto, ModelAndView mav) {
        userService.createUser(userDto);
        mav.addObject("users", userService.getAllUsers(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/usersEdit")
    public ModelAndView displayUserEditPage(@RequestParam Long id) {
        UserDto user = userService.getUserById(id);
        ModelAndView mav = new ModelAndView("usersEdit");
        mav.addObject("userDto", user);

        return mav;
    }

    @PutMapping(value = "/usersEdit")
    public ModelAndView displayUserEditPage(@ModelAttribute(value = "userDto") @Valid UserDto userDto) {
        UserDto user = userService.getUserById(userDto.getId());
        userService.updateUser(user.getId(), userDto);
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", userService.getAllUsers(PageRequest.of(0, 10)));

        return mav;
    }

}
