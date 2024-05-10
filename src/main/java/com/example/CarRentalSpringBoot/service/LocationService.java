package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.pojo.dto.LocationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationService {
    List<LocationDto> getAllLocations(Pageable pageable);

    void newLocation(LocationDto locationDto);

    LocationDto getLocation(Long locationId);

    void deleteLocation(Long locationId);

}
