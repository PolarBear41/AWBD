package com.example.CarRentalSpringBoot.pojo.converter;

import com.example.CarRentalSpringBoot.entity.Car;
import com.example.CarRentalSpringBoot.entity.Feedback;
import com.example.CarRentalSpringBoot.entity.User;
import com.example.CarRentalSpringBoot.pojo.dto.FeedbackDto;


public class FeedbackConverter {

    public static FeedbackDto feedbackConvertToDto(Feedback feedback) {
        return FeedbackDto.builder()
                .id(feedback.getFeedbackId())
                .feedbackText(feedback.getFeedbackText())
                .feedbackDate(feedback.getFeedbackDate())
                .rating(feedback.getRating())
                .userId(feedback.getUser().getUserId())
                .carId(feedback.getCar().getCarId())
                .build();
    }

    public static Feedback dtoConvertToFeedback(FeedbackDto feedbackDto, User user, Car car) {
        return Feedback.builder()
                .feedbackId(feedbackDto.getId())
                .feedbackText(feedbackDto.getFeedbackText())
                .feedbackDate(feedbackDto.getFeedbackDate())
                .rating(feedbackDto.getRating())
                .car(car)
                .user(user)
                .build();
    }
}
