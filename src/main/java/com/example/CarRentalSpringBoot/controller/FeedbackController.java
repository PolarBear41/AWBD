package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.pojo.dto.CarDto;
import com.example.CarRentalSpringBoot.pojo.dto.FeedbackDto;
import com.example.CarRentalSpringBoot.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@AllArgsConstructor
@Controller
public class FeedbackController {

    private FeedbackService feedbackService;

    @GetMapping(value = {"/feedbacks"})
    public ModelAndView displayFeedbackPage() {
        ModelAndView mav = new ModelAndView("feedbacks");
        mav.addObject("feedbacks", feedbackService.getAllFeedbacks(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/feedbacksSearchByUser")
    public ModelAndView getFeedbackByUserId(@RequestParam Long userId) {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacksByUserId(userId, PageRequest.of(0, 10));
        ModelAndView mav = new ModelAndView("feedbacks");
        mav.addObject("feedbacks", feedbackService.getAllFeedbacks(PageRequest.of(0, 10)));

        if (feedbacks != null && !feedbacks.isEmpty()) {
            mav.addObject("feedbacksSearchByUser", feedbacks);
        }

        return mav;
    }

    @GetMapping(value = "/feedbacksSearchByCar")
    public ModelAndView getFeedbackByCarId(@RequestParam Long carId) {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacksByCarId(carId, PageRequest.of(0, 10));
        ModelAndView mav = new ModelAndView("feedbacks");
        mav.addObject("feedbacks", feedbackService.getAllFeedbacks(PageRequest.of(0, 10)));

        if (feedbacks != null && !feedbacks.isEmpty()) {
            mav.addObject("feedbacksSearchByCar", feedbacks);
        }

        return mav;
    }

    @GetMapping(value = "/feedbacksAdd")
    public ModelAndView displayFeedbackAddPage() {
        ModelAndView mav = new ModelAndView("feedbacksAdd");
        mav.addObject("feedbackDto", new FeedbackDto());

        return mav;
    }

    @PostMapping(value = "/feedbacks")
    public ModelAndView addFeedback(@ModelAttribute(value = "feedbackDto") @Valid FeedbackDto feedbackDto, ModelAndView mav) {
        feedbackService.leaveFeedback(feedbackDto);
        mav.addObject("feedbacks", feedbackService.getAllFeedbacks(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/feedbacksDelete")
    public ModelAndView deleteFeedback(@RequestParam Long id, @ModelAttribute(value = "feedbackDto") FeedbackDto feedbackDto) {
        feedbackService.deleteFeedback(id);
        ModelAndView mav = new ModelAndView("feedbacks");
        mav.addObject("feedbacks", feedbackService.getAllFeedbacks(PageRequest.of(0, 10)));

        return mav;
    }
}
