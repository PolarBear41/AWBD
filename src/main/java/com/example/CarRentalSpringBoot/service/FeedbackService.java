package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.pojo.dto.FeedbackDto;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface FeedbackService {
    List<FeedbackDto> getAllFeedbacks(Pageable pageable);

    void leaveFeedback(FeedbackDto feedbackDto);

    List<FeedbackDto> getAllFeedbacksByCarId(Long carId, Pageable pageable);

    List<FeedbackDto> getAllFeedbacksByUserId(Long userId, Pageable pageable);

    boolean deleteFeedback(Long feedbackId);
}
