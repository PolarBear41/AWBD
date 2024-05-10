package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.pojo.dto.FeedbackDto;
import com.example.CarRentalSpringBoot.pojo.dto.ReservationDto;
import com.example.CarRentalSpringBoot.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@AllArgsConstructor
@Controller
public class ReservationController {

    private ReservationService reservationService;

    @GetMapping(value = {"/reservations"})
    public ModelAndView displayReservationPage() {
        ModelAndView mav = new ModelAndView("reservations");
        mav.addObject("reservations", reservationService.getAllReservations(PageRequest.of(0, 10)));

        return mav;
    }

    @GetMapping(value = "/reservationsSearchByUser")
    public ModelAndView getReservationsByUserId(@RequestParam Long userId) {
        List<ReservationDto> reservations = reservationService.getAllReservationsByUserId(userId);
        ModelAndView mav = new ModelAndView("reservations");
        mav.addObject("reservations", reservationService.getAllReservations(PageRequest.of(0, 10)));

        if (reservations != null && !reservations.isEmpty()) {
            mav.addObject("reservationsSearchByUser", reservations);
        }

        return mav;
    }

    @GetMapping(value = "/reservationsSearchByCar")
    public ModelAndView getReservationsByCarId(@RequestParam Long carId) {
        List<ReservationDto> reservations = reservationService.getAllReservationsByCarId(carId);
        ModelAndView mav = new ModelAndView("reservations");
        mav.addObject("reservations", reservationService.getAllReservations(PageRequest.of(0, 10)));

        if (reservations != null && !reservations.isEmpty()) {
            mav.addObject("reservationsSearchByCar", reservations);
        }

        return mav;
    }

    @GetMapping(value = "/reservationsAdd")
    public ModelAndView displayReservationAddPage() {
        ModelAndView mav = new ModelAndView("reservationsAdd");
        mav.addObject("reservationDto", new ReservationDto());

        return mav;
    }

    @PostMapping(value = "/reservations")
    public ModelAndView addReservation(@ModelAttribute(value = "reservationDto") @Valid ReservationDto reservationDto, ModelAndView mav) {
        reservationService.addReservation(reservationDto);
        mav.addObject("reservations", reservationService.getAllReservations(PageRequest.of(0, 10)));

        return mav;
    }

}
