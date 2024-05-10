package com.example.CarRentalSpringBoot.service.impl;

import com.example.CarRentalSpringBoot.pojo.converter.LocationConverter;
import com.example.CarRentalSpringBoot.pojo.dto.LocationDto;
import com.example.CarRentalSpringBoot.repository.LocationRepository;
import com.example.CarRentalSpringBoot.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public List<LocationDto> getAllLocations(Pageable pageable) {
        return locationRepository.findAll(pageable).stream()
                .map(LocationConverter::locationConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void newLocation(LocationDto locationDto) {
        locationRepository.save(LocationConverter.dtoConvertToLocation(locationDto));
    }

    @Override
    public LocationDto getLocation(Long locationId) {
        var locationOptional = locationRepository.findById(locationId);
        return locationOptional.map(LocationConverter::locationConvertToDto).orElse(null);
    }

    @Override
    public void deleteLocation(Long locationId) {
        if (locationRepository.existsById(locationId)) {
            locationRepository.deleteById(locationId);
        }
    }
}
