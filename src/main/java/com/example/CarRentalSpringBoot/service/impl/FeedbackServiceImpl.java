package com.example.CarRentalSpringBoot.service.impl;

import com.example.CarRentalSpringBoot.entity.Car;
import com.example.CarRentalSpringBoot.entity.User;
import com.example.CarRentalSpringBoot.pojo.converter.FeedbackConverter;
import com.example.CarRentalSpringBoot.pojo.dto.FeedbackDto;
import com.example.CarRentalSpringBoot.repository.CarRepository;
import com.example.CarRentalSpringBoot.repository.FeedbackRepository;
import com.example.CarRentalSpringBoot.repository.UserRepository;
import com.example.CarRentalSpringBoot.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;


    @Override
    public List<FeedbackDto> getAllFeedbacks(Pageable pageable) {
        return feedbackRepository.findAll().stream()
                .map(FeedbackConverter::feedbackConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void leaveFeedback(FeedbackDto feedbackDto) {
        User user = userRepository.findByUserId(feedbackDto.getUserId());
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        Car car = carRepository.findByCarId(feedbackDto.getCarId());
        if(car == null) {
            throw new RuntimeException("Car not found");
        }
        feedbackRepository.save(FeedbackConverter.dtoConvertToFeedback(feedbackDto, user, car));
    }

    @Override
    public List<FeedbackDto> getAllFeedbacksByCarId(Long carId, Pageable pageable) {
        return feedbackRepository.findByCarId(carId).stream()
                .map(FeedbackConverter::feedbackConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDto> getAllFeedbacksByUserId(Long userId, Pageable pageable) {
        return feedbackRepository.findByUserId(userId).stream()
                .map(FeedbackConverter::feedbackConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteFeedback(Long feedbackId) {
        if (feedbackRepository.existsById(feedbackId)) {
            feedbackRepository.deleteById(feedbackId);
            return true;
        }
        return false;
    }

}
