package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.pojo.dto.LocationDto;
import com.example.CarRentalSpringBoot.service.LocationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class LocationController {

    private LocationService locationService;

    @GetMapping(value = {"/locations"})
    public ModelAndView displayLocationPage() {
        ModelAndView mav = new ModelAndView("locations");
        mav.addObject("locations", locationService.getAllLocations(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/locationsAdd")
    public ModelAndView displayLocationAddPage() {
        ModelAndView mav = new ModelAndView("locationsAdd");
        mav.addObject("locationDto", new LocationDto());

        return mav;
    }

    @PostMapping(value = "/locations")
    public ModelAndView addLocation(@ModelAttribute(value = "locationDto") @Valid LocationDto locationDto, ModelAndView mav) {
        locationService.newLocation(locationDto);
        mav.addObject("locations", locationService.getAllLocations(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/locationsDelete")
    public ModelAndView deleteLocation(@RequestParam Long id, @ModelAttribute(value = "locationDto") LocationDto locationDto) {
        locationService.deleteLocation(id);
        ModelAndView mav = new ModelAndView("locations");
        mav.addObject("locations", locationService.getAllLocations(PageRequest.of(0, 10)));

        return mav;
    }
}
