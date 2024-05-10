package com.example.CarRentalSpringBoot.controller.v1;

import com.example.CarRentalSpringBoot.pojo.dto.LocationDto;
import com.example.CarRentalSpringBoot.service.impl.LocationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("LocationControllerV1")
@RequestMapping("/v1/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationServiceImpl locationService;

    @PostMapping
    @Operation(summary = "Create a New Location", description = "Create a new location record.")
    public ResponseEntity<Void> newLocation(@RequestBody @Valid LocationDto locationDto) {
        locationService.newLocation(locationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{locationId}")
    @Operation(summary = "Get Location by ID", description = "Retrieve location details by providing its unique ID.")
    public LocationDto getLocation(@PathVariable Long locationId) {
        return locationService.getLocation(locationId);
    }

    @GetMapping
    @Operation(summary = "Get All Locations", description = "Retrieve a list of all available locations.")
    public List<LocationDto> getAllLocations(Pageable pageable) {
        return locationService.getAllLocations(pageable);
    }
}
