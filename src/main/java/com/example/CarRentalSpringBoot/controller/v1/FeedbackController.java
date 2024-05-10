package com.example.CarRentalSpringBoot.controller.v1;

import com.example.CarRentalSpringBoot.pojo.dto.FeedbackDto;
import com.example.CarRentalSpringBoot.service.impl.FeedbackServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController("FeedbackControllerV1")
@RequestMapping("/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackServiceImpl feedbackService;

    @PostMapping
    @Operation(summary = "Leave feedback", description = "Leave feedback for a car rental.")
    public ResponseEntity<Void> leaveFeedback(@RequestBody @Valid FeedbackDto feedbackDto) {
        feedbackService.leaveFeedback(feedbackDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get all feedbacks", description = "Get a list of all feedbacks.")
    public List<FeedbackDto> getAllFeedbacks(Pageable pageable) {
        return feedbackService.getAllFeedbacks(pageable);
    }

    @GetMapping("/{carId}")
    @Operation(summary = "Get feedbacks by car ID", description = "Get feedbacks for a specific car by its ID.")
    public List<FeedbackDto> getFeedbacksByCarId(@PathVariable Long carId, Pageable pageable) {
        return feedbackService.getAllFeedbacksByCarId(carId, pageable);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get feedbacks by user ID", description = "Get feedbacks submitted by a specific user.")
    public List<FeedbackDto> getFeedbacksByUserId(@PathVariable Long userId, Pageable pageable) {
        return feedbackService.getAllFeedbacksByUserId(userId, pageable);
    }

    @DeleteMapping("/{feedbackId}")
    @Operation(summary = "Delete Feedback by ID", description = "Delete feedback by providing its unique ID.")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long feedbackId) {
        boolean deleted = feedbackService.deleteFeedback(feedbackId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


