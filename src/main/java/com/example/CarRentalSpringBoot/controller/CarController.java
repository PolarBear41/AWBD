package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.pojo.dto.CarDto;
import com.example.CarRentalSpringBoot.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class CarController {

    private CarService carService;

    @GetMapping(value = {"/", "/cars"})
    public ModelAndView displayCarPage() {
        ModelAndView mav = new ModelAndView("cars");
        mav.addObject("cars", carService.getAllCars(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/carsSearch")
    public ModelAndView getCar(@RequestParam Long id) {
        CarDto carDto = carService.getCarById(id);
        ModelAndView mav = new ModelAndView("cars");
        mav.addObject("cars", carService.getAllCars(PageRequest.of(0, 10)));

        if (carDto != null) {
            mav.addObject("carSearch", carDto);
        }

        return mav;
    }

    @GetMapping(value = "/carsAdd")
    public ModelAndView displayCarAddPage() {
        ModelAndView mav = new ModelAndView("carsAdd");
        mav.addObject("carDto", new CarDto());

        return mav;
    }

    @PostMapping(value = "/cars")
    public ModelAndView addCar(@ModelAttribute(value = "carDto") @Valid CarDto carDto, ModelAndView mav) {
        carService.addCar(carDto);
        mav.addObject("cars", carService.getAllCars(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/carsEdit")
    public ModelAndView displayCarEditPage(@RequestParam Long id) {
        CarDto car = carService.getCarById(id);
        ModelAndView mav = new ModelAndView("carsEdit");
        mav.addObject("carDto", car);

        return mav;
    }

    @PutMapping(value = "/carsEdit")
    public ModelAndView displayCarEditPage(@ModelAttribute(value = "carDto") @Valid CarDto carDto) {
        CarDto car = carService.getCarById(carDto.getId());
        carService.updateCar(car.getId(), carDto);
        ModelAndView mav = new ModelAndView("cars");
        mav.addObject("cars", carService.getAllCars(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/carsDelete")
    public ModelAndView deleteCar(@RequestParam Long id, @ModelAttribute(value = "carDto") CarDto carDto) {
        carService.deleteById(id);
        ModelAndView mav = new ModelAndView("cars");
        mav.addObject("cars", carService.getAllCars(PageRequest.of(0, 10)));

        return mav;
    }
}
